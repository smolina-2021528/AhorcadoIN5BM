<%-- 
    Document   : index
    Created on : 1 sept 2025, 11:26:05
    Author     : informatica
--%>

    <!-- configuracion de la pagina, usando utf-8 para el español --> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <!-- titulo de la pagina (el del navegador) -->
    <title>Juego del Ahorcado</title>
    <link rel="stylesheet" href="css/Styles.css">
</head>
<body>
    <!-- titulo de la pagina, la vista   -->
    <h1>¿Podras evitar ser ahorcado?</h1>

    <!--  contenedor para la imagen-->
    <div class="contenedor-img">
        <!--  en este img, se maneja la imagen que cambia, por eso el id ahorcado-img, por defecto carga error0 -->
        <img id="ahorcado-img" src="Image/error0.png" alt="Ahorcado">
    </div>

     <!-- division donde se colocara el tablero, ya sean guiones o letras-->
    <div id="tablero" class="tablero"></div>

     <!-- se muestra si la letra estaba o no, y si se ganó o perdio-->
    <div id="mensaje" class="mensaje"></div>

     <!--  aca se dibuja el teclado virtual-->
    <div id="teclado" class="teclado"></div>

     <!--  aqui esta el boton reinicar juego, que al hacer clic llama la funcion de iniciar juego-->
    <div class="reinicio">
        <button onclick="iniciarJuego()" class="btnReiniciar">Reiniciar Juego</button>
    </div>

    <script src="Script/script.js"></script>
</body>
</html>
