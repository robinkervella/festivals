package fr.simplon.festivals.dao;

import fr.simplon.festivals.entity.Festival;

import java.util.List;
import java.util.Optional;
/**

 Cette interface définit les méthodes pour accéder aux données des festivals.
 */
public interface FestivalDao {
        /**

         Cette méthode enregistre un festival dans la base de données.
         @param festival le festival à enregistrer
         */
        void saveFestival(Festival festival);
        /**

         Cette méthode retourne la liste de tous les festivals enregistrés dans la base de données.
         @return la liste de tous les festivals
         */
        List<Festival> getAllFestivals();
        /**

         Cette méthode recherche un festival dans la base de données par son identifiant.
         @param id l'identifiant du festival à rechercher
         @return un objet Optional contenant le festival si l'identifiant est valide, sinon Optional.empty()
         */
        public abstract Optional<Festival> findById(Long id);

}
