package fr.simplon.festivals.controller;

import fr.simplon.festivals.dao.FestivalDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 Cette classe est un contrôleur Spring MVC qui gère les requêtes liées aux festivals.
 */
@Controller
public class FestivalController {
    /**
     * Le DAO pour accéder aux données de festival.
     */
    @Autowired
    private FestivalDao festivalDao;

    /**
     * Cette méthode permet d'afficher tous les festivals sur la page d'accueil.
     *
     * @param model le modèle qui sera utilisé pour stocker les festivals
     * @return le nom de la vue qui affiche la page d'accueil
     */
    @GetMapping("/")
    public String afficherTousLesFestivals(Model model) {
        List<Festival> festivals = festivalDao.getAllFestivals();
        model.addAttribute("festivals", festivals); // corrected attribute name
        return "index";
    }

    /**
     * Cette méthode permet d'afficher le formulaire de création d'un festival.
     *
     * @param model le modèle qui sera utilisé pour stocker le festival à créer
     * @return le nom de la vue qui affiche le formulaire de création d'un festival
     */
    @GetMapping("/create")
    public String afficherFormulaire(Model model) {
        model.addAttribute("festival", new Festival());
        return "create";
    }

    /**
     * Cette méthode permet de modifier un festival existant.
     *
     * @param id    l'identifiant du festival à modifier
     * @param model le modèle qui sera utilisé pour stocker le festival à modifier
     * @return le nom de la vue qui affiche le formulaire de modification d'un festival
     */
    @GetMapping("/edit/{id}")
    public String editFestival(@PathVariable("id") Long id, Model model) {
        Festival festival = festivalDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid festival Id:" + id));
        model.addAttribute("festival", festival);
        return "edit";
    }

    /**
     * Cette méthode permet de récupérer tous les festivals au format JSON.
     *
     * @return la liste des festivals au format JSON
     */
    @GetMapping("/api/festivals")
    @ResponseBody
    public List<Festival> getAllFestivalsAsJson() {
        List<Festival> festivals = festivalDao.getAllFestivals();
        return festivals;
    }

    /**
     * Cette méthode permet d'enregistrer un festival créé.
     *
     * @param festival le festival à enregistrer
     * @return le nom de la vue qui affiche la page d'accueil
     */
    @PostMapping("/ajouterFestival")
    public String enregistrerFestival(@ModelAttribute("festival") Festival festival) {
        if (festival.getId() == null) {
            // c'est un nouveau festival, donc il faut l'enregistrer dans la base de données
            festivalDao.saveFestival(festival);
        } else {
            // c'est un festival existant, donc il faut le mettre à jour dans la base de données
            festivalDao.updateFestival(festival);
        }
        return "redirect:/";
    }


    @PostMapping("/editerFestival")
    public String editerFestival(@ModelAttribute("festival") Festival festival, @PathVariable("id") Long id) {
        festival.setId(id);
        festivalDao.updateFestival(festival);
        return "redirect:/";

    }
}
