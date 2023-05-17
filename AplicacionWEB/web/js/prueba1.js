  const url = 'https://jsonplaceholder.typicode.com/comments';
// Obtener los datos de los usuarios
fetch(url)
  .then(response => response.json())
  .then(data => {
    const nombres = data.map(user => user.name);

    // Agregar las opciones al select
    const select = document.getElementById('supermercados');
    nombres.forEach(nombre => {
      const option = document.createElement('option');
      option.text = nombre;
      select.add(option);
    });
  })
  .catch(error => console.log(error));

// Agregar un listener al botón de guardar
const guardarBtn = document.getElementById('guardar');
guardarBtn.addEventListener('click', () => {
  const usuario = document.getElementById('supermercados').value;
  const comentario = document.getElementById('comentario').value;

  // Obtener los comentarios existentes de localStorage
  const comentarios = JSON.parse(localStorage.getItem('comentarios')) || [];

  // Agregar el nuevo comentario al arreglo
  comentarios.push({usuario, comentario});

  // Guardar los comentarios en localStorage
  localStorage.setItem('comentarios', JSON.stringify(comentarios));

  // Redirigir a la otra pantalla
  window.location.href = 'prueba2.html';
});
