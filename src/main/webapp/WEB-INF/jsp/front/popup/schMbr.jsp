<%@ page import="java.util.List" %>
<%@ page import="org.springframework.util.ObjectUtils" %>
<%@ page import="com.emfldlem.Common.Util" %>
<%@ page import="com.emfldlem.DocuApproval.Mbr.Entity.MbrMgmtEntity" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    List<MbrMgmtEntity> MbrMgmtList = (List<MbrMgmtEntity>) request.getAttribute("mbrMgmtList");
%>

<script type="text/javascript">
    $(document).ready(function () {

    })

</script>
<div class="col-md-12" style="height: 500px; overflow: auto;">
    <table class="table table-bordered" id="mbrListTable">

        <thead>
        <tr>
            <th style="width: 10px"></th>
            <th>이름</th>
            <th>부서</th>
        </tr>
        </thead>
        <tbody>
        <%if (ObjectUtils.isEmpty(MbrMgmtList)) {%>
        <tr>
            <td colspan="3">조회 된 결과가 없습니다.</td>
        </tr>
        <%}else {
            int index = 0;
            for(MbrMgmtEntity mbrMgmt : MbrMgmtList) {%>
        <tr>
            <input type="hidden" class="mbr_no" value="<%=mbrMgmt.getMbrNo()%>">
            <td>
                <div class="text-center">
                    <div class="custom-control custom-checkbox">
                        <input class="custom-control-input list-checkbox" name="list-checkbox" type="checkbox" id="checkbox<%=index%>">
                        <label for="checkbox<%=index%>" class="custom-control-label"></label>
                    </div>
                </div>
            </td>
            <td class="mbr_nm"><%=mbrMgmt.getMbrNm()%></td>
            <td class="dept_nm"><%=mbrMgmt.getDeptNm()%></td>
        </tr>
        <%index++;
            }
            }%>
        </tbody>
    </table>
</div>
