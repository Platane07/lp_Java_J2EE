<%@ page import="Projet.Model.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page import="Projet.Model.Groupe" %>
<%@ page import="Projet.Model.Module" %>
<%@ page import="java.util.HashSet" %><%--
  Created by IntelliJ IDEA.
  User: jolan
  Date: 19/01/2021
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="adminContent" class="java.lang.String" scope="request"/>

<style>

    .itemMenu {
        transition: transform .1s;
    }
    .itemMenu:hover {
        transform: scale(1.5);
    }

</style>
<!-- PETIT MENU PROPRE A L ADMIN QUI VIENT SE RAJOUTER À LA NAVBAR DE L'INDEX -->
<script type='text/javascript' src="<%=application.getContextPath()%>/Public/javascript/admin.js"
        charset="UTF-8"></script>

<div class="container">
    <div class="row justify-content-center">
        <h1>Espace Administrateur</h1>
    </div>
    <div class="row justify-content-center">
        <a href="<%= application.getContextPath()%>/admin/groupe">
            <div class="card text-white bg-primary itemMenu">
                <div class="card-header">GROUPES</div>
            </div>
        </a>

        <a href="<%= application.getContextPath()%>/admin/etudiant">
            <div class="card text-white bg-primary itemMenu">
                <div class="card-header">ETUDIANTS</div>
            </div>
        </a>

        <a href="<%= application.getContextPath()%>/admin/module">
            <div class="card text-white bg-primary itemMenu">
                <div class="card-header">MODULES</div>
            </div>
        </a>
    </div>
</div>

<jsp:include page="<%=adminContent%>"/>