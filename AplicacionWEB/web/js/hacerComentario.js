// obtener los comentarios guardados en el localStorage
let comentarios = JSON.parse(localStorage.getItem('comentarios')) || [];

// agregar los comentarios a la tabla
const commentTable = document.getElementById('comment-table');
// obtener la selección del usuario guardada en el localStorage (si existe)
let selectedSuper = localStorage.getItem('selectedSuper') || '';
const urlPutWishList ='http://localhost:8060/consumidor/wishlist/{idWishlist}';
const urlPostWishList ='http://localhost:8060/consumidor/wishlist';
const urlGetWishList ='http://localhost:8060/consumidor/wishlist';
const urlDeleteWishList ='http://localhost:8060/consumidor/wishlist/{idWishlist}';

/* Mapeo de wishList
const comentario = {
  //"id": Math.floor(Math.random() * 1000),
  "deseo":"deseo",
  "supermercadoId":supermercado_id,
  "consumidorId":{
      "idConsumidores":consumidor_id
  }
}

const comentarioActualizar = {
  //"id": Math.floor(Math.random() * 1000),
  "deseo":"deseo",
  "supermercadoId":supermercado_id,
  "consumidorId":{
      "idConsumidores":consumidor_id
  }
}
*/

// Agregar las filas con los comentarios
comentarios.forEach(comentario => {
  const row = commentTable.insertRow(1); // insertar después de la fila de los nombres de las columnas
  const superWor = row.insertCell();
  const commentCell = row.insertCell();
  superWor.textContent = comentario.usuario;
  commentCell.textContent = comentario.comentario;
});
commentTable.innerHTML = '';

//GET
fetch('https://jsonplaceholder.typicode.com/comments')
  .then(response => response.json())
  .then(comments => {
    // obtener los supermercados
    const superMercado = [...new Set(comments.map(comment => comment.name))];

    // rellenar el combo con los super
    const superSelect = document.getElementById('super-select');
    superMercado.forEach(superMercado => {
      const option = document.createElement('option');
      option.value = superMercado;
      option.textContent = superMercado;
      superSelect.appendChild(option);


      ///Puede ir también aqui el EVENT de actualizar

    });


    //Actualiza la tabla cuando seleccionas un supermercado en especifico
    superSelect.addEventListener('change', event => {
      const superSeleccionado = event.target.value;
      const filtroComentarios = comentarios.filter(comentario => comentario.usuario === superSeleccionado);
      commentTable.innerHTML = ''; // Limpiar la tabla antes de agregar los comentarios filtrados

      // Agregar una fila para los nombres de las columnas
      const headerRow = commentTable.insertRow(0);
      const usuarioHeader = headerRow.insertCell();
      const commentHeader = headerRow.insertCell();
      usuarioHeader.textContent = "Usuario";
      commentHeader.textContent = "Comentario";

      // Agregar las filas con los comentarios filtrados
      filtroComentarios.forEach(comentario => {
        const row = commentTable.insertRow(1); // insertar después de la fila de los nombres de las columnas
        const usuarioRow = row.insertCell();
        const comentRow = row.insertCell();
        usuarioRow.textContent = comentario.usuario;
        comentRow.textContent = comentario.comentario;
      });
    });


    superSelect.addEventListener('change', event => {
      const selectedSuper = event.target.value;
      const filteredComments = comentarios.filter(comentario => comentario.usuario === selectedSuper);
      commentTable.innerHTML = ''; // Limpiar la tabla antes de agregar los comentarios filtrados
      // Agregar una fila para los nombres de las columnas
      const headerRow = commentTable.insertRow(0);
      const superHeader = headerRow.insertCell();
      const commentHeader = headerRow.insertCell();

      //Cambios, letras en negrita
      superHeader.innerHTML = "<b>Supermercado</b>";
      commentHeader.innerHTML = "<b>Comentario</b>";

      //superHeader.textContent = "Supermercado";
      //commentHeader.textContent = "Comentario";

      // Agregar las filas con los comentarios filtrados
      filteredComments.forEach(comentario => {
        const row = commentTable.insertRow(1); // insertar después de la fila de los nombres de las columnas
        const usuario = row.insertCell();
        const commentCell = row.insertCell();
        usuario.textContent = comentario.usuario;
        commentCell.textContent = comentario.comentario;
      });
    });



    // manejar el envío del formulario
    const commentForm = document.getElementById('comment-form');
    commentForm.addEventListener('submit', event => {
      event.preventDefault();

      // obtener los datos del formulario
      const supermercadoo = superSelect.value;
      const comment = document.getElementById('comment-input').value;

      // validar que el comentario no esté vacío y no haya seleccionado un super
      if (supermercadoo.trim() === '') {
        alert('Por favor seleccione un supermercado.');
        return;
      }
      if (comment.trim() === '') {
        alert('Por favor ingrese un comentario.');
        return;
      }

      // agregar los datos a la tabla
      const row = commentTable.insertRow(1); // insertar después de la fila de los nombres de las columnas
      const superRow = row.insertCell();
      const commentCell = row.insertCell();
      superRow.textContent = supermercadoo;
      commentCell.textContent = comment;

      // agregar la selección del usuario como un campo oculto
      const selectionInput = document.createElement('input');
      selectionInput.type = 'hidden';
      selectionInput.name = 'selection';
      selectionInput.value = supermercadoo;
      commentForm.appendChild(selectionInput);

      // guardar el comentario en el localStorage
      const comentario = { usuario: supermercadoo, comentario: comment };
      comentarios.unshift(comentario); // agregar al principio del array
      localStorage.setItem('comentarios', JSON.stringify(comentarios));

      // mostrar mensaje de éxito
      showMessage();

      // limpiar el formulario
      superSelect.selectedIndex = 0;
      document.getElementById('comment-input').value = '';

    });
  });

function showMessage() {
  const messageDiv = document.getElementById('message');
  messageDiv.style.display = 'block';
  setTimeout(() => {
    messageDiv.style.display = 'none';
  }, 3000); // Mostrar durante 1 segundos
}

//PO0000000000000000ST
  /**
      fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(comentario)
      })
        .then(response => response.json())
        .then(data => {
          console.log(data);
        })
        .catch(error => {
          // Manejar errores
          console.error('Error:', error);
          return
        });*/

//PUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUT
    /*fetch(url, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(comentarioActualizar)
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
    */

   //DELETE
    /*fetch(`http://localhost:8060/consumidor/wishlist/{idWishlist}`, {
    method: 'DELETE'
    })*/