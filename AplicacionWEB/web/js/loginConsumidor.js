const loginForm = document.getElementById('login-form');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const consumidor = 'http://localhost:8060/consumidor'


loginForm.addEventListener('submit', (event) => {
  event.preventDefault();

  const username = usernameInput.value;
  const password = passwordInput.value;

  fetch(consumidor, {
    method: "GET",
    headers: {
      "Content-Type": "application/json"
    },
  })
    .then((response) => response.json())
    .then((data) => {
      
      data.forEach(c => {
        if(c.nombre === username && c.contraseña === password){
          const currentDate = new Date();
          // Redirigir al usuario a otra página
          const expirationDate = new Date(currentDate.getTime() + (10 * 365 * 24 * 60 * 60 * 1000)); // 10 años en milisegundos
          // Convierte la fecha de expiración a formato UTC
          const expirationUTC = expirationDate.toUTCString();

          document.cookie = `cookieConsumidor=${encodeURIComponent(JSON.stringify(c))}; expires=${expirationUTC}; path=/`;

          window.location.href = 'agregarSuperList.html'
        }
      })

    })
    .catch((error) => {
      console.error("Error al actualizar usuario:", error);
      return
    });

})

