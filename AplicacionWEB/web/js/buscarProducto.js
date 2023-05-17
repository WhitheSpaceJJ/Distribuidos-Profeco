const URL_API = 'https://jsonplaceholder.typicode.com/comments';
const buscador = document.getElementById('buscador');
const tablaSupermercado = document.getElementById('tabla-prductos');
const productosGET='http://localhost:8060/supermercado/producto';

let productos = [];

//BUSCAA
const buscar = () => {
  const textoBusqueda = buscador.value.toLowerCase();

  // Realizar la solicitud a la API
  fetch(productosGET)
    .then(response => response.json())
    .then(supermercados => {
      // Filtrar los comentarios por nombre, precio o super
      const supermercadosFiltrados = supermercados.filter(supermercado => {
        const nombre = supermercado.nombre.toLowerCase();
        const id = supermercado.precio.toString().toLowerCase();
        const email = supermercado.supermercadoId.nombre.toLowerCase();
        return nombre.includes(textoBusqueda) || id.includes(textoBusqueda) || email.includes(textoBusqueda);
      });
        productos = supermercados;

      // Mostrar los comentarios en la tabla
      mostrarSupermercados(supermercadosFiltrados);
    })
    .catch(error => console.error(error));
};

// Función que muestra los comentarios en la tabla
const mostrarSupermercados = supermercados => {
  // Limpiar la tabla antes de agregar nuevos comentarios
  tablaSupermercado.innerHTML = '';

  // Recorrer los comentarios y agregarlos a la tabla
  supermercados.forEach(supermercado => {
    const row = document.createElement('tr');
    const nombre = document.createElement('td');
    const id = document.createElement('td');
    const email = document.createElement('td');

    nombre.textContent = supermercado.nombre;
    id.textContent = supermercado.precio;
    email.textContent = supermercado.supermercadoId.nombre;

    row.appendChild(nombre);
    row.appendChild(id);
    row.appendChild(email);

    tablaSupermercado.appendChild(row);
  });
};

// Escuchar el evento de cambio en el cuadro de búsqueda
buscador.addEventListener('input', buscar);

// Mostrar todos los comentarios al cargar la página
buscar();


///buscar por RADIO
function mayorAMenor() {
    // Ordena los objetos por precio descendente
    productos.sort((a, b) => b.precio - a.precio);
    const tabla = document.getElementById("tabla-Comentarios");
    tabla.innerHTML = ""; // Limpiar la tabla
    // Agregar las filas de la tabla
    mostrarSupermercados(productos);

}

function menorAMayor() {
    // Ordena los objetos por precio descendente
    productos.sort((a, b) => a.precio - b.precio);
    const tabla = document.getElementById("tabla-Comentarios");
    tabla.innerHTML = ""; // Limpiar la tabla
    // Agregar las filas de la tabla
    mostrarSupermercados(productos);
}
