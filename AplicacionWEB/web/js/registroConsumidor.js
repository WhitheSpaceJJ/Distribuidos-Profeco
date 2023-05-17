const registerForm = document.getElementById('myFormrgg');
const urlPutConsumidor = 'http://localhost:8060/consumidor/{idConsumidor}';
const urlDeleteConsumidor = 'http://localhost:8060/consumidor/{idConsumidor}';
const urlPostConsumidor = 'http://localhost:8060/consumidor';
const urlGetConsumidor = 'http://localhost:8060/consumidor';

registerForm.addEventListener('submit', (event) => {
    event.preventDefault(); // evitar que el formulario se envíe automáticamente.

    // Obtener los datos del formulario
    const name = document.getElementById('name').value;
    const age = document.getElementById('age').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmpassword').value;

    // Validar que los campos no estén vacíos y que las contraseñas coincidan
    if (!name || !age || !email || !password || !confirmPassword) {
        alert('Por favor complete todos los campos');
        return;
    }
    const emailRegex = /^\S+@\S+\.\S+$/;
    if (!emailRegex.test(email)) {
        alert('Por favor ingrese una dirección de correo electrónico válida.');
        return;
    }
    if (password !== confirmPassword) {
        alert('Las contraseñas no coinciden');
        return;
    }

    // Guardar los datos del usuario en el localStorage
    const consumidor = {
        "nombre": name,
        "contraseña": password,
        "email": email,
        "edad": age
    }
    
    alert("Bienvenido " + consumidor.nombre);
    
    fetch(urlPostConsumidor, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(consumidor)
    })
        .then(response => response.json())
        .then(data => {
            const currentDate = new Date();
            // Redirigir al usuario a otra página
            const expirationDate = new Date(currentDate.getTime() + (10 * 365 * 24 * 60 * 60 * 1000)); // 10 años en milisegundos
            // Convierte la fecha de expiración a formato UTC
            const expirationUTC = expirationDate.toUTCString();

            document.cookie = `cookieConsumidor=${encodeURIComponent(JSON.stringify(data))}; expires=${expirationUTC}; path=/`;

            window.location.href = 'agregarSuperList.html'
            
        })
        .catch(error => {
            // Manejar errores
            console.error('Error:', error);
            return
        });

    
});

//F U N C I O N E S  P A R A  V A L I D A R
function validarTexto(texto) {
    return /^[a-zA-ZÀ-ÿ\s]+$/.test(texto); //Mayusculas, minusculas, acentos, espacios
}
function validarNumeros(cadena) {
    const regex = /^\d{1,6}$/; //Solo números con hasta 6 dígitos
    return regex.test(cadena);
}