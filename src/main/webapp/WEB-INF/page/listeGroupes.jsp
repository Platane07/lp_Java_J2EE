<%@ page import="Projet.Model.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page import="Projet.Model.Groupe" %><%--
  Created by IntelliJ IDEA.
  User: jolan
  Date: 19/01/2021
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="row justify-content-center">
        <h1>LISTE DES GROUPES</h1>
    </div>
</div>


<!-- Table qui affiche tous les groupes présents dans la base de données -->
<div class="container">
    <div class="row justify-content-center">
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Nom du groupe</th>
                    <th scope="col">Nombre d'élèves</th>
                    <th scope="col">Fiche</th>
                </tr>
                </thead>
                <tbody>
                <% List<Groupe> listeGroupe = (List<Groupe>) request.getAttribute("groupes");
                    for(Groupe groupe : listeGroupe){%>
                <tr>
                    <td><%= groupe.getNom()%></td>
                    <td><%= groupe.getEtudiants().size()%></td>
                    <td><a href="<%= application.getContextPath()%>/do/groupe?id=<%=groupe.getId()%>">Voir les étudiants</a></td>
                </tr>
            <a href="<%= application.getContextPath()%>/do/groupe"></a>
                <% } %>
                </tbody>
        </table>
    </div>
</div>