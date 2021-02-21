package Projet.Controller;

import javax.mvc.View;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Projet.Model.*;
import Projet.Model.Module;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class EtudiantController extends HttpServlet {

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

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");

        try {
            if (request.getParameter("etudiantGroupe") != null) {
                int idGroupe = Integer.parseInt(request.getParameter("etudiantGroupe"));
                Groupe groupe = GroupeDAO.getById(idGroupe);
                Etudiant etudiant = EtudiantDAO.create(nom, prenom, groupe);
            } else {
                Etudiant etudiant = EtudiantDAO.create(nom, prenom, null);
            }

        } catch (Exception e) {
            log("impossible de créer un étudiant");
        }

        response.sendRedirect(request.getContextPath() + "/admin/etudiant");

    }

    private void doDeleteEtudiant(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            EtudiantDAO.delete(id);

            ServletContext sc = getServletContext();
            System.out.println(sc.getContextPath());
        } catch (Exception e) {
            log("impossible de supprimer un étudiant");
        }

        response.sendRedirect(request.getContextPath() + "/admin/etudiant");

    }

    private boolean isXMLHttpRequest(HttpServletRequest request) {
        String test = request.getHeader("x-requested-with");
        return request.getHeader("x-requested-with").equals("XMLHttpRequest");
    }


}