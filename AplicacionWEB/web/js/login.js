const loginForm = document.getElementById('login-form');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');

loginForm.addEventListener('submit', (event) => {
  event.preventDefault();

  const username = usernameInput.value;
  const password = passwordInput.value;


  fetch(`https://jsonplaceholder.typicode.com/users?email=${username}`)
    .then(response => response.json())
    .then(users => {
      const user = users[0];
      //INICIA SESIÓN CON EL EMAIL Y EL USERNAME DEL USUARO DE LA API
      if(user && user.email === username && user.username === password){


        // DEPENDIENDO EL USUARIO SE ABRIRA LAS PANTALLAS DEPENDIENDO EL ID DEL USUARIO
        if (user.id === 1) {
          window.location.href = 'ProFeCo.html';//Iniciando sesion como profeco
        } else if (user.id === 2) {
          window.location.href = 'supermercado.html';//Editar dependiendo a que pantalla iran si es a la profeco, consumidor o super
        } else if (user.id === 3) {
          window.location.href = 'consumidor.html';//Editar dependiendo a que pantalla iran si es a la profeco, consumidor o super
        } else {
          alert('Usuario no autorizado');
        }
      } else {

        alert('No se pueder iniciar sesión');
      }
    })
    .catch(error => {
      console.error(error);
      alert('Ha ocurrido un error');
    });
});
