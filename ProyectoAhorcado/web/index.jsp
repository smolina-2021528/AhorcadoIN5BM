<%-- 
    Document   : index
    Created on : 1 sept 2025, 11:26:05
    Author     : informatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Ahorcado</title>
        <link rel="stylesheet" href="css/Styles.css">
    </head>
    <body>
        <h1>Juego del Ahorcado</h1>

        <div id="ahorcado-container">
            <img id="ahorcado-img" src="Image/error0.png" alt="Ahorcado">
        </div>

        <div id="tablero"></div>

        <div id="input-container">
            <input type="text" id="letra" maxlength="1" placeholder="Ingresa una letra">
            <button id="btnProbar" type="button">Probar</button>
        </div>
        <div id="letras-usadas"></div>
        
        <p id="mensaje"></p>

        <p id="mensaje"></p>

        <script src="Script/script.js"></script>
    </body>
</html>
