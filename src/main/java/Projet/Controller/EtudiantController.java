package Projet.Controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Projet.Model.*;
import com.google.gson.Gson;


public class EtudiantController extends HttpServlet {

    public void init() throws ServletException {
        GestionFactory.open();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();

        // Exécution action
        if (action.equals("/create")) {
            doCreateEtudiant(request, response);
        }
        // Exécution action
        if (action.equals("/delete") && isXMLHttpRequest(request)) {
            doDeleteEtudiant(request, response);
        }
    }

    private void doCreateEtudiant(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        try {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            if (request.getParameter("etudiantGroupe") != null) {
                int idGroupe = Integer.parseInt(request.getParameter("etudiantGroupe"));
                Groupe groupe = GroupeDAO.getById(idGroupe);
                Etudiant etudiant = EtudiantDAO.create(nom, prenom, groupe);
            } else {
                Etudiant etudiant = EtudiantDAO.create(nom, prenom, null);
            }
            response.sendRedirect(request.getContextPath() + "/admin/etudiant");
        } catch (Exception e) {
            log("impossible de créer un étudiant");
            response.sendRedirect(request.getContextPath() + "/admin/etudiant");
        }
    }

    private void doDeleteEtudiant(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            EtudiantDAO.delete(id);
            response.getWriter().write(new Gson().toJson("{AbsenceUpdated : " + id + "}"));
        } catch (Exception e) {
            log("impossible de supprimer un étudiant");
            response.getWriter().write("erreur");
        }
    }

    private boolean isXMLHttpRequest(HttpServletRequest request) {
        String test = request.getHeader("x-requested-with");
        return request.getHeader("x-requested-with").equals("XMLHttpRequest");
    }

    @Override
    public void destroy() {
        super.destroy();

        // Fermeture de la factory
        GestionFactory.close();
    }


}