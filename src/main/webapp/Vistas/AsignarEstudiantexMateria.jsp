<%@page import="Modelo.Cursos"%>
<%@page import="Modelo.Controlador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Estudiantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Template/Scripts.jsp" %>
        <title>Asignar Materia</title>
    </head>
    <body>
        <%@include file="../Template/ValidarLogin.jsp" %>
        <%@include file="../Template/BarraNav.jsp" %>
        
        <% Estudiantes est = (Estudiantes)request.getSession().getAttribute("estAsignar"); %>
        
        <div class="container">
            <h2 style="margin-top: 20px;margin-bottom: 20px;">Alumno: <%=est.getEstudiante_nombre() %></h2>
        
            <form action="${pageContext.request.contextPath}/SvAsignarEstudiantexMateria" method="POST">
                <button type="submit" class="btn btn-success" style="margin-bottom: 20px;">Asignar masivo</button>
                <input name="inptCursosIdArray" id="inptCursosIdArray" type="hidden">
            </form>
            
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">&nbsp;</th>
                        <th scope="col">Nombre</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Controlador cntrl = new Controlador();
                        List<Cursos> listaCursos = cntrl.listaCursos();
                        for(Cursos cur : listaCursos){
                    %>
                    <tr>
                        <td><input type="checkbox" onclick="AgregaId();" class="form-check-input" id="idCursoCheck" name="idCursoCheck" value="<%=cur.getCurso_id() %>"></td>
                        <td><%=cur.getCurso_nombre() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
               
            <script>
                function AgregaId(){
                    var ArrayIdCurso = [];
                    var CheckBoxId = document.querySelectorAll("input[name='idCursoCheck']:checked");
                    CheckBoxId.forEach((item)=>{
                        ArrayIdCurso.push(item.value);
                    });
                    $('#inptCursosIdArray').val(ArrayIdCurso);
                }
            </script>
        </div>
    </body>
</html>
