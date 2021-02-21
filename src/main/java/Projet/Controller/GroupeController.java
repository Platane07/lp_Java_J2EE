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
import com.google.gson.Gson;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class GroupeController extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();

        // Exécution action
        if (action.equals("/create")) {
            doCreateGroupe(request, response);
        }
        //On différencie visuellement les requêtes passant par ajax à l'aide de la fonction isXMLHttpRequest (ajout d'une sécurité en plus)
        if (action.equals("/delete") && isXMLHttpRequest(request)) {
            doDeleteGroupe(request, response);
        }
        if (action.equals("/deleteModule") && isXMLHttpRequest(request)) {
            doDeleteModule(request, response);
        }
        if (action.equals("/deleteEtudiant") && isXMLHttpRequest(request)) {
            doDeleteEtudiant(request, response);
        }
        if (action.equals("/addModule")) {
            doAddModule(request, response);
        }
        if (action.equals("/addEtudiant")) {
            doAddEtudiant(request, response);
        }
    }

    private void doCreateGroupe(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException {
        
        try {
            if (request.getParameter("nomGroupe") != null) {
                String nom = request.getParameter("nomGroupe");
                String[] idModules = request.getParameterValues("modulesAdded");
                List<Module> modules = new ArrayList<>();
                if (idModules != null) {
                    for (String id : idModules) {
                        modules.add(ModuleDAO.getById(Integer.parseInt(id)));
                    }
                }
                GroupeDAO.create(nom, modules);
                ServletContext sc = getServletContext();
                System.out.println(sc.getContextPath());

                response.sendRedirect(request.getContextPath() + "/admin/groupe");
            }
        } catch (Exception e) {
            log("erreur lors de la création d'un groupe");
            response.sendRedirect(request.getContextPath() + "/admin/groupe");
        }

    }
    //Suppression d'un groupe entier à l'aide d'ajax
    private void doDeleteGroupe(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String id = request.getParameter("id");
            GroupeDAO.delete(Integer.parseInt(id));
            response.getWriter().write(new Gson().toJson("{ id : " + id + "}"));
        } catch(Exception e) {
            log("erreur lors de la suppression du groupe");
            response.getWriter().write("error");

        }

    }

    //Suppression d'un etudiant dans un groupe à l'aide d'ajax
    private void doDeleteEtudiant(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));
            int idGroupe = Integer.parseInt(request.getParameter("idGroupe"));
            GroupeDAO.deleteEtudiant(idEtudiant, idGroupe);
            response.getWriter().write(new Gson().toJson("{ id : " + idEtudiant + "}"));
        } catch (Exception e) {
            log("erreur lors de la suppresion d'un étudiant d'un groupe");
            response.getWriter().write("erreur");
        }

    }

    private void doDeleteModule(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int idModule = Integer.parseInt(request.getParameter("idModule"));
            int idGroupe = Integer.parseInt(request.getParameter("idGroupe"));
            GroupeDAO.deleteModule(idModule, idGroupe);
            response.getWriter().write(new Gson().toJson("{ id : " + idModule + "}"));
        } catch (Exception e) {
            log("erreur lors de la suppression d'un module d'un groupe");
            response.getWriter().write("erreur");
        }

    }

    private void doAddModule(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {


        //Ajout d'un module dans un groupe
        try {
            if (request.getParameter("module") != null) {
                int idModule = Integer.parseInt(request.getParameter("module"));
                int idGroupe = Integer.parseInt(request.getParameter("groupe"));
                GroupeDAO.addModule(idModule, idGroupe);
            }
        } catch (Exception e) {
            log("erreur lors de l'ajout d'un module");
        }
        response.sendRedirect(request.getContextPath() + "/admin/groupe");



    }

    //Ajoute un étudiant dans un groupe,
    //Données récupérées : id d'un étudiant et d'un groupe
    private void doAddEtudiant(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {

        try {
            if (request.getParameter("etudiant") != null) {
                int idEtudiant = Integer.parseInt(request.getParameter("etudiant"));
                int idGroupe = Integer.parseInt(request.getParameter("groupe"));
                GroupeDAO.addEtudiant(idEtudiant, idGroupe);
            }
        } catch (Exception e) {
            log("erreur lors de l'ajout d'un étudiant");
        }
        response.sendRedirect(request.getContextPath() + "/admin/groupe");

    }

    private boolean isXMLHttpRequest(HttpServletRequest request) {
        String test = request.getHeader("x-requested-with");
        return request.getHeader("x-requested-with").equals("XMLHttpRequest");
    }


}