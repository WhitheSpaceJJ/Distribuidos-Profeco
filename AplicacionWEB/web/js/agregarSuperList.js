const URL_API = 'https://jsonplaceholder.typicode.com/comments';
const buscador = document.getElementById('buscador');
const tablaSupermercado = document.getElementById('tabla-prductos');
const supermercadoEndpointGET = 'http://localhost:8060/supermercado';
const consumidorEndpontPOST = 'http://localhost:8060/consumidor/spfavorito';
const tabla = document.getElementById('table');

let supermercados = [];

//BUSCAA
const buscar = () => {
    const textoBusqueda = buscador.value.toLowerCase();

    // Filtrar los supermercado por nombre, precio o super
    const supermercadosFiltrados = supermercados.filter(supermercado => {
        const nombre = supermercado.nombre.toLowerCase();
        return nombre.includes(textoBusqueda);
    });

    // Mostrar los supermercado en la tabla
    mostrarSupermercados(supermercadosFiltrados);
};

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

        nombre.textContent = supermercado.nombre;

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
fetch(supermercadoEndpointGET)
    .then(response => response.json())
    .then(data => {
        supermercados = data;
        mostrarSupermercados(supermercados)
    })
    .catch(error => console.error(error));

function showSelectedComments() {
    const checkboxes = document.querySelectorAll('input[name="comment"]:checked');

    const consumidor = getCookie('cookieConsumidor');
    const decodedToken = decodeURIComponent(consumidor);
    const consumidorObjeto = JSON.parse(decodedToken);


    // añadir los nuevos supermercado seleccionados a los existentes en localStorage
    checkboxes.forEach(checkbox => {
        const supermercado = JSON.parse(checkbox.value);

        const spFavorito = {
            "supermercadoId": supermercado.idSupermercados,
            "consumidorId":{
                "idConsumidores": consumidorObjeto.idConsumidores
            }
        }

        fetch(consumidorEndpontPOST, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(spFavorito)
        })
            .then(response => response.json())
            .then(data => {
                window.location.href = 'listaSuperFav.html'

            })
            .catch(error => {
                // Manejar errores
                console.error('Error:', error);
                return
            });


        if (!selectSupermercados.find(selectSuper => selectSuper.id === supermercado.id)) {
            selectSupermercados.push(supermercado);
        }
    });


    if (selectSupermercados.length > 0) {
        window.location.href = "listaSuperFav.html";
    } else {
        alert("Por favor, selecciona al menos un producto.");
    }
}

const url = URL_API;
//const consumidor = {
//    //"id_consumidores": Math.floor(Math.random() * 1000),
//    "nombre": nombre,
//    "contraseña": marca,
//    "email": precio,
//    "edad": stock,
//    //"id": consumidorId,
//    //"wishList":{
//    // "id":consumidorId
//    //}
//    //"supermercadosFavoritosList":{
//    // "id":consumidorId
//    //}
//};


