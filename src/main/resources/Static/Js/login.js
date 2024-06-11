const loginForm = document.getElementById('login-form');

function login {

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Realizar la autenticación del usuario utilizando fetch
    const formData = new URLSearchParams();
    formData.append('username', username);
    formData.append('password', password);

    fetch('http://localhost:8082/users/loginuser', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (response.ok) {
            // El login fue exitoso, redireccionar o realizar acciones adicionales
            console.log('Login successful');
            window.location.href = '/admin'; // Redirigir a la página de administrador
        } else {
            // El login falló, mostrar un mensaje de error al usuario
            console.error('Login failed');
            alert('Login failed. Please check your credentials.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred. Please try again later.');
    });
});
