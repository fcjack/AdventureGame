package org.jackson.coelho.game.menu;

import org.jackson.coelho.game.model.SavedGame;
import org.jackson.coelho.game.service.GameService;
import org.jackson.coelho.game.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackson on 12/10/17.
 */
@Component
public class SaveMenu extends AbstractMenu {

    private final MapService mapService;
    private final GameService gameService;

    @Autowired
    public SaveMenu(MapService mapService, GameService gameService) {
        this.mapService = mapService;
        this.gameService = gameService;
    }

    @Override
    public void init() {
        processOption();
    }

    private void processOption() {
        System.out.println("Do you want to save the game? (Y=Yes, N=No)");
        try {
            String option = getScanner().next();
            switch (option.toUpperCase()) {
                case "Y":
                    System.out.println("Input the name for save this game:");
                    String name = getScanner().next();
                    SavedGame savedGame = new SavedGame();
                    savedGame.setPersona(getCurrentPersona());
                    savedGame.setEnemies(mapService.getEnemies());
                    savedGame.setMap(mapService.getMap());
                    savedGame.setName(name);
                    gameService.saveGame(savedGame);
                    break;
                case "N":
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid option, try again");
            processOption();
        }
    }
}
