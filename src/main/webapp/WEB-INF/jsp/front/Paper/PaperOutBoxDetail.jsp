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
    List<PaperApprMgmtEntity> paperApprList = (List<PaperApprMgmtEntity>) request.getAttribute("paperApprList");
    String paperNo = ObjectUtils.isEmpty(paper) ? null : paper.getPaperNo();
    String paperSeNm = "";
    if(Const.PAPER_SE_10.equals(paper.getPaperSe())) {paperSeNm="분류1";} else if(Const.PAPER_SE_20.equals(paper.getPaperSe())) {paperSeNm="분류2";} else if(Const.PAPER_SE_30.equals(paper.getPaperSe())) {paperSeNm="분류3";}

%>
<script type="text/javascript">
    $(document).ready(function () {


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
            <div class="card-body row">
                <div class="col-md-7">
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
                        <textarea class="form-control" rows="4" id="paper_desc" disabled>
                            <%=paper.getPaperDesc()%>
                        </textarea>
                    </div>
                </div>

                <div class="col-md-5 card card-info">
                    <div class="card-header">
                        <h3 class="card-title">결재자</h3>

                        <div class="card-tools">
                            <%--<button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                                <i class="fas fa-minus"></i>
                            </button>--%>
                        </div>
                    </div>
                    <div class="card-body p-0">
                        <table class="table text-center">
                            <thead>
                            <tr>
                                <th>순서</th>
                                <th>이름</th>
                                <th>부서</th>
                                <th>승인/반려</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%  String rejectYn = "N";
                                for (PaperApprMgmtEntity paperAppr : paperApprList) {
                                %>
                            <tr>
                                <td><%=paperAppr.getApprOrder()%></td>
                                <td><%=paperAppr.getMbrNm()%></td>
                                <td><%=paperAppr.getDeptNm()%></td>
                                <td>
                                    <%if("N".equals(rejectYn)) { %>
                                        <%if(StringUtils.isEmpty(paperAppr.getApprStat())) { %>
                                        <span class="badge bg-primary">대기</span>
                                        <% } else if ("10".equals(paperAppr.getApprStat())) { %>
                                        <span class="badge bg-success">승인</span>
                                        <% } else if ("20".equals(paperAppr.getApprStat())) { %>
                                        <span class="badge bg-danger">반려</span>
                                        <%}%>
                                    <%}%>
                                </td>
                            </tr>
                            <% if ("20".equals(paperAppr.getApprStat())) { rejectYn = "Y"; }
                                }%>

                            </tbody>
                        </table>
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