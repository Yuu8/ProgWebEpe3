package Servlets;

import Modelo.Controlador;
import Modelo.Cursos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvCrearMateria", urlPatterns = "/SvCrearMateria")
public class SvCrearMateria extends HttpServlet {
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
        
        String nombreMateria = request.getParameter("idNombre");
        
        Cursos cur = new Cursos();
        cur.setCurso_nombre(nombreMateria);
        
        ctrl.CrearMateria(cur);
        response.sendRedirect("Vistas/ListarMaterias.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
