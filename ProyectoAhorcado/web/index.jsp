<%-- 
    Document   : index
    Created on : 1 sept 2025, 11:26:05
    Author     : informatica
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Juego del Ahorcado</title>
    <link rel="stylesheet" href="css/Styles.css">
</head>
<body>
    <h1>Juego del Ahorcado</h1>

    <div class="contenedor-img">
        <img id="ahorcado-img" src="Image/error0.png" alt="Ahorcado">
    </div>

    <div id="tablero" class="tablero"></div>

    <div id="mensaje" class="mensaje"></div>

    <div id="teclado" class="teclado"></div>

    <div class="reinicio">
        <button onclick="iniciarJuego()" class="btnReiniciar">Reiniciar Juego</button>
    </div>

    <script src="Script/script.js"></script>
</body>
</html>
