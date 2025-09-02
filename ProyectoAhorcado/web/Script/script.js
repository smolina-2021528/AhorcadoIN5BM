// las palabras que elegi para el ahorcado
const palabras = ["carretera", "computadora", "bicicleta", "elefantes", "zanahoria"];
let palabraSecreta = "";
let tablero = [];
let intentos = 0;               // contador de intentos fallidos
const maxIntentos = 6;          // máximo de errores permitidos
const letrasUsadas = new Set(); // letras que ya se usaron en el juego

// funcion que agarra una palabra random
function seleccionarPalabra() {
  const p = Math.floor(Math.random() * palabras.length);
  return palabras[p];
}

// si una letra es valida desde la a hasta la z incluyendo la Ñ por eso se pone A-ZÑ
function esLetraValida(ch) {
  return /^[A-ZÑ]$/.test(ch);
}

// funcion que dibuja el tablero
function mostrarTablero() {
  const tableroElemento = document.getElementById("tablero");
  tableroElemento.textContent = tablero.join(" ");
}

// funcion para que empezemos a jugar
function iniciarJuego() {
  palabraSecreta = seleccionarPalabra().toUpperCase(); // palabra random en mayúsculas
  tablero = Array(palabraSecreta.length).fill("_");    // guiones bajos al inicio
  intentos = 0;                                        // reiniciar intentos
  letrasUsadas.clear();                                // limpiar letras usadas

  document.getElementById("ahorcado-img").src = "Image/error0.png";
  document.getElementById("mensaje").textContent = "";
  mostrarTablero();

  // verificar las letras en el juego
  const letra = document.getElementById("letra");
  const btn = document.getElementById("btnProbar");
  letra.value = "";
  letra.disabled = false;
  btn.disabled = false;
  letra.focus();

  // al darle clic prueba la letra
  btn.onclick = jugar;
  // si se usa la tecla enter se acepta la letra tambien
  letra.onkeydown = (e) => {
    if (e.key === "Enter") jugar();
  };
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

// funcion para actualizar la imagen del ahorcado dependiendo de los intentos
function actualizarImagen() {
  const n = Math.min(intentos, maxIntentos);
  document.getElementById("ahorcado-img").src = `Image/error${n}.png`;
}

// funcion para deshabilitar input cuando termina el juego
function deshabilitarEntrada() {
  document.getElementById("letra").disabled = true;
  document.getElementById("btnProbar").disabled = true;
}

// funcion que verifica si ganaste o perdiste
function verificarFinJuego() {
  if (!tablero.includes("_")) {
    document.getElementById("mensaje").textContent = "Ganaste, al fin";
    deshabilitarEntrada();
    return true;
  }
  if (intentos >= maxIntentos) {
    document.getElementById("mensaje").textContent = ` Perdiste sos mero lelo. La palabra era: ${palabraSecreta}`;
    deshabilitarEntrada();
    return true;
  }
  return false;
}

// funcion para jugar
function jugar() {
  const letraInput = document.getElementById("letra");
  const letraV = (letraInput.value || "").toUpperCase().trim();
  letraInput.value = "";
  letraInput.focus();

  if (!esLetraValida(letraV)) {
    document.getElementById("mensaje").textContent = "Ingresa una letra válida (A-Z/Ñ).";
    return;
  }

  if (letrasUsadas.has(letraV)) {
    document.getElementById("mensaje").textContent = `Ya usaste la letra ${letraV}.`;
    return;
  }
  letrasUsadas.add(letraV);

  if (palabraSecreta.includes(letraV)) {
    actualizarTableroCon(letraV);
    document.getElementById("mensaje").textContent = `¡Bien! La letra ${letraV} está.`;
  } else {
    intentos++;
    actualizarImagen(); // actualiza la imagen cada fallo
    document.getElementById("mensaje").textContent = `Fallaste con ${letraV}. Intentos: ${intentos}/${maxIntentos}`;
  }

  verificarFinJuego();
}

// cargar el juego
window.addEventListener("load", iniciarJuego);
