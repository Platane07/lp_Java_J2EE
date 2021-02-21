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
    const urlDeleteGroupe = "<%=application.getContextPath()%>/groupe/delete";
    const urlDeleteModuleOfGroupe = "<%=application.getContextPath()%>/groupe/deleteModule";
    const urlDeleteEtudiantOfGroupe = "<%=application.getContextPath()%>/groupe/deleteEtudiant";
</script>


<%
    List<Groupe> groupes = (List<Groupe>) request.getAttribute("groupes");
    List<Module> modules = (List<Module>) request.getAttribute("modules");
    List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
%>
<div class="container">
    <div class="row justify-content-center">
        <table class="table table-striped table-dark table-bordered">

            <h1>LISTE DES GROUPES </h1>
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Nom</th>
                <th scope="col">Modules</th>
                <th scope="col">Etudiants</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for(Groupe groupe : groupes) {%>
            <tr>
                <td><%=groupe.getId()%></td>
                <td><%=groupe.getNom()%></td>

                <!-- LISTE DES MODULES -->


                <td>
                    <table class="table table-striped table-dark table-bordered">
                        <% for(Module module : groupe.getModules()) { %>
                        <tr>
                            <td><%=module.getNom()%></td>
                            <td><button onClick="deleteModuleOfGroupe(<%=module.getId()%>, <%=groupe.getId()%>, this)">supprimer</button></td>
                        </tr>
                        <% } %>
                        <tr>

                            <form method="post" name="addModuleInGroupe" action="<%=application.getContextPath()%>/groupe/addModule">
                                <td>
                                    <select name="module">
                                        <%
                                            List<Module> groupeModulesUnused = new ArrayList<>(modules);
                                            groupeModulesUnused.removeAll(groupe.getModules());
                                            for(Module module: groupeModulesUnused) {%>sd
                                        <option value="<%=module.getId()%>"><%=module.getNom()%></option>
                                        <% } %>
                                    </select>
                                    <input type="hidden" name="groupe" value="<%=groupe.getId()%>"/>
                                </td>
                                <td><input type="submit"/></td>
                            </form>
                        </tr>
                    </table>
                </td>



                <!-- LISTE DES ETUDIANTS -->



                <td>
                    <table class="table table-striped table-dark table-bordered">
                        <% for(Etudiant etudiant : groupe.getEtudiants()) { %>
                        <tr>
                            <td><%=etudiant.getNom()%> <%=etudiant.getPrenom()%></td>
                            <td><button onClick="deleteEtudiantOfGroupe(<%=etudiant.getId()%>, <%=groupe.getId()%>, this)">supprimer</button></td>
                        </tr>
                        <% } %>
                        <tr>

                            <form method="post" name="addEtudiantInGroupe" action="<%=application.getContextPath()%>/groupe/addEtudiant">
                                <td>
                                    <select name="etudiant">
                                        <%
                                            for(Etudiant etudiant: etudiants) {%>sd
                                        <option value="<%=etudiant.getId()%>"><%=etudiant.getNom()%></option>
                                        <% } %>
                                    </select>
                                    <input type="hidden" name="groupe" value="<%=groupe.getId()%>"/>
                                </td>
                                <td><input type="submit"/></td>
                            </form>
                        </tr>
                    </table>




                </td>
                <td>
                    <button onClick="deleteGroupe(<%=groupe.getId()%>, this)">supprimer</button>
                </td>
            </tr>
            <% } %>
            <tr>
                <th scope="row">Nouveau :</th>
                <form method="post" name="addGroupe" action="<%= application.getContextPath()%>/groupe/create">
                    <td><input type="text" name="nomGroupe" placeholder="Nom du groupe" autocomplete="off" required/></td>
                    <td><select name="modules" class="form-select" multiple><% for(Module module: modules) {%><option value="<%=module.getId()%>" onClick="populateSelect(this)"><%= module.getNom()%></option><% } %></select><select name="modulesAdded" multiple></select></td>
                    <td><input type="submit" value="Ajouter"/></td>
                </form>
            </tr>
            </tbody>
        </table>
    </div>
</div>