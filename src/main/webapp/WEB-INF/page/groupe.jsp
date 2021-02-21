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
<script type='text/javascript'>
    // Description des urls de demande ajax
        const urlEditNote = "<%=application.getContextPath()%>/note/editNote";
        const urlCreateNote = "<%=application.getContextPath()%>/note/createNote";
        const urlDeleteNote = "<%=application.getContextPath()%>/note/deleteNote"
</script>
<script type='text/javascript' src="<%=application.getContextPath()%>/Public/javascript/editNote.js"
        charset="UTF-8">
</script>
<div class="container">
    <div class="row justify-content-center">
            <% Groupe groupe = (Groupe) request.getAttribute("groupe");%>
        <h1>GROUPE : <%= groupe.getNom()%></h1>
    </div>
</div>
<div class="container">
    <div class="row justify-content-center">
        <table class="table table-bordered">
            <thead class="thead-dark">
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
                        <td id="<%=etu.getId()%><%=module.getId()%>" onClick="editNote(<%=etu.getId()%>,<%=module.getId()%>)"><%=etu.getNoteByModule(module).getValeur()%></td>
                    <%} else {%>
                        <td id="<%=etu.getId()%><%=module.getId()%>" onClick="editNote(<%=etu.getId()%>,<%=module.getId()%>)"></td>
                    <%}%>
                <%}%>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
