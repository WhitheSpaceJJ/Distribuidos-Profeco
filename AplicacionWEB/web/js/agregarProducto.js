const url = 'https://jsonplaceholder.typicode.com/users';
const respuesta = document.querySelector("#respuesta")
const btnAgregar = document.querySelector("#botonGuardar")
const btnActualizar = document.querySelector("#btnActualizar")
const tabla = document.querySelector("table tbody");
const urlPostProducto = 'http://localhost:8060/supermercado/producto'
const urlPutProducto = 'http://localhost:8060/supermercado/producto/{idProducto}'
const urlGetProducto = 'http://localhost:8060/supermercado/producto'

document.addEventListener("DOMContentLoaded", mostrarProducto)
btnAgregar.addEventListener("click", agregarProducto)
btnActualizar.addEventListener("click", actualizarProducto)

async function mostrarProducto() {
  const respuesta = await fetch(url)
  const productos = await respuesta.json()
  //mostrarListaUsuarios(usuarios)
  // obtenerUsuarios()
}

/*
//endpoint producto
const producto = {
  //"id": Math.floor(Math.random() * 1000),
  "nombre": nombre,
  "marca": marca,
  "precio":precio,
  "stock":stock,
  //"supermercado":{
     // "id":idSupermercado
  //}
}

const productoActualizado = {
  //"id": Math.floor(Math.random() * 1000),
  "nombre": nombre,
  "marca": marca,
  "precio":precio,
  "stock":stock,
  //"supermercado":{
     // "id":idSupermercado
  //}
}
*/

const idSupermercado = 5

function agregarProducto() {
  const nombre = document.querySelector("#nombreProducto").value
  const marca = document.querySelector("#marcaProducto").value
  const precio = document.querySelector("#precioProducto").value
  const stock = document.querySelector("#stockProducto").value

  const producto = {
    //"id": Math.floor(Math.random() * 1000),
    "nombre": nombre,
    "marca": marca,
    "precio": precio,
    "stock": stock,
    //"supermercado":{
    // "id":idSupermercado
    //}
  }

  //PO0000000000000000ST
  // este es para agregar productos a los supermercados
  /**
      let idProductoAgregado = 0
      fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(producto)
      })
        .then(response => response.json())
        .then(data => {
          idProductoAgregado = data.id
          console.log(data);
        })
        .catch(error => {
          // Manejar errores
          console.error('Error:', error);
          return
        });*/

  const fila = document.createElement("tr");
  console.log(respuesta)
  const nombreValido = validarTexto(producto.nombre);
  const marcaValida = validarTexto(producto.marca);
  const precioValido = validarNumeros(producto.precio);
  const stockValido = validarNumeros(producto.stock);
  if (!nombreValido) {
    alert("Debe introducir un nombre válido");
  } else if (!marcaValida) {
    alert("La marca debe contener solo letras y espacios");
    document.querySelector("#marcaProducto").value = "";
  } else if (!precioValido) {
    alert("El precio debe ser un número de hasta 6 dígitos");
    document.querySelector("#precioProducto").value = "";
  } else if (!stockValido) {
    alert("El stock debe ser un número de hasta 6 dígitos");
    document.querySelector("#stockProducto").value = "";
  } else {
    // Crear fila y agregar a tabla
    const fila = document.createElement("tr");
    fila.innerHTML = `
                <td>${producto.nombre}</td>
                <td>${producto.marca}</td>
                <td>${producto.precio}</td>
                <td>${producto.stock}</td>
                <td>
                  <button data-id="${producto.id}" class="btn btn-primary actualizar" href="myModal">Actualizar</button>
                  <button data-id="${producto.id}" class="btn btn-danger eliminar">Eliminar</button>
                </td>
              `;
    tabla.appendChild(fila);
    document.querySelector("#nombreProducto").value = "";
    document.querySelector("#marcaProducto").value = "";
    document.querySelector("#precioProducto").value = "";
    document.querySelector("#stockProducto").value = "";
  }
  console.log(respuesta)
}


