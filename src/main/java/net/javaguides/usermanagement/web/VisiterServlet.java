package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import net.javaguides.usermanagement.model.Visiter;
import net.javaguides.usermanagement.model.Medecin;
import net.javaguides.usermanagement.model.Patient;
import net.javaguides.usermanagement.dao.VisiterDAO;
import net.javaguides.usermanagement.dao.MedecinDAO;
import net.javaguides.usermanagement.dao.PatientDAO;

@WebServlet("/visiter/*")
public class VisiterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VisiterDAO visiterDao;
    private MedecinDAO medecinDao;
    private PatientDAO patientDao;

    public void init() {
        visiterDao = new VisiterDAO();
        medecinDao = new MedecinDAO();
        patientDao = new PatientDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo(); // Récupère l'URL après /visiter/
        if (action == null || action.equals("/")) {
            action = "/list";  // Redirige vers la liste si aucune action spécifiée
        }
        try {
            switch (action) {
                case "/list":
                    listVisiters(request, response);
                    break;
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertVisiter(request, response);
                    break;
                case "/delete":
                    deleteVisiter(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateVisiter(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non reconnue");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listVisiters(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Visiter> listVisiters = visiterDao.getAllVisiters();
        request.setAttribute("listVisiters", listVisiters);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/visiter-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/visiter-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String codevisiterStr = request.getParameter("codevisiter");
        if (codevisiterStr == null || codevisiterStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/visiter/list");
            return;
        }
        int codevisiter = Integer.parseInt(codevisiterStr);
        Visiter existingVisiter = visiterDao.getVisiter(codevisiter);
        if (existingVisiter == null) {
            response.sendRedirect(request.getContextPath() + "/visiter/list");
            return;
        }
        request.setAttribute("visiter", existingVisiter);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/visiter-form.jsp");
        dispatcher.forward(request, response);
    }

   private void insertVisiter(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    String codemed = request.getParameter("codemed");
    String codepal = request.getParameter("codepal");
    String datesr = request.getParameter("date");

    if (codemed == null || codepal == null || datesr == null ||
        codemed.isEmpty() || codepal.isEmpty() || datesr.isEmpty()) {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h2>Erreur : Données manquantes.</h2>");
        return;
    }
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(datesr);

        // Récupération des objets associés pour vérifier leur existence
        Medecin medecin = medecinDao.getMedecin(codemed);
        Patient patient = patientDao.getPatient(codepal);
        if (medecin == null || patient == null) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Erreur : Vérifier le code médecin ou le code patient.</h2>");
            return;
        }
        Visiter newVisiter = new Visiter(medecin, patient, date);
        visiterDao.saveVisiter(newVisiter);
        response.sendRedirect(request.getContextPath() + "/visiter/list");
    } catch (ParseException e) {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h2>Erreur : Date invalide.</h2>");
    }
}

   private void updateVisiter(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    String codevisiterStr = request.getParameter("codevisiter");
    String codemed = request.getParameter("codemed");
    String codepal = request.getParameter("codepal");
    String datesr = request.getParameter("date");

    if (codevisiterStr == null || codemed == null || codepal == null || datesr == null ||
        codevisiterStr.isEmpty() || codemed.isEmpty() || codepal.isEmpty() || datesr.isEmpty()) {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h2>Erreur : Données manquantes.</h2>");
        return;
    }
    try {
        int codevisiter = Integer.parseInt(codevisiterStr);
        // Récupération des objets associés via leurs DAO respectifs
        Medecin medecin = medecinDao.getMedecin(codemed);
        Patient patient = patientDao.getPatient(codepal);
        if (medecin == null || patient == null) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Erreur : Vérifier le code médecin ou le code patient.</h2>");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(datesr);

        // Création d'un objet Visiter avec les associations
        Visiter visiter = new Visiter(codevisiter, medecin, patient, date);
        visiterDao.updateVisiter(visiter);
        response.sendRedirect(request.getContextPath() + "/visiter/list");
    } catch (NumberFormatException e) {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h2>Erreur : Numéro invalide.</h2>");
    } catch (ParseException e) {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h2>Erreur : Date invalide.</h2>");
    }
}

    private void deleteVisiter(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String codevisiterStr = request.getParameter("codevisiter");
        if (codevisiterStr == null || codevisiterStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/visiter/list");
            return;
        }
        int codevisiter = Integer.parseInt(codevisiterStr);
        visiterDao.deleteVisiter(codevisiter);
        response.sendRedirect(request.getContextPath() + "/visiter/list");
    }
}
