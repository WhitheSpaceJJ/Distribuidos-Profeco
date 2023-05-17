const url = 'https://jsonplaceholder.typicode.com/users';
const selectedComments = JSON.parse(localStorage.getItem("selectedComments"));
const selectedCommentsTableBody = document.querySelector("#selected-comments-table tbody");
const urlPostSuperFav ='http://localhost:8060/consumidor/spfavorito';
const urlGetSuperFav ='http://localhost:8060/consumidor/spfavorito';
const urlDeletetSuperFav ='http://localhost:8060/consumidor/spfavorito/{idSpFavorito}';

const urlGetSupermercados = 'http://localhost:8060/supermercado'

const btnDelete = document.createElement("button");

let supermercados = []

const supermercadoFavorito  = {
    //"id": Math.floor(Math.random() * 1000),
    //"id": id_favoritos
    //"supermercado":{
      // "id":idSupermercado
    //}
    //"consumidor":{
      // "id":consumidorId
    //}
}

const supermercadoFavoritoActualizado  = {
  //"id": Math.floor(Math.random() * 1000),
  //"id": id_favoritos
  //"supermercado":{
    // "id":idSupermercado
  //}
  //"consumidor":{
    // "id":consumidorId
  //}
}
cargarSpFavoritos()

function getCookie(name) {
  const cookies = document.cookie.split(';').map(cookie => cookie.trim());
  for (const cookie of cookies) {
    const [cookieName, cookieValue] = cookie.split('=');
    if (cookieName === name) {
      return cookieValue;
    }
  }
  return null;
}

function cargarSpFavoritos(){
  fetch(urlGetSupermercados, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    },
  })
    .then(response => response.json())
    .then(data => {
      supermercados = data
      const cookieConsumidor = getCookie('cookieConsumidor')
      const decodedToken = decodeURIComponent(cookieConsumidor);
      const consumidorObjeto = JSON.parse(decodedToken);

      if (consumidorObjeto.supermercadosfavoritosList == null) {
        alert("Aún no has agregado supermercados favoritos!")
        return
      }

      const misSpFavoritos = consumidorObjeto.supermercadosfavoritosList
      const tabla = document.querySelector("table tbody");

      misSpFavoritos.forEach(spFavorito => {
        const row = document.createElement("tr");
        const nombreSp = supermercados.filter(s => s.idSupermercados === spFavorito.supermercadoId)[0]

        const fila = document.querySelector('tabla-listaSuper');
        fila.innerHTML = `
        <td>${nombreSp.nombre}</td>
      <td>
        <button data-id="${nombreSp.idSupermercados}" class="btn btn-danger eliminar">Eliminar</button>
      </td>
    `;

        tabla.appendChild(fila);
      });
    })
  //agregar super favorito

  
      

}


//Accion para que elimine un producto de la wish
btnDelete.addEventListener("click", () => {
  if (confirm("¿Está seguro que desea eliminar este supermercado?")) {
    // Eliminar fila de la tabla
    row.remove();
    // Eliminar comentario del almacenamiento local
    const index = selectedComments.indexOf(comment);
    selectedComments.splice(index, 1);
    localStorage.setItem("selectedComments", JSON.stringify(selectedComments));
  }
});



//actualizar super favorito
  fetch(url, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(supermercadoFavoritoActualizado)
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

    //DELETE
    /*fetch(`http://localhost:8060/supermercado/comentario/{idComentario}`, {
    method: 'DELETE'
    })*/