// Método actualizar / PUT

function actualizarProducto(e) {
  const nombre = document.querySelector("#nombreProductoActualizar").value
  const marca = document.querySelector("#marcaProductoActualizar").value
  const precio = document.querySelector("#precioProductoActualizar").value
  const stock = document.querySelector("#stockProductoActualizar").value
  const idProductoActualizar = e.target.dataset.id;


  console.log("Entro")
  console.log(idProductoActualizar)

  const producto = {
    //"id": Math.floor(Math.random() * 1000),
    "nombre": nombre,
    "marca": marca,
    "precio": precio,
    "stock": stock,
    //"supermercado":{
    // "id":idSupermercado
    //}
  }
  let idProductoActualizado = 0
  const nombreValido = validarTexto(producto.nombre);
  const marcaValida = validarTexto(producto.marca);
  const precioValido = validarNumeros(producto.precio);
  const stockValido = validarNumeros(producto.stock);

  if (!nombreValido) {
    alert("Debe introducir un nombre válido");
  } else if (!marcaValida) {
    alert("La marca debe contener solo letras y espacios");
    document.querySelector("#marcaProductoActualizar").value = "";
  } else if (!precioValido) {
    alert("El precio debe ser un número de hasta 6 dígitos");
    document.querySelector("#precioProductoActualizar").value = "";
  } else if (!stockValido) {
    alert("El stock debe ser un número de hasta 6 dígitos");
    document.querySelector("#stockProductoActualizar").value = "";
  } else {

//PUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUT
    //Este es para actualizar el producto en el supermercado
    fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(producto)
    })
      .then((response) => response.json())
      .then((data) => {
        idProductoActualizado = data.id
        console.log(data)
      })
      .catch((error) => {
        console.error("Error al actualizar usuario:", error);
        return
      });

    const fila = document.querySelector(`tr[data-id="${idProductoActualizar}"]`);
    fila.innerHTML = `
      <td>${producto.nombre}</td>
      <td>${producto.marca}</td>
      <td>${producto.precio}</td>
      <td>${producto.stock}</td>
      <td>
        <button data-id="${idProductoActualizar}" class="btn btn-primary actualizar" href="myModal">Actualizar</button>
        <button data-id="${idProductoActualizar}" class="btn btn-danger eliminar">Eliminar</button>
      </td>
    `;

    tabla.appendChild(fila);
    document.querySelector("#nombreProductoActualizar").value = "";
    document.querySelector("#marcaProductoActualizar").value = "";
    document.querySelector("#precioProductoActualizar").value = "";
    document.querySelector("#stockProductoActualizar").value = "";
  }
  console.log(respuesta)
}

//GEEEEEEEEEEEEEEEEET
//Aqui va la peticion get
function obtenerProducto() {
  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      data.forEach((producto) => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
          <td>${producto.name}</td>
          <td>${producto.username}</td>
          <td>${producto.address.geo.lat}</td>
          <td>${producto.address.geo.lat}</td>
          <td style="margin-left=30px"><button data-id="${producto.id}" class="btn btn-primary actualizar" data-toggle="modal" data-target="#myModalUpdate">Actualizar</button>
                <button data-id="${producto.id}" class="btn btn-danger eliminar">Eliminar</button></td>
        `;
        tabla.appendChild(fila);
      });
    });
}


obtenerProducto();
//DELETEEEEEEEEEEEEE
// Agregar event listener para el botón "Eliminar"
tabla.addEventListener('click', e => {
  if (e.target.classList.contains('eliminar')) {
    const idUsuario = e.target.dataset.id;
    if (confirm(`¿Seguro que desea eliminar la oferta ${idUsuario}?`)) {
      // Si el usuario confirma que desea eliminar, enviar la solicitud DELETE a la API
      fetch(`https://jsonplaceholder.typicode.com/users/${idUsuario}`, {
        method: 'DELETE'
      })
        .then(response => {
          if (response.ok) {
            // Si la solicitud DELETE tiene éxito, eliminar la fila correspondiente de la tabla
            e.target.closest('tr').remove();
            alert(`La oferta ${idUsuario} eliminado correctamente.`);
          } else {
            throw new Error('No se pudo eliminar la oferta.');
          }
        })
        .catch(error => alert(error.message));
    }
  }
});

