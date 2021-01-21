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

<div class="container">
    <div class="row justify-content-center">
            <% Groupe groupe = (Groupe) request.getAttribute("groupe");%>
        <h1>GROUPE : <%= groupe.getNom()%></h1>
    </div>
</div>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Nom de l'étudiant</th>
        <% for(Module module : groupe.getModules()){%>
        <th scope="col"><%=module.getNom()%></th>
        <%}%>
    </tr>
    </thead>
    <tbody>
    <% List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
        for(Etudiant etu : etudiants){%>
    <tr>
        <td><%= etu.getNom()%></td>
         <% for(Module module : groupe.getModules()){ %>
            <%if (etu.getNoteByModule(module) != null) {%>
                <td><%=etu.getNoteByModule(module).getValeur()%></td>
            <%} else {%>
                <td></td>
            <%}%>
        <%}%>
    </tr>
    <% } %>
    </tbody>
</table>
