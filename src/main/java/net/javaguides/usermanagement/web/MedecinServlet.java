package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import net.javaguides.usermanagement.model.Medecin;
import net.javaguides.usermanagement.dao.MedecinDAO;

@WebServlet("/medecin/*")
public class MedecinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MedecinDAO medecinDao;

    public void init() {
        medecinDao = new MedecinDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo(); // Récupère l'URL après /medecin/

        if (action == null || action.equals("/")) {
            action = "/list";  // Redirige vers la liste si aucune action spécifiée
        }

        try {
            switch (action) {
                case "/list":
                    listMedecins(request, response);
                    break;
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertMedecin(request, response);
                    break;
                case "/delete":
                    deleteMedecin(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateMedecin(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non reconnue");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMedecins(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Medecin> listMedecins = medecinDao.getAllMedecins();
        request.setAttribute("listMedecins", listMedecins);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/medecin-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/medecin-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String codemed = request.getParameter("codemed");
        if (codemed == null || codemed.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/medecin/list");
            return;
        }

        Medecin existingMedecin = medecinDao.getMedecin(codemed);
        if (existingMedecin == null) {
            response.sendRedirect(request.getContextPath() + "/medecin/list");
            return;
        }

        request.setAttribute("medecin", existingMedecin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/medecin-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMedecin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nom = request.getParameter("nom").trim();
        String prenom = request.getParameter("prenom").trim();
        String grade = request.getParameter("grade").trim();

        if (nom.isEmpty() || prenom.isEmpty() || grade.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/medecin/new?error=empty_fields");
            return;
        }

        Medecin newMedecin = new Medecin(nom, prenom, grade);
        medecinDao.saveMedecin(newMedecin);
        response.sendRedirect(request.getContextPath() + "/medecin/list");
    }

    private void updateMedecin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String codemed = request.getParameter("codemed");
        if (codemed == null || codemed.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/medecin/list");
            return;
        }

        String nom = request.getParameter("nom").trim();
        String prenom = request.getParameter("prenom").trim();
        String grade = request.getParameter("grade").trim();

        if (nom.isEmpty() || prenom.isEmpty() || grade.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/medecin/edit?codemed=" + codemed + "&error=empty_fields");
            return;
        }

        Medecin medecin = new Medecin(codemed, nom, prenom, grade);
        medecinDao.updateMedecin(medecin);
        response.sendRedirect(request.getContextPath() + "/medecin/list");
    }

    private void deleteMedecin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String codemed = request.getParameter("codemed");
        if (codemed == null || codemed.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/medecin/list");
            return;
        }

        medecinDao.deleteMedecin(codemed);
        response.sendRedirect(request.getContextPath() + "/medecin/list");
    }
}