// Agregar event listener para el botón "Actualizar"
// Agregar event listener para el botón "Actualizar"
tabla.addEventListener('click', e => {
  if (e.target.classList.contains('actualizar')) {
    const idProducto = e.target.dataset.id;
    // Realizar solicitud fetch para obtener los datos del usuario
    fetch(`https://jsonplaceholder.typicode.com/users/${idProducto}`)
      .then(response => response.json())
      .then(producto => {
        // Llenar los campos del formulario con los datos del usuario
        const formulario = document.querySelector('#formulario-actualizar');
        formulario.querySelector('#nombreProductoActualizar').value = producto.name;
        formulario.querySelector('#marcaProductoActualizar').value = producto.username;
        formulario.querySelector('#precioProductoActualizar').value = producto.address.geo.lat;
        formulario.querySelector('#stockProductoActualizar').value = producto.address.geo.lng;

        // Mostrar la ventana modal

        const modal = new bootstrap.Modal(document.querySelector('#myModalUpdate'));
        modal.show();

        // Agregar evento submit al formulario de la ventana modal
        formulario.addEventListener('submit', e => {
          e.preventDefault();

          const producto = {
            "id": 1,
            "nombre": formulario.querySelector('#nombreProductoActualizar').value,
            "marca": formulario.querySelector('#marcaProductoActualizar').value,
            "precio": formulario.querySelector('#precioProductoActualizar').value,
            "stock": formulario.querySelector('#stockProductoActualizar').value
            //"supermercado":{
            // "id":idSupermercado
            //}
          }
          // Realizar solicitud fetch para actualizar los datos del usuario
          fetch(`https://jsonplaceholder.typicode.com/users/${idProducto}`, {
            method: 'PUT',
            body: JSON.stringify(producto),
            headers: {
              'Content-Type': 'application/json'
            }
          })
            .then(response => response.json())
            .then(productoActualizado => {
              // Actualizar los datos del usuario en la tabla
              const fila = e.target.closest('tr');
              fila.querySelector('td:nth-child(1)').textContent = productoActualizado.name;
              fila.querySelector('td:nth-child(2)').textContent = productoActualizado.username;
              fila.querySelector('td:nth-child(3)').textContent = productoActualizado.address.geo.lat;
              fila.querySelector('td:nth-child(4)').textContent = productoActualizado.address.geo.lng;
              // Cerrar la ventana modal
              //modal.style.display = 'none';
            });
        });
      });
    //.catch(error => alert(error.message));
  }
});

function mostrarProducto(producto) {
  const fila = document.createElement('tr')
  fila.innerHTML = `
        <td>${producto.name}</td>
        <td>${producto.username}</td>
        <td>${producto.address.geo.lat}</td>
        <td>${producto.address.geo.lng}</td>
    `
  respuesta.appendChild(fila)
}

function mostrarProductoID(producto) {
  const fila = document.createElement('tr')
  fila.innerHTML = `
      <td>${producto.id}</td>
      <td>${producto.name}</td>
      <td>${producto.username}</td>
      <td>${producto.address.geo.lat}</td>
      <td>${producto.address.geo.lng}</td>
  `
  respuesta.appendChild(fila)
}

//F U N C I O N E S  P A R A  V A L I D A R
function validarTexto(texto) {
  return /^[a-zA-ZÀ-ÿ\s]+$/.test(texto); //Mayusculas, minusculas, acentos, espacios
}
function validarNumeros(cadena) {
  const regex = /^\d{1,6}$/; //Solo números con hasta 6 dígitos
  return regex.test(cadena);
}
