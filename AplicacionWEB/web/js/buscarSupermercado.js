
//SE BUSCA CON EL NOMBRE DEL USUARIO


const form = document.getElementById('search-form');
const input = document.getElementById('search-input');
const results = document.getElementById('results');

form.addEventListener('submit', function(event) {
  event.preventDefault();
  const searchTerm = input.value.trim();
  if (!searchTerm) return;
//SE BUSCA POR NAME DEL USUARIO DE LA API
  fetch(`https://jsonplaceholder.typicode.com/users?name=${searchTerm}`)
    .then(function(response) {
      return response.json();
    })
    .then(function(data) {
      //CUANDO NO ENCUENTRE EL SUPER O NO EXISTA EN LA API
      results.innerHTML = '';
      if (data.length === 0) {
      alert('No se encontraron el supermercado');

        results.innerHTML = '<p>No se encontraron el supermercado</p>';
        console.log("NO EXISTE");
        return;
      }
      //CUANDO SI EXISTA EL SUPERMERCADO EN LA API
      const user = data[0];

      //EMPIEZA A CONSUMIR LOS DATOS DE LA API
      const name = document.createElement('p');
      name.textContent = `Nombre: ${user.name}`;
      results.appendChild(name);

      const username = document.createElement('p');
      username.textContent = `Nombre de usuario: ${user.username}`;
      results.appendChild(username);

      const email = document.createElement('p');
      email.textContent = `Email: ${user.email}`;
      results.appendChild(email);

      const phone = document.createElement('p');
      phone.textContent = `Teléfono: ${user.phone}`;
      results.appendChild(phone);

      const adrees = document.createElement('p');
            adrees.textContent = `Dirección: ${user.address.street}`;
            results.appendChild(adrees);

      results.style.display = 'block';
      console.log(" EXISTE");
    })
    .catch(function(error) {
      console.log(error);
      results.innerHTML = '<p>Ha ocurrido un error al buscar el supermercado</p>';
      alert('Ha ocurrido un error al buscar el supermercado');
    });
});
