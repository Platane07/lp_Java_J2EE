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
            <table class="table table-bordered table-secondary">

            <!-- formulaire d'ajout d'un groupe -->
            <!-- on ne peut sélectionner que les étudiants n'ayant pas encore de groupes (c'est possible)-->
            <tr>
                <th scope="row">Nouveau :</th>
                <form method="post" name="addGroupe" action="<%= application.getContextPath()%>/groupe/create">
                    <td><input type="text" name="nomGroupe" placeholder="Nom du groupe" autocomplete="off" pattern="[A-Za-z0-9]{1,30}" required/></td>
                    <td><select name="modules" class="form-select" multiple><% for(Module module: modules) {%><option value="<%=module.getId()%>" onClick="populateSelect(this)"><%= module.getNom()%></option><% } %></select><select name="modulesAdded" multiple></select></td>
                    <td><select name="etudiants" class="form-select" multiple><% for(Etudiant etudiant: etudiants) {%><option value="<%=etudiant.getId()%>" onClick="populateSelect(this)"><%= etudiant.getNom()%> <%=etudiant.getPrenom()%></option><% } %></select><select name="etudiantsAdded" multiple></select></td>
                    <td><input type="submit" class="btn btn-success" value="Ajouter"/></td>
                </form>
            </tr>

            <!-- Liste des groupes existants -->

            <h1>LISTE DES GROUPES </h1>
            <thead class="thead-dark">
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
                <td class="text-center"><h2><%=groupe.getId()%></h2></td>
                <td class="text-center"><h2><%=groupe.getNom()%></h2></td>



                <!-- LISTE DES MODULES EFFECTUES PAR LE GROUPE -->


                <td>
                    <table class="table table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col" colspan="2" class="text-center">Modules dans le groupe</th>
                            </tr>
                        </thead>
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
                                <td><input class="btn btn-success" type="submit"/></td>
                            </form>
                        </tr>
                    </table>
                </td>



                <!-- LISTE DES ETUDIANTS DANS LE GROUPE -->



                <td>
                    <table class="table table-bordered">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col" colspan="2" class="text-center">Etudiants du groupe</th>
                        </tr>
                        </thead>
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
                                            for(Etudiant etudiant: etudiants) {%>
                                        <option value="<%=etudiant.getId()%>"><%=etudiant.getNom()%> <%=etudiant.getPrenom()%></option>
                                        <% } %>
                                    </select>
                                    <input type="hidden" name="groupe" value="<%=groupe.getId()%>"/>
                                </td>
                                <td><input class="btn btn-success" type="submit"/></td>
                            </form>
                        </tr>
                    </table>




                </td>
                <td>
                    <button class="btn btn-danger" onClick="deleteGroupe(<%=groupe.getId()%>, this)">supprimer</button>
                </td>
            </tr>
            <% } %>

            </tbody>
        </table>
    </div>
</div>