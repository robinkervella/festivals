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
        model.addAttribute("festivals", festivals); // corrected attribute name
        return "index";
    }

    @GetMapping("/create")
    public String afficherFormulaire(Model model) {
        model.addAttribute("festival", new Festival());
        return "create";
    }

    @PostMapping("/ajouterFestival")
    public String enregistrerFestival(@ModelAttribute("festival") Festival festival) {
        festivalDao.saveFestival(festival);
        return "redirect:/";
    }

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
