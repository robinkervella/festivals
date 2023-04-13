package fr.simplon.festivals.dao.impl;

import fr.simplon.festivals.entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Cette interface fournit des méthodes pour accéder aux données de festival en utilisant JPA.
 * Elle hérite de l'interface JpaRepository et utilise la classe Festival comme type d'entité
 * et Long comme type d'identifiant.
 */
public interface FestivalRepository extends JpaRepository<Festival, Long> {
}
