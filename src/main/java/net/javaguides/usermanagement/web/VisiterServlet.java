package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import net.javaguides.usermanagement.model.Visiter;
import net.javaguides.usermanagement.dao.VisiterDAO;
import java.util.Date; 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;



@WebServlet("/visiter/*")

public class VisiterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VisiterDAO visiterDao;

    public void init() {
        visiterDao = new VisiterDAO();
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
    String codemedStr = request.getParameter("codemed");
    String codepalStr = request.getParameter("codepal");
    String datesr = request.getParameter("date");

    if (codemedStr == null || codepalStr == null || datesr == null ||
        codemedStr.isEmpty() || codepalStr.isEmpty() || datesr.isEmpty()) {
        response.sendRedirect(request.getContextPath() + "/visiter/list?error=missingData");
        return;
    }

    try {
        int codemed = Integer.parseInt(codemedStr);
        int codepal = Integer.parseInt(codepalStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(datesr);

        Visiter newVisiter = new Visiter(codemed, codepal, date);
        visiterDao.saveVisiter(newVisiter);
        response.sendRedirect(request.getContextPath() + "/visiter/list");
    } catch (NumberFormatException e) {
        response.sendRedirect(request.getContextPath() + "/visiter/list?error=invalidNumber");
    } catch (ParseException e) {
        response.sendRedirect(request.getContextPath() + "/visiter/list?error=invalidDate");
    }
}

private void updateVisiter(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    String codevisiterStr = request.getParameter("codevisiter");
    String codemedStr = request.getParameter("codemed");
    String codepalStr = request.getParameter("codepal");
    String datesr = request.getParameter("date");

    if (codevisiterStr == null || codemedStr == null || codepalStr == null || datesr == null ||
        codevisiterStr.isEmpty() || codemedStr.isEmpty() || codepalStr.isEmpty() || datesr.isEmpty()) {
        response.sendRedirect(request.getContextPath() + "/visiter/list?error=missingData");
        return;
    }

    try {
        int codevisiter = Integer.parseInt(codevisiterStr);
        int codemed = Integer.parseInt(codemedStr);
        int codepal = Integer.parseInt(codepalStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(datesr);

        Visiter visiter = new Visiter(codevisiter, codemed, codepal, date);
        visiterDao.updateVisiter(visiter);
        response.sendRedirect(request.getContextPath() + "/visiter/list");
    } catch (NumberFormatException e) {
        response.sendRedirect(request.getContextPath() + "/visiter/list?error=invalidNumber");
    } catch (ParseException e) {
        response.sendRedirect(request.getContextPath() + "/visiter/list?error=invalidDate");
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