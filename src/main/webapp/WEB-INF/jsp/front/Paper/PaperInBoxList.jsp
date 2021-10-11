<%@ page import="java.util.HashMap" %>
<%@ page import="com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.util.ObjectUtils" %>
<%@ page import="java.awt.print.Pageable" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="../../common/header.jsp"/>

<%
    int totalPages = (int) request.getAttribute("totalPages");
    if(ObjectUtils.isEmpty(totalPages)) { totalPages = 0;};
%>


<script type="text/javascript">
    $(document).ready(function () {

        function getPaperList(page) {
            var param = {
                page : page-1,
                size : 10
            }
            $('#div_paperList').load("/paper/getPaperInBoxList",param);
        }

        $('#pagination').twbsPagination({
            totalPages  : <%=totalPages%>, // 전체 페이지
            startPage   : 1, // 시작(현재) 페이지
            visiblePages: 10, // 최대로 보여줄 페이지
            prev        : "‹", // Previous Button Label
            next        : "›", // Next Button Label
            first       : '«', // First Button Label
            last        : '»', // Last Button Label
            onPageClick : function (event, page) { // Page Click event
                console.info("current page : " + page);
            }
        }).on('page', function (event, page) {
            getPaperList(page);
        });

        //초기 리스트 조회
        getPaperList(1);

    })

</script>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>InBox</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">InBox</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="card card-primary card-outline">
                    <div class="card-header">
                        <h3 class="card-title">InBox</h3>

                        <div class="card-tools">
                            <div class="input-group input-group-sm">
                                <input type="text" class="form-control" placeholder="Search Mail">
                                <div class="input-group-append">
                                    <div class="btn btn-primary">
                                        <i class="fas fa-search"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.card-tools -->
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body p-0">
                        <div class="table-responsive mailbox-messages " id="div_paperList">
                        </div>
                        <!-- /.mail-box-messages -->
                    </div>
                    <!-- /.card-body -->
                    <div class="card-footer clearfix p-1 ">
                        <div class="paging-div">
                            <ul class="pagination" id="pagination"></ul>
                        </div>
                    </div>

                </div>
                <!-- /.card -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<jsp:include page="../../common/footer.jsp"/>