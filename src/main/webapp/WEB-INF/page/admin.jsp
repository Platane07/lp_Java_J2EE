<%@ page import="Projet.Model.Etudiant" %>
<%@ page import="Projet.Model.Groupe" %>
<%@ page import="Projet.Model.Module" %>
<%@ page import="Projet.Model.Absence" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.<%--
  Created by IntelliJ IDEA.
  User: jolan
  Date: 19/01/2021
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="row justify-content-center">
        <h1>TOUS LES ETUDIANTS</h1>
    </div>
</div>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Nom de l'élève</th>
        <th scope="col">Groupe</th>
        <th scope="col">Moyenne générale</th>
    </tr>
    </thead>
    <tbody>
    <% List<Etudiant> listeEtudiants = (List<Etudiant>) request.getAttribute("etudiants");
        for(Etudiant etudiant : listeEtudiants){%>
    <tr>
        <td><%= etudiant.getNom()%></td>
        <td><a href="<%= application.getContextPath()%>/do/groupe?id=<%=etudiant.getGroupe().getId()%>"><%=etudiant.getGroupe().getNom()%></a></td>
        <td><%= etudiant.getMoyenne()%></td>
        <td><a href="<%= application.getContextPath()%>/do/etudiant?id=<%=etudiant.getId()%>">Détails</a></td>
    </tr>
    <a href="<%= application.getContextPath()%>/do/groupe"></a>
    <% } %>
    </tbody>
</table>