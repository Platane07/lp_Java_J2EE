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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import Projet.Model.*;
import Projet.Model.Module;
import com.google.gson.Gson;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class ModuleController extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();

        // Exécution action
        if (action.equals("/create")) {
            doCreateModule(request, response);
        }
        if (action.equals("/delete") && isXMLHttpRequest(request)) {
            doDeleteModule(request, response);
        }
        if (action.equals("/deleteGroupe") && isXMLHttpRequest(request)) {
            doDeleteGroupe(request, response);
        }
        if (action.equals("/addGroupe")) {
            doAddGroupe(request, response);
        }

    }

    private void doCreateModule(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        try {
            String nom = request.getParameter("nomModule");
            String[] idGroupes = request.getParameterValues("groupesAdded");

            List<Groupe> groupes = new ArrayList<>();
            if (idGroupes != null) {
                for (String id : idGroupes) {
                    groupes.add(GroupeDAO.getById(Integer.parseInt(id)));
                }
            }
            ModuleDAO.create(nom, groupes);
        } catch (Exception e) {
            log("erreur lors de la création d'un module");
        }

        response.sendRedirect(request.getContextPath() + "/admin/module");

    }

    private void doDeleteModule(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String id = request.getParameter("id");
            Module module = ModuleDAO.delete(Integer.parseInt(id));
            ServletContext sc = getServletContext();
            System.out.println(sc.getContextPath());
            response.getWriter().write(new Gson().toJson("{ id : "+id+"}"));
        } catch (Exception e) {
            log("erreur lors de la suppression du module");
            response.getWriter().write("erreur");
        }

    }

    private void doDeleteGroupe(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        try {
            int idModule = Integer.parseInt(request.getParameter("idModule"));
            int idGroupe = Integer.parseInt(request.getParameter("idGroupe"));
            ModuleDAO.deleteGroupe(idModule, idGroupe);
            response.getWriter().write(new Gson().toJson("{ id : " + idGroupe + "}"));
        } catch (Exception e) {
            log("erreur lors de la suppression du module");
            response.getWriter().write("erreur");
        }

    }

    private void doAddGroupe(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {


        try {
            if (request.getParameter("groupe") != null && request.getParameter("module") != null) {
                int idModule = Integer.parseInt(request.getParameter("module"));
                int idGroupe = Integer.parseInt(request.getParameter("groupe"));
                ModuleDAO.addGroupe(idGroupe, idModule);
            }
        } catch (Exception e) {
            log("erreur lors de la suppression du groupe");
        }
        response.sendRedirect(request.getContextPath() + "/admin/module");
    }

    private boolean isXMLHttpRequest(HttpServletRequest request) {
        String test = request.getHeader("x-requested-with");
        return request.getHeader("x-requested-with").equals("XMLHttpRequest");
    }

}