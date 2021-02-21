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
        <h1>TOUS LES ETUDIANTS</h1>
    </div>
</div>
</br>
<div class="container">
    <div class="row justify-content-center">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Nom de l'élève</th>
                <th scope="col">Groupe</th>
                <th scope="col">Moyenne générale</th>
                <th scope="col">Nombre d'absences</th>
                <th scope="col">Fiche</th>
            </tr>
            </thead>
            <tbody>
            <% List<Etudiant> listeEtudiants = (List<Etudiant>) request.getAttribute("etudiants");
                for(Etudiant etudiant : listeEtudiants){%>
            <tr>
                <td><%= etudiant.getNom()%></td>
                <td>
                    <% if (etudiant.getGroupe() != null) { %>
                    <a href="<%= application.getContextPath()%>/do/groupe?id=<%=etudiant.getGroupe().getId()%>"><%=etudiant.getGroupe().getNom()%></a>
                    <%  } %>
                </td>
                <td><%= etudiant.getMoyenne()%></td>
                <td><%= etudiant.getAbsences().size()%></td>
                <td><a href="<%= application.getContextPath()%>/do/etudiant?id=<%=etudiant.getId()%>"><button type="button" class="btn btn-primary">Détails</button></a></td>
            </tr>
            <a href="<%= application.getContextPath()%>/do/groupe"></a>
            <% } %>
            </tbody>
        </table>
    </div>
</div>