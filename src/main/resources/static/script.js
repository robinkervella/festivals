// Add map focus on Bretagne
var map = L.map('map').setView([48.202047, -2.932644], 8);

// Add OSM tile layer to the map
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

// Get HTML element for the list of festivals
var festivalList = document.getElementById("festival-list");

// Loop through the festivals list and create a new HTML list element for each festival
festivals.forEach(function(festival) {
    // Create a new HTML list element for the festival
    var newFestival = document.createElement("li");

    // Add festival details to the HTML list element
    newFestival.innerHTML = "<h2>" + festival.nom + "</h2><p><strong>Ville :</strong> " + festival.ville + "</p><p><strong>Lieu :</strong> " + festival.lieu + "</p><p><strong>Début :</strong> " + festival.debut + "</p><p><strong>Fin :</strong> " + festival.fin + "</p><button>Éditer</button>";

    // Add click event to the "Editer" button
    newFestival.querySelector("button").addEventListener("click", function() {
        // Récupérer l'ID du festival
        var festivalId = festival.id;

        // Récupérer les détails du festival à partir de la base de données en utilisant son ID
        fetch("/festivals/" + festivalId)
            .then(response => response.json())
            .then(data => {
                // Afficher les détails du festival dans une alerte
                alert("Nom : " + data.nom + "\nVille : " + data.ville + "\nLieu : " + data.lieu + "\nDébut : " + data.debut + "\nFin : " + data.fin);
            })
            .catch(error => {
                console.error("Erreur lors de la récupération des détails du festival : ", error);
            });
    });


    // Add the new HTML list element to the list of festivals
    festivalList.appendChild(newFestival);
});

// Loop through the festivals list and create a marker for each festival
festivals.forEach(function(festival) {
    // Create a new marker for the festival
    var festivalMarker = L.marker([festival.lat, festival.lon]).addTo(map);

    // Add a custom popup for the festival marker
    festivalMarker.bindPopup("<b>" + festival.nom + "</b><br>" + festival.lieu + "<br>" + festival.ville + "<br>" + festival.url);

    // Open the popup automatically when the map is loaded
    festivalMarker.openPopup();
});
