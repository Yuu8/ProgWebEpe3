<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Template/Scripts.jsp" %>
        <title>Inicio de sesión</title>
    </head>
    <body>        
        <section class="h-100">
            <div class="container h-100">
                <div class="row justify-content-sm-center h-100">
                    <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                        <div class="text-center my-5">
                            <img src="Imagenes/student.png" alt="logo" width="100">
                        </div>
                        <div class="card shadow-lg">
                            <div class="card-body p-5">
                                <h1 class="fs-4 card-title fw-bold mb-4">Inicio de Sesión</h1>
                                <form method="POST" action="${pageContext.request.contextPath}/SvLogin">
                                    <div class="mb-3">
                                        <label class="mb-2 text-muted" for="inptEmail">Correo electrónico</label>
                                        <input id="inptEmail" type="email" class="form-control" name="inptEmail" value="" required autofocus>
                                    </div>
                                    
                                    <div class="mb-3">
                                        <div class="mb-2 w-100">
                                            <label class="text-muted" for="inptPassword">Contraseña</label>
                                        </div>
                                        <input id="inptPassword" type="password" class="form-control" name="inptPassword" required>
                                    </div>
                                    
                                    <div class="d-flex align-items-center">
                                        <button type="submit" class="btn btn-primary ms-auto">
                                            Ingresar
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>