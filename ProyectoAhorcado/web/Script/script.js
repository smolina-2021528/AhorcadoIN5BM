// las palabras que elegi para el ahorcado
const palabras = ["carretera", "computadora", "bicicleta", "elefantes", "zanahoria"];

// funcion que agarra una palabra random
function seleccionarPalabra() {
  const p = Math.floor(Math.random() * palabras.length);
  return palabras[p];
}


