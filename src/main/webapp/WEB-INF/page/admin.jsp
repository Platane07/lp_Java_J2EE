<%@ page import="Projet.Model.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page import="Projet.Model.Groupe" %>
<%@ page import="Projet.Model.Module" %><%--
  Created by IntelliJ IDEA.
  User: jolan
  Date: 19/01/2021
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type='text/javascript' src="<%=application.getContextPath()%>/Public/javascript/admin.js"
        charset="UTF-8"></script>
<div class="container">
    <div class="row justify-content-center">
        <h1>Espace Administrateur</h1>
    </div>
</div>

<%  List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
    List<Groupe> groupes = (List<Groupe>) request.getAttribute("groupes");
    List<Module> modules = (List<Module>) request.getAttribute("modules");%>
<div class="container">
    <div class="row justify-content-center">
    <table class="table table-striped table-dark">

            <h1>LISTE DES GROUPES </h1>
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Nom</th>
                <th scope="col">Modules</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
                <%  int ind= 0;
                        for(Groupe groupe : groupes) {%>
            <tr>
                <th scope="row">#</th>
                <td><%=groupe.getId()%></td>
                <td><%=groupe.getNom()%></td>
                <td>Modules</td>
                <td><button onClick="deleteGroupe(<%=groupe.getId()%>, this, <%=ind%>)">supprimer</button></td>
            </tr>
        <%ind++; } %>
                <th scope="row">Nouveau :</th>
                <form method="post" name="addGroupe" action="<%= application.getContextPath()%>/groupe/create">
                    <td><input type="text" name="nomGroupe" placeholder="Nom du groupe" required/></td>
                    <td><select name="modules" multiple>
                        <% for(Module module: modules) {%>
                        <option value="<%=module.getId()%>" onClick="populateSelect(this)"><%= module.getNom()%></option>
                        <% } %>
                    </select>
                    <select name="modulesAdded" multiple>
                    </select></td>
                    <td><input type="submit" value="Ajouter"/></td>
                </form>
            </tbody>
    </table>

    <table class="table table-striped table-dark">

        <h1>LISTE DES MODULES </h1>
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Nom</th>
            <th scope="col">Groupes</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <%  int ind1=0;
            for(Module module : modules) {%>
        <tr>
            <td><%=module.getId()%></td>
            <td><%=module.getNom()%></td>
            <td>Groupes</td>
            <td><button onClick="deleteModule(<%=module.getId()%>, this, <%=ind%>)">supprimer</button></td>
        </tr>
        <%ind1++; } %>
        <tr>
            <th scope="row">Nouveau :</th>
            <form method="post" action="<%= application.getContextPath()%>/groupe/create">
                <td><input type="text" name="nomModule" placeholder="Nom du module" required/></td>
                <td><select id="groupe" multiple>
                    <% for(Groupe groupe: groupes) {%>
                    <option value="<%=groupe.getId()%>" onClick="populateSelect(this)"><%=groupe.getNom()%></option>
                    <% } %>
                </select></td>
                <td><input type="submit" value="Ajouter"/></td>
            </form>
        </tr>
        </tbody>
    </table>

    <table class="table table-striped table-dark">

        <h1>LISTE DES ETUDIANTS </h1>
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Nom</th>
            <th scope="col">Modules</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <%  int ind2= 0;
            for(Etudiant etudiant : etudiants) {%>
        <tr>
            <td><%=etudiant.getId()%></td>
            <td><%=etudiant.getNom()%></td>
            <td>Quel Groupe ?</td>
            <td><button onClick="deleteEtudiant(<%=etudiant.getId()%>, this, <%=ind%>)">supprimer</button></td>
        </tr>
        <%ind2++; } %>
        </tbody>
    </table>
    </div>
</div>