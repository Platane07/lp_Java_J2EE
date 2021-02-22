package Projet.Controller;

import Projet.Model.Module;
import Projet.Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class Index extends HttpServlet {


    // URL
    private String urlIndex;
    private String urlListeGroupes;
    private String urlGroupe;
    private String urlEtudiant;
    private String urlListeEtudiants;
    private String urlAdmin;
    private String urlHome;


    // INIT
    public void init() throws ServletException {
        urlIndex = getServletConfig().getInitParameter("index");
        urlListeGroupes = getServletConfig().getInitParameter("urlListeGroupes");
        urlGroupe = getServletConfig().getInitParameter("urlGroupe");
        urlListeEtudiants = getServletConfig().getInitParameter("urlListeEtudiants");
        urlEtudiant = getServletConfig().getInitParameter("urlEtudiant");
        urlAdmin = getServletConfig().getInitParameter("urlAdmin");
        urlHome = getServletConfig().getInitParameter("urlHome");
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
        if (action == null) {
            action = "/home";
        }

        if (action.equals("/home")) {
            doHome(request, response);
        }

        if (action.equals("/admin")) {
            doAdmin(request, response);
        }

        if (action.equals("/listeGroupes")) {
            doListeGroupes(request, response);
        }
        if (action.equals("/groupe")) {
            doGroupe(request, response);
        }

        if (action.equals("/listeEtudiants")) {
            doListeEtudiants(request, response);
        }

        if (action.equals("/etudiant")) {
            doEtudiant(request, response);
        }

        //this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward( request, response );
        loadJSP(urlIndex, request, response);
    }

    private void doHome(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("content", urlHome);
        loadJSP(urlIndex, request, response);
    }

    // /////////////////////// affichage de la liste des groupes
    //
    private void doListeGroupes(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        try {
            Collection<Groupe> listeGroupes = GroupeDAO.getAll();
            // Inclusion du content dans le template
            request.setAttribute("groupes", listeGroupes);
            request.setAttribute("content", urlListeGroupes);
            loadJSP(urlIndex, request, response);
        } catch (Exception e) {
            log("erreur dans le rendu de la liste des étudiants");
            loadJSP(urlIndex, request, response);
        }
    }

    // /////////////////////// affichage de la liste des étudiants et leurs notes dans un groupe
    //
    private void doGroupe(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        try {
            Groupe groupe = GroupeDAO.getById(Integer.parseInt(request.getParameter("id")));
            List<Module> modules = groupe.getModules();

            System.out.println(groupe);
            List<Etudiant> etudiants = groupe.getEtudiants();

            // Inclusion du content dans le template
            request.setAttribute("modules", modules);
            request.setAttribute("groupe", groupe);
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("content", urlGroupe);
            loadJSP(urlIndex, request, response);
        } catch (Exception e) {
            log("erreur dans le rendu de la liste des étudiants");
            loadJSP(urlIndex, request, response);
        }
    }


    private void doEtudiant(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {

        try {
            if (EtudiantDAO.getById(Integer.parseInt(request.getParameter("id"))) != null) {
                Etudiant etudiant = EtudiantDAO.getById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("etudiant", etudiant);
                request.setAttribute("content", urlEtudiant);
                loadJSP(urlIndex, request, response);
            } else {
                log("erreur l'étudiant n'existe pas");
            }
        } catch (Exception e) {
            log("erreur dans le rendu de l'étudiant");
            loadJSP(urlIndex, request, response);
        }
    }


    // Affichage de la page de la liste des étudiants
    private void doListeEtudiants(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Etudiant> etudiants = EtudiantDAO.getAll();
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("content", urlListeEtudiants);
            loadJSP(urlIndex, request, response);
        } catch (Exception e) {
            log("erreur dans le rendu de la liste des étudiants");
            loadJSP(urlIndex, request, response);
        }
        // Inclusion du content dans le template

    }

    private void doAdmin(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {


        try {
            List<Etudiant> etudiants = EtudiantDAO.getAll();
            List<Groupe> groupes = GroupeDAO.getAll();
            List<Module> modules = ModuleDAO.getAll();
            // Inclusion du content dans le template
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("groupes", groupes);
            request.setAttribute("modules", modules);
            request.setAttribute("content", urlAdmin);
            loadJSP(urlIndex, request, response);
        } catch (Exception e) {
            log("erreur dans le rendu de la page admin");
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

    @Override
    public void destroy() {
        super.destroy();

        // Fermeture de la factory
        GestionFactory.close();
    }
}
