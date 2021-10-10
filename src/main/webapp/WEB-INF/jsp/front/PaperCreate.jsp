<%@ page import="java.util.HashMap" %>
<%@ page import="org.springframework.util.ObjectUtils" %>
<%@ page import="com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="../common/header.jsp"/>

<%
    PaperMgmtEntity paper = (PaperMgmtEntity) request.getAttribute("paperMgmtEntity");
    String paperNo = ObjectUtils.isEmpty(paper) ? null : paper.getPaperNo();

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

    $(document).ready(function () {
        //결재자 추가 버튼
        $('#add_appr').click(function () {

            var appr_ord = $('#appr_list tbody tr').length;

            $('#appr_list tbody').append("<tr>\n" +
                "<input type='hidden' class='mbr_no' value='11'>" +
                "<td class='appr_ord'>" + (++appr_ord) + "</td>\n" +
                "<td>183</td>\n" +
                "<td>John Doe</td>\n" +
                "<td><button type=\"button\" class=\"btn-xs btn-default del_appr\" onclick='del_appr(this);'>x</button></td>\n" +
                "</tr>")
        })

        //전자 결재 문서 제출
        $('#appr_req_btn').click(function () {

            var paper_title = $('#paper_title').val(); //문서 제목
            var paper_se = $('#paper_se').val(); // 문서 분류
            var paper_desc = $('#paper_desc').val(); //문서 내용
            var appr_lise = $('#appr_list tbody tr'); // 승인자 리스트

            if (trim(paper_title) == "") {
                alert("제목을 입력해 주세요.");
                $('#paper_title').focus();
                return;
            }
            if (!checkLen('paper_title', 20, "제목은 ")) return;

            if (isEmpty(paper_se)) {
                alert("분류를 입력해주세요.");
                return false;
            }

            if (trim(paper_desc) == "") {
                alert("내용을 입력해 주세요.");
                $('#ta_content').val("");
                $('#ta_content').focus();
                return;
            }

            if (!checkLen('paper_desc', 1450, "내용은 ")) return;

            if (appr_lise.length <= 0 || appr_lise.length > 11) {
                alert("승인자를 1명 이상 10명 이하로 선택해주세요.");
                return false;
            }


            var paperNo = <%if(ObjectUtils.isEmpty(paperNo)) { %> null
            <% }else {%>
            '<%=paperNo%>'
            <% } %>
            var rows = [];
            $('#appr_list tbody tr').each(function (index, trItem) {
                if (index != 0) {
                    var row = {};
                    var mbr_no = $(trItem).find(".mbr_no").val();
                    var appr_ord = $(trItem).find(".appr_ord").text();

                    row.paperNo = paperNo;
                    row.mbrNo = mbr_no;
                    row.apprOrd = appr_ord;

                    rows.push(row);
                }
            });

            var param = {
                paperNo                : paperNo,
                paperTitle             : paper_title,
                paperSe                : paper_se,
                paperDesc              : paper_desc,
                paperApprMgmtEntityList: rows
            };

            $.ajax({
                url: "/paper/savePaper",
                type: "POST",
                contentType: 'application/json',
                dataType: "JSON",
                data: JSON.stringify(param),
                success: function (result) {
                    console.log(result);
                    if (result.res_cd === 'S') {
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
                    <h1>전자 결제 작성</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">전자 결제 작성</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

        <!-- Default box -->
        <div class="card">
            <div class="card-body row">
                <div class="col-7">
                    <div class="form-group">
                        <label for="paper_title">제목</label>
                        <input type="text" id="paper_title" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>분류</label>
                        <select class="form-control" id="paper_se">
                            <option value="paper_se_10">분류 1</option>
                            <option value="paper_se_20">분류 2</option>
                            <option value="paper_se_30">분류 3</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="paper_desc">Message</label>
                        <textarea class="form-control" rows="4" id="paper_desc"></textarea>
                    </div>

                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="전자문서 결제 신청" id="appr_req_btn">
                    </div>
                </div>
                <div class="col-5 align-items-center justify-content-center">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">

                                    <div class="card-tools">
                                        <div class="btn-group w-100">
                                            <span class="btn btn-success col fileinput-button dz-clickable" id="add_appr">
                                                <i class="fas fa-plus"></i>
                                                <span>승인자 추가</span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body table-responsive p-0">
                                    <table class="table table-hover text-nowrap" id="appr_list">
                                        <thead>
                                        <tr>
                                            <th>결제순서</th>
                                            <th>ID</th>
                                            <th>User</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<jsp:include page="../common/footer.jsp"/>