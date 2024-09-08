package Servlets;

import Modelo.Controlador;
import Modelo.Cursos;
import Modelo.Estudiantes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvAsignarEstudiantexMateria", urlPatterns = "/SvAsignarEstudiantexMateria")
public class SvAsignarEstudiantexMateria extends HttpServlet {
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
        
        miSesion.setAttribute("estAsignar", est);
        response.sendRedirect("Vistas/AsignarEstudiantexMateria.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        //Obtiene la lista de idMaterias que estan dentro de un input
        String arrayId = request.getParameter("inptCursosIdArray");
        
        if(arrayId == null || arrayId.equals("")){
            session.removeAttribute("estAsignar");
            out.println("<script>");
            out.println("alert('Debe seleccionar una materia a asignar.-');");
            out.println("location = 'Vistas/ListarEstudiantes.jsp'");
            out.println("</script>");
        }else{
            //El string se transforma en una lista, y dentro del split se indica que estan separados por una coma los valores
            List<String> idMaterias = new ArrayList<String>(Arrays.asList(arrayId.split(",")));

            //Se crea una lista de tipo Cursos
            List<Cursos> idMateriasNew = new ArrayList<>();

            Estudiantes est = (Estudiantes)request.getSession().getAttribute("estAsignar");

            /* Se recorre la lista de tipo String, la cual por cada uno de sus valores se hará un Parseo a tipo Int.
            Este valor de tipo Int, se ingresará dentro de un método para obtener un resultado de tipo Curso,
            así esto se añadirá a la lista de tipo Curso. */
            for (int i = 0; i < idMaterias.size(); i++) {
                idMateriasNew.add(ctrl.obtCurso(Integer.valueOf(idMaterias.get(i))));
            }
            //La lista de tipo Curso, se ingresará en un Set al Estudiante
            est.setCurso_asignado(idMateriasNew);

            ctrl.editarEstudiante(est);
            response.sendRedirect("Vistas/ListarEstudiantes.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
