<%@ page import="java.util.HashMap" %>
<%@ page import="org.springframework.util.ObjectUtils" %>
<%@ page import="com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity" %>
<%@ page import="com.emfldlem.DocuApproval.Paper.Entity.PaperApprMgmtEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page import="com.emfldlem.Common.Const" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="../../common/header.jsp"/>

<%
    PaperMgmtEntity paper = (PaperMgmtEntity) request.getAttribute("paper");
    PaperApprMgmtEntity paperAppr = (PaperApprMgmtEntity) request.getAttribute("paperAppr");

    String paperNo = "";
    String paperApprNo = paperAppr.getPaperApprNo();
    String paperSeNm = "";
    if (!ObjectUtils.isEmpty(paper)) {
        paperNo = paper.getPaperNo();
        if (Const.PAPER_SE_10.equals(paper.getPaperSe())) {
            paperSeNm = "분류1";
        } else if (Const.PAPER_SE_20.equals(paper.getPaperSe())) {
            paperSeNm = "분류2";
        } else if (Const.PAPER_SE_30.equals(paper.getPaperSe())) {
            paperSeNm = "분류3";
        }
    }

%>
<script type="text/javascript">
    function checkLen(name, length, str) {
        var temp;
        var count;
        count = 0;
        len = $('#' + name).val().length;

        for (k = 0; k < len; k++) {
            temp = $('#' + name).val().charCodeAt(k);

            if (temp < 128)
                count++;
            else
                count += 2;
        }

        if (count > (length * 2)) {
            if (str == "") alert("한글 " + length + "자(영문 " + (length * 2) + "자)를 넘을 수 없습니다.");
            else alert(str + " 한글 " + length + "자(영문 " + (length * 2) + "자)를 넘을 수 없습니다.");

            $('#' + name).val($('#' + name).val().substring(0, $('#' + name).val().length - 1));
            $('#' + name).focus();
            return false;
        }

        return true;
    }

    function trim(tmpStr) {
        var atChar;
        if (tmpStr.length > 0)
            atChar = tmpStr.charAt(0);

        while (isSpace(atChar)) {
            tmpStr = tmpStr.substring(1, tmpStr.length);
            atChar = tmpStr.charAt(0);
        }

        if (tmpStr.length > 0)
            atChar = tmpStr.charAt(tmpStr.length - 1);

        while (isSpace(atChar)) {
            tmpStr = tmpStr.substring(0, (tmpStr.length - 1));
            atChar = tmpStr.charAt(tmpStr.length - 1);
        }

        return tmpStr;
    }

    function isSpace(inChar) {
        return (inChar == ' ' || inChar == '\t' || inChar == '\n');
    }


    //결재자 삭제
    function del_appr(that) {
        $(that).parent().parent().remove();

        $.each($('#appr_list tbody tr'), function (i, obj) {
            $(obj).find(".appr_ord").text(++i);
        })
    }

    function getschMbrList() {
        var param = {
            mbr_nm: $('#schMbrNm').val()
        }
        $('#div_schMbrList').load("/popup/getSchMbrList", param);
    }

    function modalAddMbr() {
        var checkList = $("#mbrListTable [name='list-checkbox']:checked");
        if (checkList.length == 0) {
            alert("결재자를 선택해주세요.");
            return false;
        } else {
            var appr_ord = $('#appr_list tbody tr').length;
            var mbr_no_list = "";
            $.each($('#appr_list input.mbr_no'), function (i, obj) {
                mbr_no_list = mbr_no_list + $(obj).val() + ",";
            })
            $.each(checkList, function (i, obj) {
                var $this = $(obj).parent().parent().parent().parent();
                var row_mbr_no = $this.find(".mbr_no").val();
                var row_mbr_nm = $this.find(".mbr_nm").text();
                var row_dept_nm = $this.find(".dept_nm").text();

                if (mbr_no_list.indexOf(row_mbr_no) > -1) {
                    return false;
                } else {
                    $('#appr_list tbody').append("<tr>\n" +
                        "<input type='hidden' class='mbr_no' value='" + row_mbr_no + "'>" +
                        "<td class='appr_ord text-center'>" + (++appr_ord) + "</td>\n" +
                        "<td>" + row_mbr_nm + "</td>\n" +
                        "<td>" + row_dept_nm + "</td>\n" +
                        "<td><button type=\"button\" class=\"btn-xs btn-default del_appr\" onclick='del_appr(this);'>x</button></td>\n" +
                        "</tr>")
                }

            })
            $('#div_schMbrList').html('');
            $('#modal-default').modal('hide');

        }

    }

    $(document).ready(function () {


        //전자 결재 문서 제출
        $('.statBtn').click(function () {

            var appr_stat = $(this).data('apprstat'); //승인상태
            var appr_comment = $('#appr_comment').val(); //코멘트

            var param = {
                paperNo    : '<%=paperNo%>',
                paperApprNo: '<%=paperApprNo%>',
                apprStat   : appr_stat,
                apprComment: appr_comment
            };

            $.ajax({
                url        : "/paper/savePaperAppr",
                type       : "POST",
                contentType: 'application/json',
                dataType   : "JSON",
                data       : JSON.stringify(param),
                success    : function (result) {
                    if (result.res_cd == 'S') {
                        alert("전자결제를 신청하였습니다.");
                    } else {
                        alert(result.res_msg);
                    }
                }
            });

        })

    });

</script>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>전자 결제 상세</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">전자 결제 상세</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

        <!-- Default box -->
        <div class="card">
            <div class="card-header">
                <div class="card-tools">
                    <button type="button" class="btn btn-primary statBtn" data-apprstat="20">승인</button>
                    <button type="button" class="btn btn-danger statBtn" data-apprstat="30">거절</button>

                </div>
            </div>
            <div class="card-body row">
                <div class="col-md-7">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="paper_title">기안자</label>
                                <input type="text" class="form-control" value="<%=paper.getRegNm()%>" disabled/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="paper_title">등록일시</label>
                                <input type="text" class="form-control" value="<%=paper.getRegDtime()%>" disabled/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="paper_title">제목</label>
                        <input type="text" id="paper_title" class="form-control" value="<%=paper.getPaperTitle()%>" disabled/>
                    </div>
                    <div class="form-group">
                        <label>분류</label>
                        <input type="text" id="paper_se" class="form-control" value="<%=paperSeNm%>" disabled/>
                    </div>

                    <div class="form-group">
                        <label for="paper_desc">내용</label>
                        <textarea class="form-control" rows="4" id="paper_desc" disabled><%=paper.getPaperDesc()%></textarea>
                    </div>
                </div>

                <div class="col-md-5 card card-info">
                    <div class="card-header">
                        <h3 class="card-title">코멘트</h3>
                    </div>
                    <div class="card-body p-0">
                        <div class="form-group mt-2">
                            <textarea class="form-control" rows="10" id="appr_comment"></textarea>
                        </div>

                    </div>
                    <!-- /.card-body -->
                </div>
            </div>
            <div class="card-footer">
                <div class="float-right">
                    <a href="/paper/outBoxList" class="btn btn-default">목록</a>
                </div>

            </div>

        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<jsp:include page="../../common/footer.jsp"/>