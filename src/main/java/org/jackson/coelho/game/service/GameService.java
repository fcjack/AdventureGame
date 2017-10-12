package org.jackson.coelho.game.service;

import org.jackson.coelho.game.model.SavedGame;

import java.util.List;

/**
 * Created by jackson on 12/10/17.
 */
public interface GameService {

    void saveGame(SavedGame savedGame);

    List<SavedGame> loadGames();

}
