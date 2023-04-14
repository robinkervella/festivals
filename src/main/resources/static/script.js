/**
 Initialise une carte centrée sur la région Bretagne
 @type {L.Map} l'objet Leaflet représentant la carte
 */
let map = L.map('map').setView([48.202047, -2.932644], 8);

/**
 Ajoute une couche de tuiles OpenStreetMap à la carte
 @type {L.TileLayer} la couche de tuiles ajoutée à la carte
 */
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

/**
 Effectue une requête à l'API pour récupérer la liste des festivals, puis crée un marqueur pour chacun d'entre eux et l'ajoute à la carte
 @type {Promise} une promesse représentant les données récupérées depuis l'API
 */
fetch("http://localhost:8080/api/festivals")
    .then(response => response.json())
    .then(data => {
        // Parcourez les données récupérées et créez un marqueur pour chaque festival
        data.forEach(festival => {
            let marker = L.marker([festival.lat, festival.lon]).addTo(map);
            marker.bindPopup("<b>" + festival.nom + "</b><br>" + festival.ville);
        });
    })
    .catch(error => console.error(error));

/**
 * Cette fonction sert à filter la table festivals en fonction des termes recherchés
 * par l'utilisateur. Elle filtre les noms des festivals et les villes.
 */
function filterFestivals() {

    let input = document.getElementById('search-input');
    let filter = input.value.toUpperCase();
    let table = document.querySelector('table');
    let rows = table.querySelectorAll('tr');


    for (let i = 0; i < rows.length; i++) {
        let nameCol = rows[i].querySelector('td:nth-child(1)');
        let cityCol = rows[i].querySelector('td:nth-child(2)');
        if (nameCol || cityCol) {
            let name = nameCol.textContent.toUpperCase();
            let city = cityCol.textContent.toUpperCase();
            if (name.includes(filter) || city.includes(filter)) {
                rows[i].style.display = '';
            } else {
                rows[i].style.display = 'none';
            }
        }
    }
}

input.addEventListener('keyup', function () {
    filterTable();
});



