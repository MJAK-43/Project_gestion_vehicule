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
                Utilisateurs
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/users/create">Ajouter</a>
                <br><br>
            </h1>
            
        </section>

        
        
       <form style="float:right" class="form-horizontal" method="post" action="/rentmanager/users/search">
       <input   type="text"  id="search" name="search" placeholder=" Rechercher un client" >
       <button type="submit" class="btn btn-primary">Chercher</button>
       </form>
        <!-- -
         <section class="content-header">
            <h1>
                Utilisateurs
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/users/create">Ajouter</a>
            </h1>
            
        </section>
        
        <div>
            
        
          <% out.print("\t\t\t\t");%> <form class="reh" action="searchclient" method="get">
                <input class="form-control" id="search" style="width: 250px; heigth:60px" type="search" name="search" placeholder=" Rechercher un client"><span><button style="background-color: blue" class="gj-button" type="submit">Rechercher</button></span>
                  </div>
        
        
          <form action="chercher.do" method="get">
        <label>Mot Clé</label>
        <input type="text" name="motCle" value="${model.motCle}"/>
        <button type="submit" class="btn btn-primary">Chercher</button>
      </form>    
-->
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body no-padding">
                            <table class="table table-striped">
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th>Nom</th>
                                    <th>Prenom</th>
                                    <th>Email</th>
                                    <th>Date de naissance</th>
                                </tr>
                                <c:forEach items="${ listUsers }" var="user">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.name}</td>
                                        <td>${user.lastname}</td>
                                        <td>${user.email}</td>
                                        <td>${user.birthDate}</td>
                                        
                                        
                               <!-- supprimer un client -->   
                                         
                               <%--  <td>
                                 <form class="form-horizontal" method="post" action="/rentmanager/home">
                                    <input type="hidden" name="id" value="${user.id}" >
                                    <input type="submit"  class="btn btn-info pull-right" value="Delete" title="Supprimer" >
                                 </form>
                                </td> --%>
                                
                                             
                           
                             <%-- 
                                <td>
                                   <a class="btn btn-primary" style="background-color: blue" href="${pageContext.request.contextPath}/users/editer?id=${user.id}">Editer</a>
                                </td> --%>
                                
                               <td style="width :5%">
                              <form class="form-horizontal" method="get" action="/rentmanager/users/editer">
                               <input type="hidden" name="id" value="${user.id}" >
                               <button class="btn btn-success" type="submit">  <i class="fa fa-edit"></i> </button> 
                                 </form>
                                 
                            <%--  <td style="width :5%">
                               <form class="form-horizontal" method="get" action="/rentmanager/users/">
                               <input type="hidden" name="id" value="${user.id}" >
                               <button class="btn btn-primary " type="submit"><i class="fa fa-play"></i></button> 
                                 </form>
                                </td> --%>
                                
                                                             <td style="width :5%">
                               <form class="form-horizontal" method="get" action="/rentmanager/users/details">
                               <input type="hidden" name="id" value="${user.id}" >
                               <button class="btn btn-primary " type="submit"><i class="fa fa-play"></i></button> 
                                 </form>
                                </td>
                                
                                
                                     <td style="width :5%">
                                 <form class="form-horizontal" method="post" action="/rentmanager/home">
                                    <input type="hidden" name="id" value="${user.id}" >
                                    <button class="btn btn-danger " type="submit"><i class="fa fa-trash"></i></i></button>
                                 </form>
                                </td>
                                
                          
                               <%--  <td>
                              <form class="form-horizontal" method="get" action="/rentmanager/users/editer">
                               <input type="hidden" name="id" value="${user.id}" >
                               <input type="submit" class="btn btn-info pull-right" value="Editer" title="Edit" >
                                 </form>
                                </td> 
                                 --%>
                                
                               
                                
                                
                               
                                
                                <!--  <td>
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
                         
                               <!--  <button type="submit" class="btn btn-info pull-right"
                                         <td>
                                         <a class="btn btn-primary" href="${pageContext.request.contextPath}/users/list?id=${user.id}">Supprimer</a>
                                        <a class="btn btn-success disabled" href="#">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                       
                                    </td>
                                    
                                    -->
                                    
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
