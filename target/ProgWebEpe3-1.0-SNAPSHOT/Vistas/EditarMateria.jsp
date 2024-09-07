<%@page import="Modelo.Cursos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Template/Scripts.jsp" %>
        <title>Editar Materia</title>
    </head>
    <body>
        <%@include file="../Template/ValidarLogin.jsp" %>
        <%@include file="../Template/BarraNav.jsp" %>
        
        <% Cursos cur = (Cursos)request.getSession().getAttribute("curEditar"); %>
        
        <div class="container">
            <form action="${pageContext.request.contextPath}/SvEditarMateria" method="POST">
                <table style="margin-top: 30px;">
                    <tr>
                        <td><input class="form-control" type="text" name="inpCursoNombre" value="<%=cur.getCurso_nombre() %>"></td>
                    </tr>
                    <tr>
                        <td><button type="submit" class="btn btn-warning">Modificar</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
