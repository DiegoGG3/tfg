function sortTable(columnIndex) {
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("equipos-table");
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

function showPlayersModal(equipoId) {
    document.getElementById('players-modal').style.display = 'block';
    fetch('/api/jugadores?equipoId=' + equipoId)
        .then(response => response.json())
        .then(data => {
            let playersList = document.getElementById('players-list');
            playersList.innerHTML = '';
            data.forEach(player => {
                let listItem = document.createElement('li');
                listItem.textContent = player.nombre + ' - ' + player.golesTotales + ' goles';
                playersList.appendChild(listItem);
            });
        });
}

function closeModal() {
    document.getElementById('players-modal').style.display = 'none';
}
