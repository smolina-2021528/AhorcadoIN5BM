// las palabras que elegi para el ahorcado
const palabras = ["carretera", "computadora", "bicicleta", "elefantes", "zanahoria"];
let palabraSecreta = "";
let tablero = [];
let intentos = 0;
const maxIntentos = 6;
const letrasUsadas = new Set();

// funcion que agarra una palabra random
function seleccionarPalabra() {
    const p = Math.floor(Math.random() * palabras.length);
    return palabras[p];
}

// funcion que dibuja el tablero
function mostrarTablero() {
    const tableroElemento = document.getElementById("tablero");
    tableroElemento.textContent = tablero.join(" ");
}

// funcion para que empezemos a jugar
function iniciarJuego() {
    palabraSecreta = seleccionarPalabra().toUpperCase();
    tablero = Array(palabraSecreta.length).fill("_");
    intentos = 0;
    letrasUsadas.clear();

    document.getElementById("ahorcado-img").src = "Image/error0.png";
    document.getElementById("mensaje").textContent = "";
    mostrarTablero();
    crearTeclado();
}

// funcion para actualizar el tablero con una letra
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

// funcion para actualizar la imagen del ahorcado según intentos
function actualizarImagen() {
    const n = intentos;
    document.getElementById("ahorcado-img").src = `Image/error${n}.png`;
}

// funcion que deshabilita todas las teclas al terminar
function deshabilitarTeclado() {
    const teclas = document.querySelectorAll(".tecla");
    teclas.forEach(boton => boton.disabled = true);
}

// funcion que verifica si ganaste o perdiste
function verificarFinJuego() {
    if (!tablero.includes("_")) {
        document.getElementById("mensaje").textContent = " ¡Ganaste!";
        deshabilitarTeclado();
        return true;
    }
    if (intentos >= maxIntentos) {
        document.getElementById("mensaje").textContent = `Perdiste. La palabra era: ${palabraSecreta}`;
        deshabilitarTeclado();
        return true;
    }
    return false;
}

// funcion para crear el teclado virtual
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

// funcion para jugar
function jugar(letra, boton) {
    if (letrasUsadas.has(letra))
        return;
    letrasUsadas.add(letra);

    if (palabraSecreta.includes(letra)) {
        actualizarTableroCon(letra);
        boton.classList.add("acierto");
        boton.disabled = true;
        document.getElementById("mensaje").textContent = `¡Bien! La letra ${letra} está.`;
    } else {
        intentos++;
        actualizarImagen();
        boton.classList.add("fallo");
        boton.disabled = true;
        document.getElementById("mensaje").textContent = `Fallaste con ${letra}. Intentos: ${intentos}/${maxIntentos}`;
    }

    verificarFinJuego();
}

// cargar el juego
window.addEventListener("load", iniciarJuego);
