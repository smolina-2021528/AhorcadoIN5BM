// =============================
// Juego del Ahorcado
// =============================

let palabraSecreta = ""; 
let tablero = []; 
let errores = 0; 
const maxIntentos = 6; 
const letrasUsadas = new Set();

// =============================
// Obtener palabra desde el servlet
// =============================
function obtenerPalabra(callback) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "./Controlador?menu=Juego&accion=obtenerPalabra", true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const palabra = xhr.responseText.trim().toUpperCase();
            callback(palabra);
        }
    };
    xhr.send();
}

// =============================
// Dibujar tablero
// =============================
function mostrarTablero() {
    const tableroElemento = document.getElementById("tablero");
    tableroElemento.textContent = tablero.join(" ");
}

// =============================
// Iniciar juego
// =============================
function iniciarJuego() {
    obtenerPalabra(function (palabra) {
        palabraSecreta = palabra;
        tablero = Array(palabraSecreta.length).fill("_");
        errores = 0;
        letrasUsadas.clear();

        document.getElementById("ahorcado-img").src = "./Image/error0.png";
        document.getElementById("mensaje").textContent = "";
        mostrarTablero();
        crearTeclado();
    });
}

// =============================
// Actualizar tablero
// =============================
function actualizarTableroCon(letra) {
    let aciertos = 0;
    for (let i = 0; i < palabraSecreta.length; i++) {
        if (palabraSecreta[i] === letra && tablero[i] === "_") {
            tablero[i] = letra;
            aciertos++;
        }
    }
    mostrarTablero();
    return aciertos;
}

// =============================
// Actualizar imagen
// =============================
function actualizarImagen() {
    const n = errores;
    document.getElementById("ahorcado-img").src = `./Image/error${n}.png`;
}

// =============================
// Deshabilitar teclado
// =============================
function deshabilitarTeclado() {
    const teclas = document.querySelectorAll(".tecla");
    teclas.forEach(boton => boton.disabled = true);
}

// =============================
// Verificar fin de juego
// =============================
function verificarFinJuego() {
    if (!tablero.includes("_")) {
        document.getElementById("mensaje").textContent = "¡Ganaste!";
        deshabilitarTeclado();
        return true;
    }
    if (errores >= maxIntentos) {
        document.getElementById("mensaje").textContent = `Perdiste. La palabra era: ${palabraSecreta}`;
        deshabilitarTeclado();
        return true;
    }
    return false;
}

// =============================
// Crear teclado virtual
// =============================
function crearTeclado() {
    const letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".split("");
    const contenedor = document.getElementById("teclado");
    contenedor.innerHTML = "";

    letras.forEach(l => {
        const boton = document.createElement("button");
        boton.textContent = l;
        boton.className = "tecla";
        boton.onclick = () => jugar(l, boton);
        contenedor.appendChild(boton);
    });
}

// =============================
// Jugar
// =============================
function jugar(letra, boton) {
    if (letrasUsadas.has(letra)) return;
    letrasUsadas.add(letra);

    if (palabraSecreta.includes(letra)) {
        actualizarTableroCon(letra);
        boton.classList.add("acierto");
        boton.disabled = true;
        document.getElementById("mensaje").textContent = `¡Bien! La letra ${letra} está.`;
    } else {
        errores++;
        actualizarImagen();
        boton.classList.add("fallo");
        boton.disabled = true;
        document.getElementById("mensaje").textContent = `Fallaste con ${letra}. Intentos: ${errores}/${maxIntentos}`;
    }

    verificarFinJuego();
}

// =============================
// Cargar juego al inicio
// =============================
window.addEventListener("load", iniciarJuego);
