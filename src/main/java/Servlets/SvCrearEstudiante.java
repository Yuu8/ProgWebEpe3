package Servlets;

import Modelo.Controlador;
import Modelo.Estudiantes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvCrearEstudiante", urlPatterns = "/SvCrearEstudiante")
public class SvCrearEstudiante extends HttpServlet {
    Controlador ctrl = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String nombreEstud = request.getParameter("idNombre");
        
        Estudiantes est = new Estudiantes();
        est.setEstudiante_nombre(nombreEstud);
        
        ctrl.CrearEstudiante(est);
        response.sendRedirect("Vistas/ListarEstudiantes.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
