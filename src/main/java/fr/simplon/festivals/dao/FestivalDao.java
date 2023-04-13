package fr.simplon.festivals.dao;

import fr.simplon.festivals.entity.Festival;

import java.util.List;
import java.util.Optional;

/**
 * Cette interface définit les méthodes pour accéder aux données des festivals.
 */
public interface FestivalDao {
    /**
     * Cette méthode enregistre un festival dans la base de données.
     *
     * @param festival le festival à enregistrer
     */
    void saveFestival(Festival festival);


    void updateFestival(Festival festival);


    /**
     * Cette méthode retourne la liste de tous les festivals enregistrés dans la base de données.
     *
     * @return la liste de tous les festivals
     */
    List<Festival> getAllFestivals();


    Optional<Festival> findById(Long id);

}
