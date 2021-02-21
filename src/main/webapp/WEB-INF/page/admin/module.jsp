<%@ page import="Projet.Model.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page import="Projet.Model.Groupe" %>
<%@ page import="Projet.Model.Module" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: jolan
  Date: 19/01/2021
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>

<script type="text/javascript">
    const urlDeleteGroupeOfModule = "<%=application.getContextPath()%>/module/deleteGroupe";
    const urlDeleteModule = "<%=application.getContextPath()%>/module/delete";
</script>

<%  List<Groupe> groupes = (List<Groupe>) request.getAttribute("groupes");
    List<Module> modules = (List<Module>) request.getAttribute("modules");%>

<table class="table table-striped table-dark table-bordered">
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
    <%
        for(Module module : modules) {%>
    <tr>
        <td><%=module.getId()%></td>
        <td><%=module.getNom()%></td>
        <td>


            <!-- liste des groupes -->

            <table class="table table-striped table-dark table-bordered">
                <% for(Groupe groupe : module.getGroupes()) { %>
                <tr>
                    <td><%=groupe.getNom()%></td>
                    <td><button onClick="deleteGroupeOfModule(<%=groupe.getId()%>, <%=module.getId()%>, this)">supprimer</button></td>
                </tr>
                <% } %>
                <tr>

                    <form method="post" name="addGroupeInModule" action="<%=application.getContextPath()%>/module/addGroupe">
                        <td>
                            <select name="groupe">
                                <%
                                    List<Groupe> moduleGroupesUnused = new ArrayList<>(groupes);
                                    moduleGroupesUnused.removeAll(module.getGroupes());
                                    for(Groupe groupe: moduleGroupesUnused) {%>
                                <option value="<%=groupe.getId()%>"><%=groupe.getNom()%></option>
                                <% } %>
                            </select>
                            <input type="hidden" name="module" value="<%=module.getId()%>"/>
                        </td>
                        <td><input type="submit"/></td>
                    </form>
                </tr>
            </table>
        </td>
        <td><button onClick="deleteModule(<%=module.getId()%>, this)">supprimer</button></td>
    </tr>
    <% } %>
    <tr>
        <th scope="row">Nouveau :</th>
        <form method="post" action="<%= application.getContextPath()%>/module/create">
            <td><input type="text" name="nomModule" placeholder="Nom du module" autocomplete="off" required/></td>
            <td><select name="groupe" class="form-select" multiple><% for(Groupe groupe: groupes) {%><option value="<%=groupe.getId()%>" onClick="populateSelect(this)"><%=groupe.getNom()%></option><% } %></select><select name="groupesAdded" multiple></select></td>
            <td><input type="submit" value="Ajouter"/></td>
        </form>
    </tr>
    </tbody>
</table>