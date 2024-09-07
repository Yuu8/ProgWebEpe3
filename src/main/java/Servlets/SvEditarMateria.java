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
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvEditarMateria", urlPatterns = "/SvEditarMateria")
public class SvEditarMateria extends HttpServlet {
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
        
        int idMateria = Integer.parseInt(request.getParameter("inptMateriaId"));
        Cursos cur = ctrl.obtCurso(idMateria);
        
        miSesion.setAttribute("curEditar", cur);
        response.sendRedirect("Vistas/EditarMateria.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String cursoNombre = request.getParameter("inpCursoNombre");
        
        Cursos cur = (Cursos)request.getSession().getAttribute("curEditar");
        cur.setCurso_nombre(cursoNombre);
        
        ctrl.editarMateria(cur);
        response.sendRedirect("Vistas/ListarMaterias.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
