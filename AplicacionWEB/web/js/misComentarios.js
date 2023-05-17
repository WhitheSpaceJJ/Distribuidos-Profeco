//const comentarios = JSON.parse(localStorage.getItem('comentarios')) || [];
//const tabla = document.querySelector("table tbody");

const comentarios = JSON.parse(localStorage.getItem('comentarios')) || [];
const tabla = document.getElementById('tabla-comentarios');

if (comentarios.length > 0) {
  tabla.innerHTML = comentarios.map(comentario => `
    <tr>
      <td >${comentario.usuario}</td>
      <td >${comentario.comentario}</td>
      <td >
        <button class="btn btn-primary editar">Editar</button>
        <button class="btn btn-danger eliminar">Eliminar</button>
      </td>
    </tr>
  `).join('');
} else {
  const mensaje = document.createElement('p');
  mensaje.innerText = 'No se encontraron comentarios.';
  tabla.appendChild(mensaje);
}

//boton eliminar
const botonesEliminar = document.querySelectorAll('.eliminar');

botonesEliminar.forEach((boton, index) => {
  boton.addEventListener('click', () => {
    // Obtener el comentario a eliminar
    const comentarioEliminar = comentarios[index];

    // Mostrar un mensaje de confirmación
    if (confirm('¿Está seguro que desea eliminar este comentario?')) {
      // Eliminar el comentario de la lista y actualizar localStorage
      comentarios.splice(index, 1);
      localStorage.setItem('comentarios', JSON.stringify(comentarios));

      // Eliminar la fila correspondiente a la tabla
      boton.parentNode.parentNode.remove();

      // Recargar la página para actualizar la vista
      //location.reload();
    } else {
      // Si el usuario cancela, no hacer nada
      return;
    }
  });
});



// boton actualizar
const botonesEditar = document.querySelectorAll('.editar');

// Obtener el modal
  const modal = document.getElementById('modal');

  // Obtener los elementos del formulario dentro del modal
  const usuarioInput = modal.querySelector('#usuario');
  const comentarioInput = modal.querySelector('#comentario');
 // Manejar el evento de clic en los botones de editar
 botonesEditar.forEach((boton, index) => {
   boton.addEventListener('click', () => {
     // Obtener el comentario a editar
     const comentarioEditar = comentarios[index];

     // Establecer los valores iniciales en el formulario del modal
     usuarioInput.value = comentarioEditar.usuario;
     usuarioInput.setAttribute('readonly', true);
     comentarioInput.value = comentarioEditar.comentario;

     // Mostrar el modal
     modal.showModal();

     // Manejar el envío del formulario
     modal.querySelector('form').addEventListener('submit', event => {
       event.preventDefault();

       // Obtener los nuevos datos del formulario
       const nuevoUsuario = usuarioInput.value;
       const nuevoComentario = comentarioInput.value;

       // Actualizar el comentario en la matriz de comentarios
       comentarios[index].usuario = nuevoUsuario;
       comentarios[index].comentario = nuevoComentario;

       // Volver a guardar la matriz actualizada en localStorage
       localStorage.setItem('comentarios', JSON.stringify(comentarios));

       // Cerrar el modal
       modal.close();

       // Recargar la página
       location.reload();
     });
   });
 });

//boton de cancelar del model
 const botonCancelar = document.querySelector('#cancelar');
      botonCancelar.addEventListener('click', () => {
        modal.close();
      });
