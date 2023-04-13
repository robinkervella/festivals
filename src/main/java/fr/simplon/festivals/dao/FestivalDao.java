package fr.simplon.festivals.dao;

import fr.simplon.festivals.entity.Festival;

import java.util.List;
import java.util.Optional;

public interface FestivalDao {

        void saveFestival(Festival festival);
        void updateFestival(Festival festival);
        List<Festival> getAllFestivals();

        public abstract Optional<Festival> findById(Long id);

}
