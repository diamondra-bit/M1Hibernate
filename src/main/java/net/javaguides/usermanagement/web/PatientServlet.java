package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import net.javaguides.usermanagement.model.Patient;
import net.javaguides.usermanagement.dao.PatientDAO;

@WebServlet("/")
public class PatientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PatientDAO patientDao;

    public void init() {
        patientDao = new PatientDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertPatient(request, response);
                    break;
                case "/delete":
                    deletePatient(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updatePatient(request, response);
                    break;
                case "/list":
                default:
                    listPatients(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPatients(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Patient> listPatients = patientDao.getAllPatients();
        request.setAttribute("listPatients", listPatients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int codepal = Integer.parseInt(request.getParameter("codepal"));
        Patient existingPatient = patientDao.getPatient(codepal);
        request.setAttribute("patient", existingPatient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPatient(HttpServletRequest request,HttpServletResponse response)
    		throws SQLException,IOException {
    			String nom=request.getParameter("nom");
    			String prenom=request.getParameter("prenom");
    			String sexe=request.getParameter("sexe");
    			String adresse=request.getParameter("adresse");
    			
    			Patient newPatient=new Patient(nom,prenom,sexe,adresse);
    			patientDao.savePatient(newPatient);
    			response.sendRedirect("list");
    		}


    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int codepal = Integer.parseInt(request.getParameter("codepal"));
        String nom = request.getParameter("nom").trim();
        String prenom = request.getParameter("prenom").trim();
        String sexe = request.getParameter("sexe").trim();
        String adresse = request.getParameter("adresse").trim();

        Patient patient = new Patient(codepal, nom, prenom, sexe,adresse);
        patientDao.updatePatient(patient);
        response.sendRedirect("list");
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int codepal = Integer.parseInt(request.getParameter("codepal"));
        patientDao.deletePatient(codepal);
        response.sendRedirect("list");
    }
}
