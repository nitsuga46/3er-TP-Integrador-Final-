//valor tickets
const valor = 200;

//descuentos
let descuentoEst = 80;
let descuentoTra = 50;
let descuentoJun = 15;

//variables Formulario
let nombre = document.getElementById("nombre");
let apellido = document.getElementById("apellido");
let email = document.getElementById("email");
let cantidad = document.getElementById("cantTickets");
let categoria = document.getElementById("categ");

//calculo importe a pagar
function total() {
  //valido ingreso de datos
  if (nombre.value == "") {
    alert("Por favor ingrese su nombre.");
    return;
  }
  if (apellido.value == "") {
    alert("Por favor ingrese su apellido.");
    return;
  }
  if (email.value == "") {
    alert("Por favor ingrese su Email.");
    return;
  }
  if (cantidad.value == 0 || isNaN(cantTickets.value)) {
    alert("Por favor ingrese cantidad de tickets.");
    return;
  }
  if (categoria.value == "") {
    alert("Por favor seleccione una categoria.");
    return;
  }
  //Calculo total       --------------------------------------------------

  let valortotal = parseInt(cantidad.value) * valor;

  //Aplico descuento       --------------------------------------------------
  /*La categoria 0(sin categoria) no la evaluo porque no es necesario aplicarle ningun descuento es un calculo
  directo hecho arriba*/
  if (categoria.value == 1) {
    valortotal = valortotal - (descuentoEst / 100) * valortotal;
  }
  if (categoria.value == 2) {
    valortotal = valortotal - (descuentoTra / 100) * valortotal;
  }
  if (categoria.value == 3) {
    valortotal = valortotal - (descuentoJun / 100) * valortotal;
  }

  //inserto valor en HTML
  //alert("valor total: "+valortotal)
  pagototaltickets.innerHTML = valortotal;
}

//boton resumen

btresu.addEventListener("click", total);

//boton borrar

function reset_total() {
  pagototaltickets.innerHTML = "-";
}
btborra.addEventListener("click", reset_total);

