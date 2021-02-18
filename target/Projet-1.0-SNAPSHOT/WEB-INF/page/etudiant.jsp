<%@ page import="Projet.Model.Etudiant" %>
<%@ page import="Projet.Model.Groupe" %>
<%@ page import="Projet.Model.Module" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jolan
  Date: 19/01/2021
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");%>
<div class="container">
    <div class="row justify-content-center">
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="<%=application.getContextPath()%>/Public/img/pp.jpeg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title"><%= etudiant.getNom()%> <%= etudiant.getPrenom() %></h5>
                <p class="card-text"><%= etudiant.getGroupe().getNom()%></p>
            </div>
        </div>
        <!-- table des absences -->
        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">First</th>
                <th scope="col">Last</th>
                <th scope="col">Handle</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<table class="table">
    allloifopfidfiop
    <thead>
    <tr>
        <th scope="col">Nom de l'étudiant</th>
    </tr>
    </thead>
</table>
