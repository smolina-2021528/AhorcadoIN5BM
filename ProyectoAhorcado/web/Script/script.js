// las palabras que elegi para el ahorcado
const palabras = ["carretera", "computadora", "bicicleta", "elefantes", "zanahoria"];
let palabraSecreta = "";
let tablero = [];
let intentos = 0;               // contador de los intentos fallidos
const maxIntentos = 6;        
const letrasUsadas = new Set(); 

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
  tablero = Array(palabraSecreta.length).fill("_");    
  intentos = 0;                                       
  letrasUsadas.clear();                             

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

  // ver si la letra ya fue usada
  if (letrasUsadas.has(letraV)) {
    document.getElementById("mensaje").textContent = `Ya usaste la letra ${letraV}.`;
    return;
  }
  letrasUsadas.add(letraV);

  if (palabraSecreta.includes(letraV)) {
    // letra correcta
    actualizarTableroCon(letraV);
    document.getElementById("mensaje").textContent = `¡Bien! La letra ${letraV} está.`;
  } else {
    // letra incorrecta
    intentos++;
    document.getElementById("mensaje").textContent = `Fallaste con ${letraV}. Intentos: ${intentos}/${maxIntentos}`;
  }
}

// cargar el juego
window.addEventListener("load", iniciarJuego);
