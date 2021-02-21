package Projet.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import Projet.Model.*;
import Projet.Model.Module;

public class AdminController extends HttpServlet {


    // URL
    private String urlEtudiant;
    private String urlGroupe;
    private String urlModule;
    private String urlAdmin;
    private String urlIndex;


    // INIT
    public void init() throws ServletException {

        urlGroupe = getServletConfig().getInitParameter("urlGroupe");
        urlEtudiant = getServletConfig().getInitParameter("urlEtudiant");
        urlModule = getServletConfig().getInitParameter("urlModule");
        urlAdmin = getServletConfig().getInitParameter("urlAdmin");
        urlIndex = getServletConfig().getInitParameter("urlIndex");
        GestionFactory.open();

    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // on passe la main au GET
        doGet(request, response);
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getPathInfo();
        if (action == null){
            action = "/";
        }

        if (action.equals("/etudiant")){
            doEtudiant(request, response);
        }

        if(action.equals("/groupe")){
            doGroupe(request, response);
        }
        if(action.equals("/module")){
            doModule(request, response);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/page/admin.jsp").forward( request, response );
    }

    // /////////////////////// affichage de l'espace de gestion des groupes
    //
    private void doGroupe(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        try {
            List<Groupe> groupes = GroupeDAO.getAll();
            List<Module> modules = ModuleDAO.getAll();
            List<Etudiant> etudiants = EtudiantDAO.getEtudiantsWithoutGroupe();

            request.setAttribute("groupes", groupes);
            request.setAttribute("modules", modules);
            request.setAttribute("etudiants", etudiants);
            // Inclusion de l'affichage des groupes dans le template
            request.setAttribute("adminContent", urlGroupe);
        } catch (Exception e) {
            log("impossible de récupérer les groupes");
            loadJSP(urlIndex, request, response);

        }

        request.setAttribute("content", urlAdmin);
        loadJSP(urlIndex, request, response);
    }

    // /////////////////////// affichage de l'espace de gestion des modules
    //
    private void doModule(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        try {

            List<Groupe> groupes = GroupeDAO.getAll();
            List<Module> modules = ModuleDAO.getAll();


            // affichage des modules dans le template
            request.setAttribute("groupes", groupes);
            request.setAttribute("modules", modules);
            request.setAttribute("adminContent", urlModule);
            request.setAttribute("content", urlAdmin);
            loadJSP(urlIndex, request, response);

        } catch (Exception e) {
            log("impossible de récupérer les modules");
            request.setAttribute("content", urlAdmin);
            loadJSP(urlIndex, request, response);
        }

    }



    // Affichage de la page de la liste des étudiants
    private void doEtudiant(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {


        try {
            List<Etudiant> etudiants = EtudiantDAO.getAll();
            List<Groupe> groupes = GroupeDAO.getAll();

            // Affichage des modules dans le template
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("groupes", groupes);
            request.setAttribute("content", urlAdmin);
            request.setAttribute("adminContent", urlEtudiant);
            loadJSP(urlIndex, request, response);
        } catch (Exception e) {
            log("impossible de récupérer les modules");
            request.setAttribute("content", urlAdmin);
            loadJSP(urlIndex, request, response);
        }

    }
    /**
     * Charge la JSP indiquée en paramètre
     *
     * @param url
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loadJSP(String url, HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        // L'interface RequestDispatcher permet de transférer le contrôle à une
        // autre servlet
        // Deux méthodes possibles :
        // - forward() : donne le contrôle à une autre servlet. Annule le flux
        // de sortie de la servlet courante
        // - include() : inclus dynamiquement une autre servlet
        // + le contrôle est donné à une autre servlet puis revient à la servlet
        // courante (sorte d'appel de fonction).
        // + Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        System.out.println(sc.getContextPath());
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
