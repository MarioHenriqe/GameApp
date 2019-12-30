package com.gameapp.gameform.repository;


import com.gameapp.gameform.models.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

    Game findById(long id);


}
