package Projet.Controller;

import Projet.Model.Note;
import Projet.Model.NoteDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class NoteController extends HttpServlet {

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
            if (action.equals("/deleteNote")){
                doDeleteNote(request, response);
            }

        } else {

            log("allo");
            // Bad request
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    // ///////////////////////
    //
    private void doEditNote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération d'une donnée envoyé par le client
        int idModule = Integer.parseInt(request.getParameter("idModule"));
        int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));
        float value = Float.parseFloat(request.getParameter("value"));

        Note note = NoteDAO.getByEtudiantAndModule(idEtudiant, idModule);
        note.setValeur(value);
        Note noteUpdated = NoteDAO.update(note);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("success");
    }

    private void doCreateNote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idModule = Integer.parseInt(request.getParameter("idModule"));
        int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));
        float value = Float.parseFloat(request.getParameter("value"));

        Note note = NoteDAO.create(value, idEtudiant, idModule);

        String json = new Gson().toJson(note);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void doDeleteNote(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idModule = Integer.parseInt(request.getParameter("idModule"));
        int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));

        NoteDAO.deleteById(idEtudiant, idModule);
        // Retourne le résultat sous forme JSON

        // Retourne le résultat sous forme JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("success");
    }


    //
    private boolean isXMLHttpRequest(HttpServletRequest request) {
        String test = request.getHeader("x-requested-with");
        return request.getHeader("x-requested-with").equals("XMLHttpRequest");
    }

}
