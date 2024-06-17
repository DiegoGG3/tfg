const formulario = document.getElementById('crear-campeonato');

function submitCampeonato(evento) {
    evento.preventDefault(); // Prevenir el comportamiento por defecto del formulario

    const equiposSeleccionados = Array.from(document.getElementById('equipos').selectedOptions).map(option => ({
        equipoId: parseInt(option.value)
    }));

    const campeonatoData = {
        nombre: document.getElementById('nombre').value,
        formato: document.getElementById('formato').checked,
        fechaInicio: document.getElementById('fechaInicio').value,
        ganador: null,
        foto: null,
        pais: document.getElementById('pais').value,
        equipos: equiposSeleccionados
    };

    fetch('http://localhost:8082/campeonatos', {
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(campeonatoData)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));
}

function resetForm() {
    document.getElementById('crear-campeonato').reset();
}
