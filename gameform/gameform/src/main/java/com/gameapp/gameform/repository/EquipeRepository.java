package com.gameapp.gameform.repository;

import com.gameapp.gameform.models.Equipe;
import com.gameapp.gameform.models.Game;
import org.springframework.data.repository.CrudRepository;

public interface EquipeRepository extends CrudRepository<Equipe, String> {

    Iterable<Equipe> findByGame(Game game);
    Equipe findById(long id);

}
