<%--
  Created by IntelliJ IDEA.
  User: jolan
  Date: 21/02/2021
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- hall d'accueil -->
<div class="container">
    <div class="row justify-content-center">
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="<%=application.getContextPath()%>/Public/img/groupe.jpg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Liste des groupes d'étudiants</h5>
                <p class="card-text">Vous permet de voir la liste des groupes, les étudiants de chaque groupe et d'ajouter des notes et de consulter leurs statistiques.</p>
                <a href="<%= application.getContextPath()%>/do/listeGroupes" class="btn btn-primary">Naviguer</a>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="<%=application.getContextPath()%>/Public/img/etudiant.jpg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Liste de tous les étudiants</h5>
                <p class="card-text">Consultez la totalité des étudiants, gérez leurs notes et leurs absences.</p>
                <a href="<%= application.getContextPath()%>/do/listeEtudiants" class="btn btn-primary">Naviguer</a>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="<%=application.getContextPath()%>/Public/img/admin.jpg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Espace administrateur</h5>
                <p class="card-text">Espace dédié à l'administrateur. Il peut ajouter, supprimer et modifier les groupes, les modules et les étudiants.</p>
                <a href="<%= application.getContextPath()%>/do/admin" class="btn btn-primary">Naviguer</a>
            </div>
        </div>
    </div>
</div>
