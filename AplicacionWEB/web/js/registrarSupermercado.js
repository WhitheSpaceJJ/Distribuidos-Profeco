const url = 'https://jsonplaceholder.typicode.com/users';
const form = document.getElementById('myFormrg');
const urlPutSupermercado ='http://localhost:8060/supermercado/{idSupermercado}';
const urlPostSupermercado ='http://localhost:8060/supermercado';
const urlGetSupermercado ='http://localhost:8060/supermercado';
const urlDeleteSupermercado ='http://localhost:8060/supermercado/{idSupermercado}';

form.addEventListener('submit', (event) => {
  event.preventDefault();
  
    // Obtener los valores de los campos
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const email = document.getElementById('email').value;
  const pwd = document.getElementById('pwd').value;
  const cpwd = document.getElementById('cpwd').value;

    // Verificar que todos los campos tengan un valor
  if (!nombre || !apellido || !email || !pwd || !cpwd) {
    alert('Por favor complete todos los campos.');
    return;
  }

  // Verificar que la dirección de correo electrónico sea válida
  const emailRegex = /^\S+@\S+\.\S+$/;
  if (!emailRegex.test(email)) {
    alert('Por favor ingrese una dirección de correo electrónico válida.');
    return;
  }

  // Verificar que la contraseña y la confirmación sean iguales
  if (pwd !== cpwd) {
    alert('La contraseña y la confirmación no coinciden.');
    return;
  }

  // Guardar los datos del usuario en el localStorage
  const usuario = {
    nombre,
    apellido,
    email,
    pwd
  };

  localStorage.setItem('usuario', JSON.stringify(usuario));
  const usuarioActivo = JSON.parse(localStorage.getItem('usuario'));
  const nombreUsuario = usuarioActivo.nombre;
  //const bienvenida = document.getElementById('bienvenida');
  if(nombreUsuario){
    alert("Bienvenido "+nombreUsuario);
  }
  window.location.href = 'agregarProducto.html';
  //bienvenida.innerHTML = `¡Bienvenido/a, ${nombreUsuario}!`;
  //    console.log(bienvenida)
  });

//endpoint supermercado
const supermercado = {
  "id": 1,
  "nombre": formulario.querySelector('#nombreSupermercadoActualizar').value,
  "contraseña": formulario.querySelector('#direccionSupermercadoActualizar').value,
  //"supermercado":{
     // "id":idSupermercado
  //}
}

const supermercadoActualizado = {
  "id": 1,
  "nombre": formulario.querySelector('#nombreSupermercadoActualizar').value,
  "contraseña": formulario.querySelector('#direccionSupermercadoActualizar').value,
  //"supermercado":{
     // "id":idSupermercado
  //}
}

fetch(url)
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error(error));

  function agregarSupermercado() {
    const nombre = document.querySelector("#nombre").value
    const marca = document.querySelector("#direccion").value


    // este es para agregar supermercados
/**
    let idSupermercadoAgregado = 0
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(supermercado)
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
            const nombreValido = validarTexto(consumidor.nombre);
            const direccionValida = validarTexto(consumidor.contraseña);
            if (!nombreValido) {
              alert("Debe introducir un nombre válido");
            } else if (!direccionValida) {
              alert("Error al guardar direccion");
              document.querySelector("#direccion").value = "";
            } else {
              // Crear fila y agregar a tabla
              const fila = document.createElement("tr");
              fila.innerHTML = `
                <td>${supermercado.nombre}</td>
                <td>${supermercado.direccion}</td>
                <td>
                  <button data-id="${supermercado.id}" class="btn btn-primary actualizar" href="myModal">Actualizar</button>
                  <button data-id="${supermercado.id}" class="btn btn-danger eliminar">Eliminar</button>
                </td>
              `;
              tabla.appendChild(fila);
              document.querySelector("#nombre").value="";
              document.querySelector("#direccion").value="";
            }
    console.log(respuesta)
}

tabla.addEventListener('click', e => {
  if (e.target.classList.contains('actualizar')) {
    const idSupermercado = e.target.dataset.id;
    // Realizar solicitud fetch para obtener los datos del usuario
    fetch(`https://jsonplaceholder.typicode.com/users/${idUsuario}`)
      .then(response => response.json())
      .then(usuario => {
        // Llenar los campos del formulario con los datos del consumidor
        const formulario = document.querySelector('#formulario-actualizar');
        formulario.querySelector('#nombreSupermercadoActualizar').value = usuario.name;
        formulario.querySelector('#direccionSupermercadoActualizar').value = usuario.username;

        // Mostrar la ventana modal

        const modal = new bootstrap.Modal(document.querySelector('#myModalUpdate'));
        modal.show();

        // Agregar evento submit al formulario de la ventana modal
        formulario.addEventListener('submit', e => {
          e.preventDefault();
          
          //PUT
          // Realizar solicitud fetch para actualizar los datos del usuario
          fetch(`https://jsonplaceholder.typicode.com/users/${idUsuario}`, {
            method: 'PUT',
            body: JSON.stringify(supermercadoActualizado),
            headers: {
              'Content-Type': 'application/json'
            }
          })
            .then(response => response.json())
            .then(consumidorActualizado => {
              // Actualizar los datos del usuario en la tabla
              const fila = e.target.closest('tr');
              fila.querySelector('td:nth-child(1)').textContent = consumidorActualizado.name;
              fila.querySelector('td:nth-child(2)').textContent = consumidorActualizado.username;
              // Cerrar la ventana modal
              //modal.style.display = 'none';
            });
        });
      });
      //.catch(error => alert(error.message));
  }
});

//DELETE
tabla.addEventListener('click', e => {
  if (e.target.classList.contains('eliminar')) {
    const idSupermercado = e.target.dataset.id;
    if (confirm(`¿Seguro que desea eliminar el supermercado ${idSupermercado}?`)) {
      // Si el usuario confirma que desea eliminar, enviar la solicitud DELETE a la API
      fetch(`https://jsonplaceholder.typicode.com/users/${idSupermercado}`, {
        method: 'DELETE'
      })
        .then(response => {
          if (response.ok) {
            // Si la solicitud DELETE tiene éxito, eliminar la fila correspondiente de la tabla
            e.target.closest('tr').remove();
            alert(`El supermercado ${idSupermercado} eliminado correctamente.`);
          } else {
            throw new Error('No se pudo eliminar el supermercado.');
          }
        })
        .catch(error => alert(error.message));
    }
  }
});

//F U N C I O N E S  P A R A  V A L I D A R
function validarTexto(texto) {
  return /^[a-zA-ZÀ-ÿ\s]+$/.test(texto); //Mayusculas, minusculas, acentos, espacios
}
function validarNumeros(cadena) {
  const regex = /^\d{1,6}$/; //Solo números con hasta 6 dígitos
  return regex.test(cadena);
}
