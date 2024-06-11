const formulario = document.getElementById('crear-jugador');

function submitPersona(evento) {
    evento.preventDefault(); // Previene el comportamiento por defecto del formulario
    const equipoId = document.getElementById('equipo').value;
    const equipo = equipoId ? { 'equipoId': parseInt(equipoId) } : null;

    fetch('http://localhost:8082/jugadores', {
        method: 'post',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify([{
            'nombre': document.getElementById('nombre').value,
            'apellido1': document.getElementById('apellido1').value,
            'apellido2': document.getElementById('apellido2').value,
            'fechaNacimiento': document.getElementById('fechaNacimiento').value,
            'golesTotales': 0,
            'asistenciasTotales': 0,
            'nacionalidad': document.getElementById('nacionalidad').value,
            'equipo': equipo
        }])
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));
}

function resetForm() {
    document.getElementById('crear-jugador').reset();
}
