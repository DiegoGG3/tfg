const formulario = document.getElementById('crear-user');
const body = document.body;


function submitPersona(evento){
    console.log(evento);
    fetch('http://localhost:8082/users',{
    method:'post',
    headers:{"Content-Type": "application/json"},
    body:JSON.stringify([{
        'nombre': document.getElementById('nombre').value,
        'apellido': document.getElementById('apellido').value,
        'gmail': document.getElementById('gmail').value,
        'contrasena': document.getElementById('contrasena').value,
        'rol': document.getElementById('rol').value,

    }])
})
  .then(function(response){ return response.json()})
  .then( evento.preventDefault())

  resetForm();
}


function resetForm(){
    document.getElementById('formulario').reset();
}