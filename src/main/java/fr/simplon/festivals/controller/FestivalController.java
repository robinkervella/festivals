package fr.simplon.festivals.controller;

import fr.simplon.festivals.dao.FestivalDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
 Cette classe est un contrôleur Spring MVC qui gère les requêtes liées aux festivals.
 */
@Controller
public class FestivalController {
    /**
     Le DAO pour accéder aux données de festival.
     */
    @Autowired
    private FestivalDao festivalDao;
    /**
     Cette méthode permet d'afficher tous les festivals sur la page d'accueil.
     @param model le modèle qui sera utilisé pour stocker les festivals
     @return le nom de la vue qui affiche la page d'accueil
     */
    @GetMapping("/")
    public String afficherTousLesFestivals(Model model) {
        List<Festival> festivals = festivalDao.getAllFestivals();
        model.addAttribute("festivals", festivals); // corrected attribute name
        return "index";
    }
    /**
     Cette méthode permet d'afficher le formulaire de création d'un festival.
     @param model le modèle qui sera utilisé pour stocker le festival à créer
     @return le nom de la vue qui affiche le formulaire de création d'un festival
     */
    @GetMapping("/create")
    public String afficherFormulaire(Model model) {
        model.addAttribute("festival", new Festival());
        return "create";
    }

    /**
     Cette méthode permet de modifier un festival existant.
     @param id l'identifiant du festival à modifier
     @param model le modèle qui sera utilisé pour stocker le festival à modifier
     @return le nom de la vue qui affiche le formulaire de modification d'un festival
     */
    @GetMapping("/edit/{id}")
    public String editFestival(@PathVariable("id") Long id, Model model) {
        Festival festival = festivalDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid festival Id:" + id));
        model.addAttribute("festival", festival);
        return "edit";
    }
    /**
     Cette méthode permet de récupérer tous les festivals au format JSON.
     @return la liste des festivals au format JSON
     */
    @GetMapping("/api/festivals")
    @ResponseBody
    public List<Festival> getAllFestivalsAsJson() {
        List<Festival> festivals = festivalDao.getAllFestivals();
        return festivals;
    }

    /**
     Cette méthode permet d'enregistrer un festival créé.
     @param festival le festival à enregistrer
     @return le nom de la vue qui affiche la page d'accueil
     */
    @PostMapping("/ajouterFestival")
    public String enregistrerFestival(@ModelAttribute("festival") Festival festival) {
        festivalDao.saveFestival(festival);
        return "redirect:/";
    }
    /**
     Cette méthode permet de soumettre un formulaire de création/modification d'un festival.
     @param festival le festival à enregistrer
     @param model le modèle qui sera utilisé pour stocker les festivals
     @return le nom de la vue qui affiche la page d'accueil
     */
    @PostMapping("/submit")
    public String submitForm (@ModelAttribute("festival") Festival festival, Model model) {
        //Enregistrer le festival dans la base de données
        festivalDao.saveFestival(festival);

        //Ajouter le festival à la liste pour l'afficher dans la vue index
        List<Festival> festivals = festivalDao.getAllFestivals();
        model.addAttribute("festivals", festivals);

        //Retourne la vue index qui affichera la liste des festivals
        return "index";
    }
}
