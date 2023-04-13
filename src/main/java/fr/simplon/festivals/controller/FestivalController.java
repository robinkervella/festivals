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


    @GetMapping("/edit/{id}")
    public String editFestival(@PathVariable("id") Long id, Model model) {
        Festival festival = festivalDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid festival Id:" + id));
        model.addAttribute("festival", festival);
        return "edit";
    }

    @GetMapping("/api/festivals")
    @ResponseBody
    public List<Festival> getAllFestivalsAsJson() {
        List<Festival> festivals = festivalDao.getAllFestivals();
        return festivals;
    }


    @PostMapping("/ajouterFestival")
    public String enregistrerFestival(@ModelAttribute("festival") Festival festival) {
        if(festival.getId() == null){
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
