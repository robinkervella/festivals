package fr.simplon.festivals.controller;

import fr.simplon.festivals.dao.FestivalDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FestivalController {
    @Autowired
    private FestivalDao festivalDao;

    @GetMapping("/")
    public String afficherTousLesFestivals(Model model) {
        List<Festival> festivals = festivalDao.getAllFestivals();
        model.addAttribute("festivals", festivals);
        return "festivals";
    }

    @GetMapping("/create")
    public String afficherFormulaire(Model model) {
        model.addAttribute("festival", new Festival());
        return "create";
    }

    @PostMapping ("/ajouterFestival")
    public String enregistrerFestival(@ModelAttribute("festival") Festival festival) {
        festivalDao.saveFestival(festival);
        return "redirect:/";
    }

    @PostMapping ("/submit")
    public String submitForm (@RequestParam("nom") String nom,
                              @RequestParam("url") String url,
                              @RequestParam("debut") LocalDate debut,
                              @RequestParam("fin") LocalDate fin,
                              @RequestParam("ville") String ville, @RequestParam("cp") int cp,
                              @RequestParam("lieu") String lieu, @RequestParam("lat") double lat,
                              @RequestParam("lon") double lon,

                              Model model) {

        //Ajout des données dans le modèle


        Festival festival = new Festival();
        model.addAttribute("festival", festival);

        //Retourne la vue qui affichera les données du formulaire
        return "result";
    }
}
