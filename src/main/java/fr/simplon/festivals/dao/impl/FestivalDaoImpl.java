package fr.simplon.festivals.dao.impl;

import fr.simplon.festivals.dao.FestivalDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 Cette classe est une implémentation de l'interface FestivalDao
 pour accéder aux données de festival en utilisant
 un objet FestivalRepository.
 */
@Repository
public class FestivalDaoImpl implements FestivalDao {
    /**
     Le repository utilisé pour accéder aux données de festival.
     */
    @Autowired
    private FestivalRepository festivalRepository;
    /**
     Enregistre un festival dans la base de données.
     @param festival le festival à enregistrer
     */
    @Override
    public void saveFestival(Festival festival) {
        festivalRepository.save(festival);
    }
    /**
     Récupère la liste de tous les festivals enregistrés dans la base de données.
     @return la liste de tous les festivals
     */
    @Override
    public List<Festival> getAllFestivals() {
        return festivalRepository.findAll(); }
    /**
     Recherche un festival dans la base de données par son identifiant.
     @param id l'identifiant du festival à rechercher
     @return un objet Optional contenant le festival si l'identifiant est valide, sinon Optional.empty()
     */
    @Override
    public Optional<Festival> findById(Long id) {
        return festivalRepository.findById(id);
    }
}