<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Vistas/ListarEstudiantes.jsp">Estudiantes / Cursos</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Gestión Estudiantes
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modalCrearEstud">Crear Estudiantes</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Vistas/ListarEstudiantes.jsp">Ver Estudiantes</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Gestión Materias
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modalCrearMateria">Crear Materias</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Vistas/ListarMaterias.jsp">Ver Materias</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/Vistas/ListarTareas.jsp">Asignar Estudiante-Curso</a>
                </li>
            </ul>
            <div class="d-flex">
                <div class="dropdown">
                    <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="../Imagenes/profile.png" alt="" width="32" height="32" class="rounded-circle me-2">
                        <%
                        String nombreUser = (String)request.getSession().getAttribute("nombreUsuario");
                        %>
                        <strong><%=nombreUser%></strong>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/SvLogout">Cerrar sesión</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>

<%@include file="CrearEstudiantes.jsp" %>
<%@include file="CrearMaterias.jsp" %>