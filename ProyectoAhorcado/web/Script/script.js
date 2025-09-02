// las palabras que elegi para el ahorcado
const palabras = ["carretera", "computadora", "bicicleta", "elefantes", "zanahoria"];
let palabraSecreta = "";
let tablero = [];

// funcion que agarra una palabra random
function seleccionarPalabra() {
  const p = Math.floor(Math.random() * palabras.length);
  return palabras[p];
}

//  si una letra es valida desde la a hasta la z incluyendo la Ñ por eso se pone A-ZÑ
function esLetraValida(ch) {
  return /^[A-ZÑ]$/.test(ch);
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
  
  // verificar las letras en el juego
  const letra = document.getElementById("letra");
  const btn = document.getElementById("btnProbar");
  letra.value = "";
  letra.focus();
  
  // al darle clic prueba la letra
  btn.onclick = jugar;
  //si se usa la tecla enter se acepta la letra tambien
  letra.onkeydown = (e) => {
    if (e.key === "Enter") jugar();
  };
}

// funcion para jugar
function jugar() {
  const letra = document.getElementById("letra");
  const letraV = (letra.value || "").toUpperCase().trim();
  letra.value = "";
  letra.focus();

  if (!esLetraValida(letraV)) {
    document.getElementById("mensaje").textContent = "Ingresa una letra válida (A-Z/Ñ).";
    return;
  }


//cargar el juego
window.addEventListener("load", iniciarJuego);
