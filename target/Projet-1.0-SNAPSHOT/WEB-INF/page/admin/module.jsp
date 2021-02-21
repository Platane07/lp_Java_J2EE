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

<div class="container">
    <div class="row justify-content-center">
        <table class="table table-bordered table-secondary">
            <h1>LISTE DES MODULES </h1>
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="text-center">Id</th>
                <th scope="col" class="text-center">Nom</th>
                <th scope="col" class="text-center">Groupes</th>
                <th scope="col" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                for(Module module : modules) {%>
            <tr>
                <td class="text-center"><h2><%=module.getId()%></h2></td>
                <td class="text-center"><h2><%=module.getNom()%></h2></td>
                <td>


                    <!-- Tableau des groupes associés au module -->

                    <table class="table table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col" colspan="2" class="text-center">Groupes comportant le module</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(Groupe groupe : module.getGroupes()) { %>
                            <tr>
                                <td class="text-center"><%=groupe.getNom()%></td>
                                <td><button onClick="deleteGroupeOfModule(<%=groupe.getId()%>, <%=module.getId()%>, this)">supprimer</button></td>
                            </tr>
                            <% } %>
                            <tr>

                                <form method="post" name="addGroupeInModule" action="<%=application.getContextPath()%>/module/addGroupe">
                                    <td>
                                        <select name="groupe" class="form-select">
                                            <%
                                                List<Groupe> moduleGroupesUnused = new ArrayList<>(groupes);
                                                moduleGroupesUnused.removeAll(module.getGroupes());
                                                for(Groupe groupe: moduleGroupesUnused) {%>
                                            <option value="<%=groupe.getId()%>"><%=groupe.getNom()%></option>
                                            <% } %>
                                        </select>
                                        <input type="hidden" name="module" value="<%=module.getId()%>"/>
                                    </td>
                                    <td><input type="submit" class="btn btn-success"/></td>
                                </form>
                            </tr>
                        </tbody>
                    </table>

                    <!-- Fin du tableau des groupes associés au module -->


                </td>
                <td><button class="btn btn-danger" onClick="deleteModule(<%=module.getId()%>, this)">supprimer</button></td>
            </tr>
            <% } %>
            <tr>
                <th scope="row">Nouveau :</th>
                <form method="post" action="<%= application.getContextPath()%>/module/create">
                    <td><input type="text" name="nomModule" placeholder="Nom du module" autocomplete="off" pattern="[A-Za-z0-9]{1,30}" required/></td>
                    <td><select class="form-controle" name="groupe" class="form-select" multiple><% for(Groupe groupe: groupes) {%><option value="<%=groupe.getId()%>" onClick="populateSelect(this)"><%=groupe.getNom()%></option><% } %></select class="form-control"><select name="groupesAdded" multiple></select></td>
                    <td><input type="submit" value="Ajouter" class="btn btn-success"/></td>
                </form>
            </tr>
            </tbody>
        </table>
    </div>
</div>