<%@page import="Modelo.Estudiantes"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Controlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Template/Scripts.jsp" %>
        <title>Estudiantes x Curso</title>
    </head>
    <body>
        <%@include file="../Template/ValidarLogin.jsp" %>
        <%@include file="../Template/BarraNav.jsp" %>
        
        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Estudiante</th>
                        <th scope="col">Cursos</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Controlador cntrl = new Controlador();
                        List<Estudiantes> listaEstudiantes = cntrl.listaEstudiantes();
                        for(Estudiantes est : listaEstudiantes){
                    %>
                    <tr>
                        <td><%=est.getEstudiante_nombre() %></td>
                        <td>
                            <ul>
                                <% for (int i = 0; i < est.getCurso_asignado().size(); i++) { %>
                                <li><%=est.getCurso_asignado().get(i).getCurso_nombre() %></li>
                                <% } %>
                            </ul>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
