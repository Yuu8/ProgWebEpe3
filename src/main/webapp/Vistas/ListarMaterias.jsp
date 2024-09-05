<%@page import="java.util.List"%>
<%@page import="Modelo.Cursos"%>
<%@page import="Modelo.Controlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Template/Scripts.jsp" %>
        <title>Materias</title>
    </head>
    <body>
        <%@include file="../Template/ValidarLogin.jsp" %>
        <%@include file="../Template/BarraNav.jsp" %>
        
        <div class="container">
            <br>
            <h2>Lista de Cursos</h2>
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
                        List<Cursos> listaCursos = cntrl.listaCursos();
                        for(Cursos cur : listaCursos){
                    %>
                    <tr>
                        <td><%=cur.getCurso_id() %></td>
                        <td><%=cur.getCurso_nombre() %></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/SvEditarTarea" method="GET">
                                <input name="inptTareaId" type="hidden" value="<%=cur.getCurso_id() %>">
                                <button type="submit" class="btn btn-warning">Editar</button>
                            </form>
                        </td>
                        <td><form action="${pageContext.request.contextPath}/SvEliminarMateria" method="POST">
                                <input name="inptMateriaId" type="hidden" value="<%=cur.getCurso_id() %>">
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