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

public class GroupeController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();

        // Exécution action
        if (action.equals("/create")) {
            doCreateGroupe(request, response);
        }
        // Exécution action
        if (action.equals("/delete")) {
            doDeleteGroupe(request, response);
        }
    }

    private void doCreateGroupe(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException {

        String nom = request.getParameter("nomGroupe");
        log(nom);
        String[] idModules = request.getParameterValues("modulesAdded");
        List<Module> modules = new ArrayList<>();
        if (idModules != null) {
            for (String id : idModules) {
                modules.add(ModuleDAO.getById(Integer.parseInt(id)));
            }
        }

        Groupe groupe = GroupeDAO.create(nom, modules);

        ServletContext sc = getServletContext();
        System.out.println(sc.getContextPath());

        response.sendRedirect(request.getContextPath() + "/do/admin");

    }

    private void doDeleteGroupe(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        log("delete");

        Groupe groupe = GroupeDAO.delete(Integer.parseInt(id));

        ServletContext sc = getServletContext();
        System.out.println(sc.getContextPath());

        response.sendRedirect(request.getContextPath() + "/do/admin");

    }


}