<%-- 
    Document   : index
    Created on : 1 sept 2025, 11:26:05
    Author     : informatica
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Juego del Ahorcado</title>
        <link rel="stylesheet" href="css/Styles.css">
    </head>
    <body>
        <h1>Juego del Ahorcado</h1>

        <!-- Imagen del ahorcado -->
        <div class="contenedor-img">
            <img id="ahorcado-img" src="Image/error0.png" alt="Ahorcado">
        </div>

        <!-- Tablero donde aparece la palabra con guiones bajos -->
        <div id="tablero" class="tablero"></div>

        <!-- Mensaje de estado del juego -->
        <div id="mensaje" class="mensaje"></div>

        <!-- Teclado virtual -->
        <div id="teclado" class="teclado"></div>

        <!-- Botón para reiniciar -->
        <div class="reinicio">
            <button onclick="iniciarJuego()"> Reiniciar Juego</button>
        </div>

        <script src="Script/script.js"></script>
    </body>
</html>
