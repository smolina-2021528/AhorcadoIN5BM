// las palabras que elegi para el ahorcado
const palabras = ["carretera", "computadora", "bicicleta", "elefantes", "zanahoria"];
let palabraSecreta = "";
let tablero = [];

// funcion que agarra una palabra random
function seleccionarPalabra() {
  const p = Math.floor(Math.random() * palabras.length);
  return palabras[p];
}

// funcion que dibuja el tablero
function mostrarTablero() {
  const tablero = document.getElementById("tablero");
  tablero.textContent = tablero.join(" ");
}

// funcion para que empezemos a jugar
function iniciarJuego() {
  palabraSecreta = seleccionarPalabra();
  tablero = Array(palabraSecreta.length).fill("_");
  document.getElementById("ahorcado-img").src = "Image/error0.png";
  document.getElementById("mensaje").textContent = "";
  mostrarTablero();
}

//cargar el juego
window.addEventListener("load", iniciarJuego);
