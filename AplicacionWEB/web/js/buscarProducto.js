const URL_API = 'https://jsonplaceholder.typicode.com/comments';
const buscador = document.getElementById('buscador');
const tablaSupermercado = document.getElementById('tabla-prductos');

//BUSCAA
const buscar = () => {
  const textoBusqueda = buscador.value.toLowerCase();

  // Realizar la solicitud a la API
  fetch(URL_API)
    .then(response => response.json())
    .then(supermercados => {
      // Filtrar los comentarios por nombre, precio o super
      const supermercadosFiltrados = supermercados.filter(supermercado => {
        const nombre = supermercado.name.toLowerCase();
        const id = supermercado.id.toString().toLowerCase();
        const email = supermercado.email.toLowerCase();
        return nombre.includes(textoBusqueda) || id.includes(textoBusqueda) || email.includes(textoBusqueda);
      });

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

    nombre.textContent = supermercado.name;
    id.textContent = supermercado.id;
    email.textContent = supermercado.email;

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
 function mostrarProductos() {
   const ordenRadios = document.getElementsByName("orden");
   let orden = "asc"; // orden por defecto
   for (let i = 0; i < ordenRadios.length; i++) {
     if (ordenRadios[i].checked) {
       orden = ordenRadios[i].value === "ascendente" ? "asc" : "desc";
     }
   }
   fetch(`https://jsonplaceholder.typicode.com/comments?_sort=id&_order=${orden}`)
     .then(response => response.json())
     .then(data => {
       const tabla = document.getElementById("tabla-Comentarios");
       tabla.innerHTML = ""; // Limpiar la tabla
       // Agregar las filas de la tabla
       mostrarSupermercados(data);

     });
 }

fetch(url)
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error(error));