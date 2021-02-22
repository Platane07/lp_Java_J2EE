package Projet.Controller;

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
import java.util.Collection;
import java.util.Date;
import java.util.List;

import Projet.Model.*;
import Projet.Model.Module;
import com.google.gson.Gson;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class AbsenceController extends HttpServlet {

    public void init() throws ServletException {
        GestionFactory.open();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();

        // Exécution action
        if (action.equals("/update")) {
            doUpdateAbsence(request, response);
        }
        if (action.equals("/create")) {
            doCreateAbsence(request, response);
        }
        if (action.equals("/delete")) {
            doDeleteAbsence(request, response);
        }
    }

    private void doCreateAbsence(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException {


        try {
            //Sécurité, si l'id est bien envoyé et si c'est bien un int alors...
            if (request.getParameter("idEtudiant") != null) {
                if (tryParse(request.getParameter("idEtudiant")) != null) {

                    //Récupération des données
                    int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));
                    LocalDateTime debut = LocalDateTime.parse(request.getParameter("dateDebut"));
                    LocalDateTime fin = LocalDateTime.parse(request.getParameter("dateFin"));
                    //Vérification si la fin de l'absence est bien supérieure au début
                    if (debut.isBefore(fin)) {
                        boolean justifie = false;
                        if (request.getParameter("justifie") != null) {
                            if (request.getParameter("justifie").equals("true")) {
                                justifie = true;
                            }
                        }

                        //Création de l'absence
                        AbsenceDAO.create(debut, fin, justifie, idEtudiant);
                        response.sendRedirect(request.getContextPath() + "/do/etudiant?id=" + idEtudiant);
                    }
                    //Else inutiles mais on sait jamais
                    else {
                        response.sendRedirect(request.getContextPath() + "/do/etudiant?id=" + idEtudiant);
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/do/listeEtudiants");
                }
            }
        } catch (Exception e) {
            log("Impossible de créer une absence");
            response.sendRedirect(request.getContextPath() + "/do/listeEtudiants");
        }
    }

    private void doDeleteAbsence(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException {

        //Les delete de l'application sont tous réalisés avec ajax
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            if(tryParse(request.getParameter("idAbsence")) != null) {
                int idAbsence = Integer.parseInt(request.getParameter("idAbsence"));
                //Suppression de l'absence
                AbsenceDAO.delete(idAbsence);

                //Renvoi de l'id de l'absence
                response.getWriter().write(new Gson().toJson("{idAbsence : " + idAbsence + "}"));
            }
        } catch (Exception e) {
            response.getWriter().write("error");
        }

    }

    private void doUpdateAbsence(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException {

        //Sécurité, cette fonction reprend la même base que la création de l'absence
        if (request.getParameter("idAbsence") != null) {
            if (tryParse(request.getParameter("idAbsence")) != null) {
                try {
                    int idAbsence = Integer.parseInt(request.getParameter("idAbsence"));

                    LocalDateTime debut = LocalDateTime.parse(request.getParameter("debut"));
                    LocalDateTime fin = LocalDateTime.parse(request.getParameter("fin"));
                    if (debut.isBefore(fin)) {
                        log("allo" + request.getParameter("justifie"));
                        boolean justifie = false;
                        if (request.getParameter("justifie") != null) {
                            if (request.getParameter("justifie").equals("true")) {
                                justifie = true;
                            }
                        }
                        Absence absence = AbsenceDAO.getById(idAbsence);
                        absence.setDébut(debut);
                        absence.setFin(fin);
                        absence.setJustifié(justifie);
                        AbsenceDAO.update(absence);

                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        //Renvoi de l'id de l'absence si celle-ci a bien été modifiée
                        response.getWriter().write(new Gson().toJson("{AbsenceUpdated : " + idAbsence + "}"));
                    }
                } catch (Exception e) {
                    log("erreur lors de la modification de l'absence");
                    response.getWriter().write("error");
                }
            }
        }

    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        // Fermeture de la factory
        GestionFactory.close();
    }

}