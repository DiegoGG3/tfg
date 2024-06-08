function sortTable(columnIndex) {
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("campeonatos-table");
    switching = true;
    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("td")[columnIndex];
            y = rows[i + 1].getElementsByTagName("td")[columnIndex];
            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}

function showEquiposModal(campeonatoId) {
    document.getElementById('equipos-modal').style.display = 'block';
    fetch('/api/equipos?campeonatoId=' + campeonatoId)
        .then(response => response.json())
        .then(data => {
            let equiposList = document.getElementById('equipos-list');
            equiposList.innerHTML = '';
            data.forEach(equipo => {
                let listItem = document.createElement('li');
                listItem.textContent = `${equipo.nombre} - Fundado en ${equipo.anoFundacion} - Presidente: ${equipo.presidente}`;
                equiposList.appendChild(listItem);
            });
        });
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}
