<%@ page import="Projet.Model.Etudiant" %>
<%@ page import="Projet.Model.Groupe" %>
<%@ page import="Projet.Model.Module" %>
<%@ page import="Projet.Model.Absence" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jolan
  Date: 19/01/2021
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>

<script type='text/javascript'>
    // Description des urls de demande ajax, ces urls vont servir dans les fichiers editAbsence.js et editNote.js
    const urlUpdateAbsence = "<%=application.getContextPath()%>/absence/update";
    const urlDeleteAbsence = "<%=application.getContextPath()%>/absence/delete";
    const urlEditNote = "<%=application.getContextPath()%>/note/editNote";
    const urlCreateNote = "<%=application.getContextPath()%>/note/createNote";
    const urlDeleteNote = "<%=application.getContextPath()%>/note/deleteNote"
</script>
<script type='text/javascript' src="<%=application.getContextPath()%>/Public/javascript/editAbsence.js"
        charset="UTF-8"></script>
<script type='text/javascript' src="<%=application.getContextPath()%>/Public/javascript/editNote.js"
        charset="UTF-8"></script>


<!-- AFFICHAGE DES DETAILS DE L ETUDIANT -->

<% Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");%>
<div class="container">
    <div class="row justify-content-center">
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="<%=application.getContextPath()%>/Public/img/pp.jpeg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title"><%= etudiant.getNom()%> <%= etudiant.getPrenom() %></h5>
                <p class="card-text">
                    <% if (etudiant.getGroupe() != null) { %>
                        <%=etudiant.getGroupe().getNom()%>
                    <% } %>
                </p>
            </div>
        </div>




        <!-- table des absences -->


        <% List<Absence> absences = etudiant.getAbsences();%>
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Absence</th>
                <th scope="col">Début</th>
                <th scope="col">Fin</th>
                <th scope="col">Justifié</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%  int ind= 0;
                for(Absence absence : absences) {%>
            <tr>
                <th scope="row">#</th>
                <td class="cellDebut"><%=absence.getDébut()%></td>
                <td class="cellFin"><%=absence.getFin()%></td>
                <td class="cellJustifie"><% if(absence.isJustifié()) { %>oui<% } else { %>non<% } %></td>
                <td><button class="btn btn-danger" onClick="deleteAbsence(<%=absence.getId()%>, this, <%=ind%>)">supprimer</button><button onClick="updateAbsence(<%=absence.getId()%>, this)" class="btn btn-pimary">Modifier</button></td>
            </tr>
            <%ind++; } %>
            <tr>
                <th scope="row">Nouveau :</th>
                <form method="post" action="<%= application.getContextPath()%>/absence/create">
                    <td><input type="datetime-local" name="dateDebut" placeholder="Date de début" required/></td>
                    <td><input type="datetime-local" name="dateFin" placeholder="Date de fin" required/></td>
                    <td><input type="checkbox" name="justifie" value="true"/></td>
                    <input type="hidden" name="idEtudiant" value="<%= etudiant.getId()%>"/>
                    <td><input type="submit" class="btn btn-success" value="Ajouter"/></td>
                </form>
            </tr>
            </tbody>
        </table>

        <!-- Fin de la table des absences -->

        <!-- Table des notes -->

        <% if (etudiant.getGroupe() != null) { %>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Module</th>
                        <th scope="col">Note</th>
                    </tr>
                </thead>
                <tbody>

                    <% Groupe groupe = etudiant.getGroupe();
                    for(Module module : etudiant.getGroupe().getModules()) {%>
                <tr>
                    <td><%=module.getNom()%></td>
                    <%if (etudiant.getNoteByModule(module) != null) {%>
                        <td class="note" id="<%=etudiant.getId()%><%=module.getId()%>" onClick="editNote(<%=etudiant.getId()%>,<%=module.getId()%>)"><%=etudiant.getNoteByModule(module).getValeur()%></td>
                    <%} else {%>
                        <td class="note" id="<%=etudiant.getId()%><%=module.getId()%>" onClick="editNote(<%=etudiant.getId()%>,<%=module.getId()%>)"></td>
                    <%}%>
                </tr>
                    <%}} else {%>
                <tr>
                    <td>L'étudiant n'a pas de groupes</td>
                </tr>
                <% } %>
                </tbody>
            </table>
    </div>
</div>
