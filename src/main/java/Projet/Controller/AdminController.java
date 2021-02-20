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


    // INIT
    public void init() throws ServletException {

        urlGroupe = getServletConfig().getInitParameter("urlGroupe");
        urlEtudiant = getServletConfig().getInitParameter("urlEtudiant");
        urlModule = getServletConfig().getInitParameter("urlModule");
        urlAdmin = getServletConfig().getInitParameter("urlAdmin");
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

        // EtudiantDAO etudiant = new EtudiantDAO();
        //List<Etudiant> listEtudiants = new ArrayList<>();

        //listEtudiants = EtudiantDAO.getAll();

        //request.setAttribute("etudiants", listEtudiants);

        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward( request, response );
    }

    // /////////////////////// affichage de la liste des étudiants et leurs notes dans un groupe
    //
    private void doGroupe(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        Groupe groupe = GroupeDAO.getById(Integer.parseInt(request.getParameter("id")));

        List<Module> modules = groupe.getModules();

        System.out.println(groupe);
        List<Etudiant> etudiants = groupe.getEtudiants();

        // Inclusion du content dans le template
        request.setAttribute("modules", modules);
        request.setAttribute("groupe", groupe);
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("content", urlGroupe);
        loadJSP(urlAdmin, request, response);
    }


    private void doModule(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        Groupe groupe = GroupeDAO.getById(Integer.parseInt(request.getParameter("id")));

        List<Module> modules = groupe.getModules();

        System.out.println(groupe);
        List<Etudiant> etudiants = groupe.getEtudiants();

        // Inclusion du content dans le template
        request.setAttribute("modules", modules);
        request.setAttribute("groupe", groupe);
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("content", urlModule);
        loadJSP(urlAdmin, request, response);
    }



    // Affichage de la page de la liste des étudiants
    private void doEtudiant(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {


        List<Etudiant> etudiants = EtudiantDAO.getAll();

        // Inclusion du content dans le template
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("content", urlEtudiant);
        loadJSP(urlAdmin, request, response);
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
