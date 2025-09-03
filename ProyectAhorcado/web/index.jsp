<%-- 
    Document   : index
    Created on : 2 sept 2025
    Author     : informatica
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - Ahorcado</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="login-container">
        <h1>Iniciar Sesión</h1>
        <form action="Validar" method="post">
            <input type="text" name="txtCorreo" placeholder="Usuario" required>
            <input type="password" name="txtPassword" placeholder="Contraseña" required>
            <button type="submit" name="accion" value="Ingresar">Login</button>
        </form>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
    </div>
</body>
</html>
