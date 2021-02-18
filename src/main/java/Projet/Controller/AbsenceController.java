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
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class AbsenceController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On vérifie dans un premier temps si la demande est une requête ajax (XMLHttpRequest)

            // On récupère le path
            String action = request.getPathInfo();

            // Exécution action
            if (action.equals("/create")) {
                doCreateAbsence(request, response);
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
        Boolean justifie = Boolean.parseBoolean(request.getParameter("justifie"));
        int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));

        Absence absence = AbsenceDAO.create(debut, fin, justifie, idEtudiant);


        ServletContext sc = getServletContext();
        System.out.println(sc.getContextPath());
        response.sendRedirect("etudiant.jsp");

    }
}