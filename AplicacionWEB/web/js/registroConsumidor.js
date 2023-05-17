const registerForm = document.getElementById('myFormrgg');
const urlPutConsumidor = 'http://localhost:8060/consumidor/{idConsumidor}';
const urlDeleteConsumidor = 'http://localhost:8060/consumidor/{idConsumidor}';
const urlPostConsumidor = 'http://localhost:8060/consumidor';
const urlGetConsumidor = 'http://localhost:8060/consumidor';
registerForm.addEventListener('submit', (event) => {
  event.preventDefault(); // evitar que el formulario se envíe automáticamente.

  // Obtener los datos del formulario
  const name = document.getElementById('name').value;
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  const confirmPassword = document.getElementById('confirmpassword').value;
  
  // Validar que los campos no estén vacíos y que las contraseñas coincidan
  if (!name || !email || !password || !confirmPassword) {
    alert('Por favor complete todos los campos');
    return;
  }
  const emailRegex = /^\S+@\S+\.\S+$/;
  if (!emailRegex.test(email)) {
    alert('Por favor ingrese una dirección de correo electrónico válida.');
    return;
  }
  if (password !== confirmPassword) {
    alert('Las contraseñas no coinciden');
    return;
  }

  // Guardar los datos del usuario en el localStorage
  const usuario = {
    name,
    email,
    password
  };
  console.log(usuario)
  localStorage.setItem('usuario', JSON.stringify(usuario));
  const usuarioActivo = JSON.parse(localStorage.getItem('usuario'));
  const nombreUsuario = usuarioActivo.name;
  //const bienvenida = document.getElementById('bienvenida');
  if(nombreUsuario){
    alert("Bienvenido "+nombreUsuario);
}
  // Redirigir al usuario a otra página
  window.location.href = 'agregarSuperList.html';
});



tabla.addEventListener('click', e => {
  if (e.target.classList.contains('actualizar')) {
    const idUsuario = e.target.dataset.id;
    // Realizar solicitud fetch para obtener los datos del usuario
    fetch(`https://jsonplaceholder.typicode.com/users/${idUsuario}`)
      .then(response => response.json())
      .then(usuario => {
        // Llenar los campos del formulario con los datos del consumidor
        const formulario = document.querySelector('#formulario-actualizar');
        formulario.querySelector('#nombreConsumidorActualizar').value = usuario.name;
        formulario.querySelector('#contraseñaConsumidorActualizar').value = usuario.username;
        formulario.querySelector('#edadConsumidorActualizar').value = usuario.address.geo.lat;
        formulario.querySelector('#edadConsumidorActualizar').value = usuario.address.geo.lng;

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
            body: JSON.stringify(consumidorActualizado),
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
              fila.querySelector('td:nth-child(3)').textContent = consumidorActualizado.address.geo.lat;
              fila.querySelector('td:nth-child(4)').textContent = consumidorActualizado.address.geo.lng;
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
            throw new Error('No se pudo eliminar el consumidor.');
          }
        })
        .catch(error => alert(error.message));
    }
  }
});

/*
const consumidorActualizado = {
  "id": 1,
  "nombre": formulario.querySelector('#nombreConsumidorActualizar').value,
  "contraseña": formulario.querySelector('#contraseñaConsumidorActualizar').value,
  "email":formulario.querySelector('#emailConsumidorActualizar').value,
  "edad":formulario.querySelector('#edadConsumidorActualizar').value
  //"supermercado":{
     // "id":idSupermercado
  //}
}
*/

/*
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
};*/


/*
fetch(url)
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error(error));

    function agregarConsumidor() {
    const nombre = document.querySelector("#nombre").value
    const marca = document.querySelector("#contraseña").value
    const precio = document.querySelector("#email").value
    const stock = document.querySelector("#edad").value

    // este es para agregar consumidores a los supermercados

    //Esto iba comentado hasta . . . 
    let idConsumidorAgregado = 0
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(consumidor)
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
      });
      //. . . . Aquí

    const fila = document.createElement("tr");
            console.log(respuesta)
            const nombreValido = validarTexto(consumidor.nombre);
            const contraseniaValida = validarTexto(consumidor.contraseña);
            const emailValido = validarTexto(consumidor.email);
            const edadValida = validarNumeros(consumidor.edad);
            if (!nombreValido) {
              alert("Debe introducir un nombre válido");
            } else if (!contraseniaValida) {
              alert("Error al guardar contraseña");
              document.querySelector("#contraseña").value = "";
            } else if (!emailValido) {
              alert("Ingrese un email valido");
              document.querySelector("#email").value = "";
            } else if (!edadValida) {
              alert("Ingrese una edad valida");
              document.querySelector("#edad").value = "";
            } else {
              // Crear fila y agregar a tabla
              const fila = document.createElement("tr");
              fila.innerHTML = `
                <td>${consumidor.nombre}</td>
                <td>${consumidor.nombre}</td>
                <td>${consumidor.marca}</td>
                <td>${consumidor.precio}</td>
                <td>${consumidor.stock}</td>
                <td>
                  <button data-id="${consumidor.id}" class="btn btn-primary actualizar" href="myModal">Actualizar</button>
                  <button data-id="${consumidor.id}" class="btn btn-danger eliminar">Eliminar</button>
                </td>
              `;
              tabla.appendChild(fila);
              document.querySelector("#nombre").value="";
              document.querySelector("#contraseña").value="";
              document.querySelector("#email").value="";
              document.querySelector("#edad").value="";
            }
    console.log(respuesta)
}
*/









//F U N C I O N E S  P A R A  V A L I D A R
function validarTexto(texto) {
  return /^[a-zA-ZÀ-ÿ\s]+$/.test(texto); //Mayusculas, minusculas, acentos, espacios
}
function validarNumeros(cadena) {
  const regex = /^\d{1,6}$/; //Solo números con hasta 6 dígitos
  return regex.test(cadena);
}