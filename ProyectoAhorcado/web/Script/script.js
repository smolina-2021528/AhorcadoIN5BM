// las palabras que elegi para el ahorcado
const palabras = ["carretera", "computadora", "bicicleta", "elefantes", "zanahoria"];
let palabraSecreta = ""; //esto se llena con la palabra random
let tablero = []; //aca se va a llenar con guiones o letras
let errores = 0; // los errores actuales
const maxIntentos = 6; //intentos hasta perder
const letrasUsadas = new Set(); //una coleccion para que no se repitan las letras usadas por eso es Set

// funcion que agarra una palabra random
// se escoge segun el indice y se devuelve ese valor
function seleccionarPalabra() {
    const p = Math.floor(Math.random() * palabras.length);
    return palabras[p];
}

// funcion que dibuja el tablero
// llama el tablero con su id, y lo muestra separado por espacios (por eso las
//comillas con espacio), además con el textContent se puede ingresar datos
// (cuando se va a hacer clic en el teclado)

function mostrarTablero() {
    const tableroElemento = document.getElementById("tablero");
    tableroElemento.textContent = tablero.join(" ");
}

// funcion para que empezemos a jugar
// Define la palabra secreta llamando el método y se transforma en mayúscula, se
// llena el tablero con guiones bajos, se pone el contador de errores en 0, y se limpia
// la variable de letras usadas
// Se trae la imagen de la horca y el mensaje (aun vacio), se crea el tablero y el teclado
function iniciarJuego() {
    palabraSecreta = seleccionarPalabra().toUpperCase();
    tablero = Array(palabraSecreta.length).fill("_");
    errores = 0;
    letrasUsadas.clear();

    document.getElementById("ahorcado-img").src = "Image/error0.png";
    document.getElementById("mensaje").textContent = "";
    mostrarTablero();
    crearTeclado();
}

// funcion para actualizar el tablero con una letra
// Se busca con el for por el tablero mientras tenga guion, si alguna letra coincide la
// coloca y sigue, luego llama el tablero actualizado
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
// Se le da el valor de los errores a una constante, se llama la imagen que cambiara
// y se coloca con el valor ${n} entre la ruta, para que cambie según los errores
function actualizarImagen() {
    const n = errores;
    document.getElementById("ahorcado-img").src = `Image/error${n}.png`;
}

// funcion que deshabilita todas las teclas al terminar
// Deshabilita todas las teclas (se pone cuando se termina el juego)
function deshabilitarTeclado() {
    const teclas = document.querySelectorAll(".tecla");
    teclas.forEach(boton => boton.disabled = true);
}

// funcion que verifica si ganaste o perdiste
// Hay victoria si el tablero ya no tiene guiones, si es así muestra el mensaje
//Ganaste y se deshabilita el teclado; Si los errores son iguales a 6, se muestra el
// mensaje perdiste y el valor de la palabra secreta de ese juego y se deshabilita el teclado;
// Los dos if tienen return true, porque si alguna condición es true el juego termina y sino, se sigue jugando;
function verificarFinJuego() {
    if (!tablero.includes("_")) {
        document.getElementById("mensaje").textContent = " ¡Ganaste!";
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

// funcion para crear el teclado virtual
// Se ponen las letras validas, y se limpia el contenedor del teclado. Se crea un botón por cada letra con la clase tecla 
// (esto sirve para el método donde se bloquea el teclado), y con un método onclick, que llama el método jugar.
// El .split es para separar cada letra de la variable, en vez de poner un array; y el .appendChild solamente añade el botón al teclado.

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
// Primero mira si la letra ya fue usada y la añade a esa lista. Despues se comprueba
// si la palabra tiene la letra que se asigna al hacer clic con el botón, se deshabilita el 
// botón y muestra un mensaje diciendo que esa letra está; si no está se suma un
// error, se cambia la imagen, se dehabilita el botón y se muestra un mensaje diciendo la letra que no es más los intentos (fallos) que llevamos.
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
        errores++;
        actualizarImagen();
        boton.classList.add("fallo");
        boton.disabled = true;
        document.getElementById("mensaje").textContent = `Fallaste con ${letra}. Intentos: ${errores}/${maxIntentos}`;
    }

    verificarFinJuego();
}

// cargar el juego
window.addEventListener("load", iniciarJuego);
