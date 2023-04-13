
// Add map focus on Bretagne
let map = L.map('map').setView([48.202047, -2.932644], 8);

// Add OSM tile layer to the map
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);


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



