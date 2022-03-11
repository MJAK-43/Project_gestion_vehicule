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
                Voitures
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/vehicles/create">Ajouter</a> 
                </br></br>
                
                
            </h1>
        </section>


       <form style="float:right" class="form-horizontal" method="post" action="/rentmanager/vehicles/search">
       <input   type="text"  id="search" name="search" placeholder=" Rechercher un vehicule" >
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
                                    <th style="width: 10px">#</th>
                                    <th style="width :23%">Marque</th>
                                    <th style="width :23%">Modele</th>
                                    <th style="width :23%">Nombre de places</th>
                                    <!--<th>Propriétaire</th>-->
                                    <th>Action</th>
                                </tr>
                                

                                  <tr>
								   <c:forEach items="${ listvehicles }" var="vehicle">
                                    <td>${vehicle.id}</td>
                                    <td>${vehicle.constructor}</td>
                                    <td>${vehicle.model}</td>
                                    <td>${vehicle.numPlace}</td>
                                    <!--<td>John Doe</td>-->
                                    
                                
                                
                                
                                    <td style="width :5%">
                              <form class="form-horizontal" method="get" action="/rentmanager/vehicles/editer">
                               <input type="hidden" name="id" value="${vehicle.id}" >
                               <button class="btn btn-success" type="submit">  <i class="fa fa-edit"></i> </button> 
                                 </form>
                                 
                             <td style="width :5%">
                               <form class="form-horizontal" method="get" action="/rentmanager/users/">
                               <input type="hidden" name="id" value="${vehicle.id}" >
                               <button class="btn btn-primary disabled" type="submit"><i class="fa fa-play"></i></button> 
                                 </form>
                                </td>
                                
                                     <td style="width :5%">
                                 <form class="form-horizontal" method="post" action="/rentmanager/vehicles/delete">
                                    <input type="hidden" name="id" value="${vehicle.id}" >
                                    <button class="btn btn-danger " type="submit"><i class="fa fa-trash"></i></i></button>
                                 </form>
                                </td>
                                    
                               <%--  <td>
                               <form class="form-horizontal" method="get" action="/rentmanager/vehicles/editer">
                               <input type="hidden" name="id" value="${vehicle.id}" >
                               <button class="btn btn-success" type="submit"><i class="fa fa-edit"></i></button> 
                                 </form>
                                </td>  
                                
                                 <td>
                               <form class="form-horizontal" method="get" action="/rentmanager/users/">
                               <input type="hidden" name="id" value="${vehicle.id}" >
                               <button class="btn btn-primary disabled" type="submit"><i class="fa fa-play"></i></button> 
                                 </form>
                                </td>
                                
                                <td>
                                 <form class="form-horizontal" method="post" action="/rentmanager/vehicles/delete">
                                    <input type="hidden" name="id" value="${vehicle.id}" >
                                    <button class="btn btn-danger " type="submit"><i class="fa fa-trash"></i></i></button>
                                 </form>
                                </td>
                                 --%>
                                
                             
                                    
                                 <!--    
                                    <td>
                                        <a class="btn btn-primary disabled" href="car-detail.html">
                                            <i class="fa fa-play"></i>
                                        </a>
                                        <a class="btn btn-success disabled" href="#">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-danger disabled" href="#">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td> -->
                                </tr>
                                </c:forEach> 
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
