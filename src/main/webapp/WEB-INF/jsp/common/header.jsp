<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AdminLTE 3 | Mailbox</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/plugins/fontawesome-free/css/all.min.css">
    <!-- icheck bootstrap -->
    <link rel="stylesheet" href="/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/dist/css/adminlte.min.css">

    <!-- jQuery -->
    <script src="/plugins/jquery/jquery.min.js"></script>
    <!-- Bootstrap 4 -->
    <script src="/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- AdminLTE App -->
    <script src="/dist/js/adminlte.min.js"></script>

    <script src="/dist/js/adminlte.min.js"></script>

    <!-- Page specific script -->
</head>
<script>
    var isEmpty = function(value){ if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){ return true }else{ return false } };
</script>
<style>
    #ajax-loading {
        display:none;
        position:fixed;
        left:0;
        top:0;
        width:100%;
        height:100%;
        z-index:9999;
        background:url(/images/bx_loader.gif) 50% 50% no-repeat rgba(249, 249, 249, 0.5);
    }
</style>
<div id="ajax-loading">

</div>

<body class="hold-transition sidebar-mini">
<!-- Page Wrapper -->
<div class="wrapper">
    <jsp:include page="navbar.jsp"></jsp:include>
    <jsp:include page="sidebar.jsp"></jsp:include>