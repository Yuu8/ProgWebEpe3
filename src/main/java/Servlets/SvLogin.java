package Servlets;

import Modelo.Controlador;
import Modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvLogin", urlPatterns = "/SvLogin")
public class SvLogin extends HttpServlet {
    Controlador controlador = new Controlador();

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
        PrintWriter out = response.getWriter();
        
        List<Usuarios> usuariosData = controlador.obtenerUsuarios();
        HttpSession sesion = request.getSession();
        
        String Email = request.getParameter("inptEmail");
        String Password = request.getParameter("inptPassword");
        
        for (Usuarios usuLog : usuariosData) {
            if(usuLog.getUsuario_email().equals(Email) && usuLog.getUsuario_clave().equals(Password)){
                sesion.setAttribute("idUsuario", usuLog.getUsuario_id());
                sesion.setAttribute("nombreUsuario", usuLog.getUsuario_nombre());
                response.sendRedirect("Vistas/ListarEstudiantes.jsp");
            }else{
                out.println("<script>");
                out.println("alert('Usuario o clave errónea.-');");
                out.println("location = 'index.jsp'");
                out.println("</script>");
                //response.sendRedirect("index.jsp");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
