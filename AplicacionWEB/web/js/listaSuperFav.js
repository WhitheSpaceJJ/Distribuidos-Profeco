const url = 'https://jsonplaceholder.typicode.com/users';
const selectedComments = JSON.parse(localStorage.getItem("selectedComments"));
const selectedCommentsTableBody = document.querySelector("#selected-comments-table tbody");
const urlPostSuperFav ='http://localhost:8060/consumidor/spfavorito';
const urlGetSuperFav ='http://localhost:8060/consumidor/spfavorito';
const urlDeletetSuperFav ='http://localhost:8060/consumidor/spfavorito/{idSpFavorito}';

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

selectedComments.forEach(comment => {
  const row = document.createElement("tr");
  row.appendChild(document.createElement("td")).textContent = comment.name;

//Boton eliminar
  const btnDelete = document.createElement("button");
  btnDelete.textContent = "Eliminar";
  btnDelete.classList.add("btn");
  btnDelete.classList.add("btn-danger")
  const tdDelete = document.createElement("td");
  tdDelete.appendChild(btnDelete);
  row.appendChild(tdDelete);
  selectedCommentsTableBody.appendChild(row);


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

});

//agregar super favorito
fetch(url, {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(supermercadoFavorito)
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