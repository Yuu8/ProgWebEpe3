<%
    HttpSession sesion = request.getSession();
    if(sesion.getAttribute("nombreUsuario") == null){
        response.sendRedirect("../index.jsp");
    }
%>