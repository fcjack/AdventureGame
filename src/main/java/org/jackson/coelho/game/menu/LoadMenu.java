package org.jackson.coelho.game.menu;

import org.jackson.coelho.game.model.SavedGame;
import org.jackson.coelho.game.service.GameService;
import org.jackson.coelho.game.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackson on 12/10/17.
 */
@Component
public class LoadMenu extends AbstractMenu {


    private final ExploreMenu exploreMenu;

    private final GameService gameService;

    private final MapService mapService;

    @Autowired
    public LoadMenu(ExploreMenu exploreMenu, GameService gameService, MapService mapService) {
        this.exploreMenu = exploreMenu;
        this.gameService = gameService;
        this.mapService = mapService;
    }

    @Override
    public void init() {
        buildMenu();
    }

    private void buildMenu() {
        System.out.println("Choose the game that you want to resume");
        List<SavedGame> savedGames = gameService.loadGames();
        for (int i = 0; i < savedGames.size(); i++) {
            System.out.println(String.format("(%d) %s", i + 1, savedGames.get(i).getName()));
        }
        try {
            int index = getScanner().nextInt();
            index--;
            if (index > savedGames.size() || index < 0) {
                throw new IllegalArgumentException();
            }

            SavedGame savedGame = savedGames.get(index);
            mapService.loadMap(savedGame.getMap());
            mapService.loadEnemies(savedGame.getEnemies());
            exploreMenu.setCurrentPersona(savedGame.getPersona());
            exploreMenu.init();

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid option, try again");
            buildMenu();
        }

    }
}
