function submitEquipo(event) {
    event.preventDefault();

    const form = document.getElementById('crear-equipo');
    const formData = new FormData();

    const equipoInputDTO = {
        nombre: form.nombre.value,
        anoFundacion: form.anoFundacion.value,
        presidente: form.presidente.value,
        numeroPremios: 0,
        fotoEscudo: form.fotoEscudo.files[0].name, // Set by the server
        estadio: form.estadio.value,
    };

    formData.append('equipos', JSON.stringify([equipoInputDTO]));
    formData.append('file', form.fotoEscudo.files[0]);

    fetch('http://localhost:8082/equipos/crear', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        alert('Equipo creado exitosamente');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error al crear el equipo');
    });
}

function resetForm() {
    document.getElementById('crear-equipo').reset();
}
