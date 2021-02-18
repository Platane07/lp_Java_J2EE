<%@ page import="Projet.Model.Etudiant" %>
<%@ page import="Projet.Model.Groupe" %>
<%@ page import="Projet.Model.Module" %>
<%@ page import="Projet.Model.Absence" %>

<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jolan
  Date: 19/01/2021
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>

<script type='text/javascript'>
    // Description des urls de demande ajax
    const urlUpdateAbsence = "<%=application.getContextPath()%>/ajax/updateAbsence";
    const urlCreateAbsence = "<%=application.getContextPath()%>/ajax/createAbsence";
    const urlDeleteAbsence = "<%=application.getContextPath()%>/ajax/deleteAbsence"
</script>
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
        <% List<Absence> absences = etudiant.getAbsences();%>


        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th scope="col">Absence</th>
                <th scope="col">Début</th>
                <th scope="col">Fin</th>
                <th scope="col">Justifié</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%  int ind= 1;
                for(Absence absence : absences) {%>
            <tr>
                <th scope="row">1</th>
                <td><%=absence.getDébut()%></td>
                <td><%=absence.getFin()%></td>
                <td>
                <% if(absence.isJustifié()) { %>
                    oui
                <% } else { %>
                    non
                <% } %>
                </td>
            </tr>
            <% } %>
            <tr>
                <form method="post" action="<%= application.getContextPath()%>/absence/create">
                    <td><input type="datetime-local" name="dateDebut" placeholder="Date de début" required/></td>
                    <td><input type="datetime-local" name="dateFin" placeholder="Date de fin" required/></td>
                    <td>Justifié : <input type="checkbox" name="justifie"/></td>
                    <input type="hidden" name="idEtudiant" value="<%= etudiant.getId()%>"/>
                    <td><input type="submit" value="Ajouter"></td>
                </form>
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
