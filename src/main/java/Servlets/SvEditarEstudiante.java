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
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvEditarEstudiante", urlPatterns = "/SvEditarEstudiante")
public class SvEditarEstudiante extends HttpServlet {
    Controlador ctrl = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        HttpSession miSesion = request.getSession();
        
        int idEstudiante = Integer.parseInt(request.getParameter("inptEstudianteId"));
        Estudiantes est = ctrl.obtEstudiante(idEstudiante);
        
        miSesion.setAttribute("estEditar", est);
        response.sendRedirect("Vistas/EditarEstudiante.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String cursoNombre = request.getParameter("inpEstudianteNombre");
        
        Estudiantes est = (Estudiantes)request.getSession().getAttribute("estEditar");
        est.setEstudiante_nombre(cursoNombre);
        
        ctrl.editarEstudiante(est);
        response.sendRedirect("Vistas/ListarEstudiantes.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
