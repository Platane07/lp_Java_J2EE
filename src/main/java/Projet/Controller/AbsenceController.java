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
import java.util.Collection;
import java.util.Date;
import java.util.List;

import Projet.Model.*;
import Projet.Model.Module;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class AbsenceController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On vérifie dans un premier temps si la demande est une requête ajax (XMLHttpRequest)

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
           /* if (action.equals("/update")) {
                doUpdateAbsence(request, response);
            }
            if (action.equals("/delete")){
                doDeleteAbsence(request, response);
            }*/
    }

    private void doCreateAbsence(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        LocalDateTime debut = LocalDateTime.parse(request.getParameter("dateDebut"));
        LocalDateTime fin = LocalDateTime.parse(request.getParameter("dateFin"));
        Boolean justifie = false;
        if (request.getParameter("justifie") != null) {
            if (request.getParameter("justifie").equals("true")) {
                justifie = true;
            } else {
                justifie = false;
            }
        }
        int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));

        Absence absence = AbsenceDAO.create(debut, fin, justifie, idEtudiant);


        Etudiant etudiant = EtudiantDAO.getById(idEtudiant);
        request.setAttribute("etudiant", etudiant);
        request.setAttribute("content", "page/etudiant.jsp");
        ServletContext sc = getServletContext();
        System.out.println(sc.getContextPath());

        request.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

    }

    private void doDeleteAbsence(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException {
        int idAbsence = Integer.parseInt(request.getParameter("idAbsence"));

        AbsenceDAO.delete(idAbsence);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("success");
    }

    private void doUpdateAbsence(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException {
        int idAbsence = Integer.parseInt(request.getParameter("idAbsence"));

        LocalDateTime debut = LocalDateTime.parse(request.getParameter("debut"));
        LocalDateTime fin = LocalDateTime.parse(request.getParameter("fin"));
        log("allo"+request.getParameter("justifie"));
        Boolean justifie = false;
        if (request.getParameter("justifie") != null) {
            if (request.getParameter("justifie").equals("true")) {
                justifie = true;
            } else {
                justifie = false;
            }
        }

        Absence absence = AbsenceDAO.getById(idAbsence);
        absence.setDébut(debut);
        absence.setFin(fin);
        absence.setJustifié(justifie);

        log("MAMADOU");

        AbsenceDAO.update(absence);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("success");
    }
}