package fr.simplon.festivals.dao.impl;

import fr.simplon.festivals.entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FestivalRepository extends JpaRepository<Festival, Long> {

    //find festival by id


}
