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

        const markers = [];
        // Créez un marqueur pour chaque festival et ajoutez-le à la carte
        data.forEach(festival => {
            let marker = L.marker([festival.lat, festival.lon]).addTo(map);
            marker.bindPopup("<b>" + festival.nom + "</b><br>" + festival.ville);
            markers.push(marker);
        });

        // Récupérez toutes les lignes du tableau
        const festivalRows = document.querySelectorAll('#festivals-table tbody tr');

        // Ajoutez un événement "click" sur chaque ligne
        festivalRows.forEach(row => {
            row.addEventListener('click', function () {
                // Récupérez la latitude et la longitude du festival sélectionné
                const lat = this.dataset.lat;
                const lon = this.dataset.lon;
                // Centrez la carte sur la position correspondante
                map.setView([lat, lon], 13);
                // Récupérez le marqueur correspondant
                const marker = markers.find(marker => marker.getLatLng().lat === parseFloat(lat) && marker.getLatLng().lng === parseFloat(lon));
                // Ouvrez la popup correspondante
                marker.openPopup();
            });
        });
    })
    .catch(error => console.error(error));







