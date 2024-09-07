<%@page import="Modelo.Estudiantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Template/Scripts.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../Template/ValidarLogin.jsp" %>
        <%@include file="../Template/BarraNav.jsp" %>
        
        <% Estudiantes est = (Estudiantes)request.getSession().getAttribute("estEditar"); %>
        
        <div class="container">
            <form action="${pageContext.request.contextPath}/SvEditarEstudiante" method="POST">
                <table style="margin-top: 30px;">
                    <tr>
                        <td><input class="form-control" type="text" name="inpEstudianteNombre" value="<%=est.getEstudiante_nombre() %>"></td>
                    </tr>
                    <tr>
                        <td><button type="submit" class="btn btn-warning">Modificar</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
