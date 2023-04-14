// Initialise une carte centrée sur la région Bretagne
let mapForm = L.map('mapForm').setView([48.202047, -2.932644], 8);

/**
 Ajoute une couche de tuiles OpenStreetMap à la carte
 @type {L.TileLayer} la couche de tuiles ajoutée à la carte
 */
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(mapForm);

// Crée un marqueur qui sera utilisé pour afficher l'emplacement sélectionné
let marker = null;

// Fonction appelée lorsqu'un clic est effectué sur la carte
function onMapClick(e) {
    // Récupère les coordonnées de l'emplacement sélectionné
    const lat = e.latlng.lat.toFixed(6);
    const lon = e.latlng.lng.toFixed(6);
    // Affiche les coordonnées dans les champs de formulaire correspondants
    document.getElementById('lat').value = lat;
    document.getElementById('lon').value = lon;
    // Place le marqueur sur l'emplacement sélectionné
    if (marker) {
        marker.setLatLng(e.latlng);
    } else {
        marker = L.marker(e.latlng).addTo(mapForm);
    }
}

// Ajoute un événement "click" sur la carte
mapForm.on('click', onMapClick);