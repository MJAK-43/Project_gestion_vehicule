<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Reservations
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/rents/create">Ajouter</a> 
               <br></br>
            </h1>
        </section>



         <form style="float:right" class="form-horizontal" method="post" action="/rentmanager/rents/search">
       <input   type="text"  id="search" name="search" placeholder=" Rechercher une reservation" >
       <button type="submit" class="btn btn-primary">Chercher</button>
       </form>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body no-padding">
                            <table class="table table-striped">
                                <tr>
                                    <!-- <th style="width: 10px">#</th> -->
                                    <th>Voiture</th>
                                    <th>Client</th>
                                    <th>Debut</th>
                                    <th>Fin</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach items="${ reservations }" var="rents">
                                    <tr>
                                      
                                        <td>${rents.voiture}</td>
                                        <td>${rents.client}</td>
                                        <td>${rents.dateStart}</td>
                                        <td>${rents.dateEnd}</td>
                               <td style="width :5%">
                              <form class="form-horizontal" method="get" action="/rentmanager/rents/editer">
                               <input type="hidden" name="id" value="${rents.id}" >
                               <button class="btn btn-success" type="submit">  <i class="fa fa-edit"></i> </button> 
                                 </form>
                                 
                             <td style="width :5%">
                               <form class="form-horizontal" method="get" action="/rentmanager/users/">
                               <input type="hidden" name="id" value="${rents.id}" >
                               <button class="btn btn-primary disabled" type="submit"><i class="fa fa-play"></i></button> 
                                 </form>
                                </td>
                                
                                     <td style="width :5%">
                                 <form class="form-horizontal" method="post" action="/rentmanager/rents">
                                    <input type="hidden" name="id" value="${rents.id}" >
                                    <button class="btn btn-danger " type="submit"><i class="fa fa-trash"></i></i></button>
                                 </form>
                                </td>
                                    </tr>
                                </c:forEach>
<%--                                 <tr>
                                    <td>1.</td>
                                    <td>Renault Clio</td>
                                    <td>John Doe</td>
                                    <td>10/01/2019</td>
                                    <td>13/01/2019</td>
                                    <td>
                                        <a class="btn btn-primary disabled" href="${pageContext.request.contextPath}/cars?id=1">
                                            <i class="fa fa-play"></i>
                                        </a>
                                        <a class="btn btn-success disabled" href="#">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-danger disabled" href="#">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr> --%>

<%--                                 <tr>
                                    <td>2.</td>
                                    <td>Citroen C2</td>
                                    <td>Jane Doe</td>
                                    <td>10/01/2019</td>
                                    <td>13/01/2019</td>
                                    <td>
                                        <a class="btn btn-primary disabled" href="${pageContext.request.contextPath}/cars?id=2">
                                            <i class="fa fa-play"></i>
                                        </a>
                                        <a class="btn btn-success disabled" href="#">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-danger disabled" href="#">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr> --%>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
