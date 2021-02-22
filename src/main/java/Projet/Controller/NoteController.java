package Projet.Controller;

import Projet.Model.*;
import Projet.Model.Note;
import Projet.Model.NoteDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoteController extends HttpServlet {


    public void init() throws ServletException {
        GestionFactory.open();
    }
    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On vérifie dans un premier temps si la demande est une requête ajax (XMLHttpRequest)
        if (isXMLHttpRequest(request)) {

            // On récupère le path
            String action = request.getPathInfo();

            // Exécution action
            if (action.equals("/editNote")) {
                doEditNote(request, response);
            }
            if (action.equals("/createNote")) {
                doCreateNote(request, response);
            }
            if (action.equals("/deleteNote")) {
                doDeleteNote(request, response);
            }

        } else {

            // Bad request
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    // ///////////////////////
    //
    private void doEditNote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            // Récupération d'une donnée envoyé par le client
            int idModule = Integer.parseInt(request.getParameter("idModule"));
            int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));
            float value = Float.parseFloat(request.getParameter("value"));

            if (value <= 20 && value >= 0) {
                Note note = NoteDAO.getByEtudiantAndModule(idEtudiant, idModule);
                note.setValeur(value);
                NoteDAO.update(note);
                response.getWriter().write(new Gson().toJson("{ value : " + value + "}"));
            }

        } catch (Exception e) {
            log("erreur lors de la modification de la note");
            response.getWriter().write("erreur");
        }
    }

    private void doCreateNote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idModule = Integer.parseInt(request.getParameter("idModule"));
            int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));
            float value = Float.parseFloat(request.getParameter("value"));

            if (value <= 20 && value >= 0) {
                Note note = NoteDAO.create(value, idEtudiant, idModule);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson("{ value : " + value + "}"));
            }
        } catch (Exception e) {
            log("erreur lors de la création de la note");
            response.getWriter().write("erreur");
        }
    }

    private void doDeleteNote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int idModule = Integer.parseInt(request.getParameter("idModule"));
            int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));

            NoteDAO.deleteById(idEtudiant, idModule);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson("{ value : success }"));
        } catch (Exception e) {
            log("erreur lors de la suppression de la note");
        }
    }


    //
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
