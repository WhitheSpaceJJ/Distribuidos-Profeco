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
      // Filtrar los supermercado por nombre, precio o super
      const supermercadosFiltrados = supermercados.filter(supermercado => {
        const nombre = supermercado.name.toLowerCase();
                const id = supermercado.id.toString().toLowerCase();
                const email = supermercado.email.toLowerCase();
                return nombre.includes(textoBusqueda) || id.includes(textoBusqueda) || email.includes(textoBusqueda);
      });

      // Mostrar los supermercado en la tabla
      mostrarSupermercados(supermercadosFiltrados);
    })
    .catch(error => console.error(error));
};

// Función que muestra los supermercado en la tabla
const mostrarSupermercados = supermercados => {
  // Limpiar la tabla antes de agregar nuevos supermercado
  tablaSupermercado.innerHTML = '';

  // Recorrer los supermercado y agregarlos a la tabla
  supermercados.forEach(supermercado => {
    const row = document.createElement('tr');
    const checkboxCell = document.createElement("td");
    const checkbox = document.createElement("input");
    const nombre = document.createElement('td');


    checkbox.type = "checkbox";
    checkbox.name = "comment";
    checkbox.value = JSON.stringify(supermercado);
    checkboxCell.appendChild(checkbox);

    nombre.textContent = supermercado.name;

    row.appendChild(checkboxCell);
    row.appendChild(nombre);


    tablaSupermercado.appendChild(row);
  });
};


buscador.addEventListener('input', buscar);

// Mostrar todos los supermercado al cargar la página
buscar();



//SELECCIÓN DE PRODUCTO Y AGREGARLO EN lista de supermercados favoritos

// obtener supermercado previamente seleccionados (cuando esta vacia))
let selectSupermercados = JSON.parse(localStorage.getItem("selectedComments")) || [];

// rellenar la tabla con supermercado
fetch('https://jsonplaceholder.typicode.com/comments')
  .then(response => response.json())
  .then(data => {
    const tablaSupermercados = document.getElementById("tabla-Comentarios");

    data.forEach(supermercado => {
      const row = document.createElement("tr");
      const checkboxCell = document.createElement("td");
      const checkbox = document.createElement("input");
      checkbox.type = "checkbox";
      checkbox.name = "comment";
      checkbox.value = JSON.stringify(supermercado);
      // marque la casilla del supermercado(s) fue seleccionado previamente
      if (selectSupermercados.find(seleccionSuper => seleccionSuper.id === supermercado.id)) {
        checkbox.checked = true;
      }
      checkboxCell.appendChild(checkbox);
      row.appendChild(checkboxCell);
      row.appendChild(document.createElement("td")).textContent = supermercado.name;

      tablaSupermercados.appendChild(row);
    });
  })
  .catch(error => console.error(error));

function showSelectedComments() {
  const checkboxes = document.querySelectorAll('input[name="comment"]:checked');

  // añadir los nuevos supermercado seleccionados a los existentes en localStorage
  checkboxes.forEach(checkbox => {
    const supermercado = JSON.parse(checkbox.value);
    if (!selectSupermercados.find(selectSuper => selectSuper.id === supermercado.id)) {
      selectSupermercados.push(supermercado);
    }
  });

  // guardar los supermercado seleccionados actualizados
  localStorage.setItem("selectedComments", JSON.stringify(selectSupermercados));


  if (selectSupermercados.length > 0) {
    window.location.href = "listaSuperFav.html";
  } else {
    alert("Por favor, selecciona al menos un producto.");
  }
}

const url = URL_API;
const consumidor = {
   //"id_consumidores": Math.floor(Math.random() * 1000),
    "nombre": nombre,
    "contraseña": marca,
    "email":precio,
    "edad":stock,
    //"id": consumidorId,
    //"wishList":{
        // "id":consumidorId
    //}
    //"supermercadosFavoritosList":{
        // "id":consumidorId
    //}
};

//POST
fetch(url, {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(consumidor)
})

  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error(error));


fetch(url)
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error(error));

