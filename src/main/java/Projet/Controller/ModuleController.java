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
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class ModuleController extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();

        // Exécution action
        if (action.equals("/create")) {
            doCreateodule(request, response);
        }
    }

    private void doCreateodule(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        String nom = request.getParameter("nomModule");
        log(nom);
        String[] idGroupes = request.getParameterValues("groupesAdded");

        if (idGroupes == null) {
            log("nuuuuuuuulmll");
        }
        List<Groupe> groupes = new ArrayList<>();
        for(String id : idGroupes){
            groupes.add(GroupeDAO.getById(Integer.parseInt(id)));
        }

        Module module = ModuleDAO.create(nom, groupes);

        request.setAttribute("content", "page/admin.jsp");
        ServletContext sc = getServletContext();
        System.out.println(sc.getContextPath());

        request.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

    }


}