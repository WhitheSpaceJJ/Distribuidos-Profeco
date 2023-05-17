const url = 'https://jsonplaceholder.typicode.com/comments';
const respuesta = document.querySelector("#respuesta")
const urlPutComentario='http://localhost:8060/supermercado/comentario/{idComentario}';
const urlPostComentario='http://localhost:8060/supermercado/comentario';
const urlGetComentario='http://localhost:8060/supermercado/comentario';
const urlDeleteComentario='http://localhost:8060/supermercado/comentario/{idComentario}';

//Evento
document.addEventListener("DOMContentLoaded", llamrAPI)

async function llamrAPI(){
    const respuesta = await fetch(url)
    const datos = await respuesta.json()
    mostrarCuerpo(datos)
}

function mostrarCuerpo(datos){
    datos.forEach(element => {
        const fila = document.createElement('tr')
        fila.innerHTML= `
        <td>${element.name}</td>
        <td>${element.body}</td>
        `
        respuesta.appendChild(fila)
    });
}

// DESCOMENTARIAR - MAPEO Y MÉTODO POST
/*
//de momento, no es la solución correcta
const comentario = datos.element.body

function agregarComentario(){
    const comentariosSupermercado = {

        //"id": Math.floor(Math.random() * 1000),
        //"consumidor":{
            //"id": 
            //idConsumidor
        //},
        "mensaje":comentario
        //"supermercado":{
            //"id": 
            //idSupermercado
        //}
    }

    let idProductoAgregado = 0
    fetch(url, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(comentariosSupermercado)
    })
    .then(response => response.json())
    .then(data => {
        idProductoAgregado = data.id
        console.log(comentariosSupermercado);
    })
    .catch(error => {
        // Manejar errores
        console.error('Error:', error);
        return
    });

}// función agregar comentario
*/ // DESCOMENTARIAR PARA TENER EL MAPEO Y EL POST

    //PUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUT
    //Este es para actualizar el producto en el supermercado
    /*fetch(url, {
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
    */

   //DELETE
    /*fetch(`http://localhost:8060/supermercado/comentario/{idComentario}`, {
    method: 'DELETE'
    })*/
