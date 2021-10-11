<%@ page import="java.util.HashMap" %>
<%@ page import="com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.util.ObjectUtils" %>
<%@ page import="java.awt.print.Pageable" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    List<PaperMgmtEntity> paperMgmtList = (List<PaperMgmtEntity>) request.getAttribute("paperMgmtList");
%>

<script type="text/javascript">

    $(document).ready(function () {
    })

</script>

<table class="table table-hover table-striped">
    <thead>
    <tr>
        <th></th>
        <th>제목</th>
        <th>진행상태</th>
        <th>등록일</th>
    </tr>
    </thead>
    <tbody>

    <%if (ObjectUtils.isEmpty(paperMgmtList)) {%>
    <tr>
        <td colspan="4">조회 된 결과가 없습니다.</td>
    </tr>
    <%
    } else {
        int index = 0;
        for (PaperMgmtEntity paperMgmt : paperMgmtList) {
            String paperSateNm = "";
            if("20".equals(paperMgmt.getPaperStat())) { paperSateNm = "승인"; } else if ("30".equals(paperMgmt.getPaperStat())) {  paperSateNm = "거절";} else {  paperSateNm = "결제진행중"; };
    %>
    <tr>
        <td>

        </td>
        <td class="mailbox-subject" >
            <a href="/paper/getPaper?paper_no=<%=paperMgmt.getPaperNo()%>" data-paperno="<%=paperMgmt.getPaperNo()%>"><%=paperMgmt.getPaperTitle()%></a>
        </td>
        <td>
            <%=paperSateNm%>
        </td>

        <td>
            <%=paperMgmt.getRegDtime()%>
        </td>

    </tr>

    <%
                index++;
            }
        }
    %>

    <%--  <tr>
          <td>
              <div class="icheck-primary">
                  <input type="checkbox" value="" id="check1">
                  <label for="check1"></label>
              </div>
          </td>
          <td class="mailbox-star"><a href="#"><i class="fas fa-star text-warning"></i></a></td>
          <td class="mailbox-name"><a href="read-mail.html">Alexander Pierce</a></td>
          <td class="mailbox-subject"><b>AdminLTE 3.0 Issue</b> - Trying to find a solution to this problem...
          </td>
          <td class="mailbox-attachment"></td>
          <td class="mailbox-date">5 mins ago</td>
      </tr>
      --%>
    </tbody>
</table>
<!-- /.table -->
