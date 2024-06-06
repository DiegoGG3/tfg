const formulario = document.getElementById('crear-equipo');

function submitEquipo(evento) {
    console.log(evento);
    fetch('http://localhost:8082/equipos', {
        method: 'post',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify([{
            'nombre': document.getElementById('nombre').value,
            'anoFundacion': document.getElementById('anoFundacion').value,
            'presidente': document.getElementById('presidente').value,
            'numeroPremios': 0,
            'fotoEscudo': document.getElementById('fotoEscudo').value,
            'estadio': document.getElementById('estadio').value,
        }])
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));
}

function resetForm() {
    document.getElementById('crear-equipo').reset();
}
