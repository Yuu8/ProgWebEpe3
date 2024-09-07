<%@page import="java.util.List"%>
<%@page import="Modelo.Estudiantes"%>
<%@page import="Modelo.Controlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Template/Scripts.jsp" %>
        <title>Estudiantes</title>
    </head>
    <body>
        <%@include file="../Template/ValidarLogin.jsp" %>
        <%@include file="../Template/BarraNav.jsp" %>
        
        <div class="container">
            <br>
            <h2>Lista de Estudiantes</h2>
            <br>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col" colspan="2">&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Controlador cntrl = new Controlador();
                        List<Estudiantes> listaEstud = cntrl.listaEstudiantes();
                        for(Estudiantes est : listaEstud){
                    %>
                    <tr>
                        <td><%=est.getEstudiante_id() %></td>
                        <td><%=est.getEstudiante_nombre() %></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/SvEditarEstudiante" method="GET">
                                <input name="inptEstudianteId" type="hidden" value="<%=est.getEstudiante_id() %>">
                                <button type="submit" class="btn btn-warning">Editar</button>
                            </form>
                        </td>
                        <td><form action="${pageContext.request.contextPath}/SvEliminarEstudiante" method="POST">
                                <input name="inptEstudianteId" type="hidden" value="<%=est.getEstudiante_id() %>">
                                <button type="submit" class="btn btn-danger">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
