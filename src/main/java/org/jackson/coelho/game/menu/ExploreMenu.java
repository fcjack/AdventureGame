package org.jackson.coelho.game.menu;

import org.jackson.coelho.game.enums.Direction;
import org.jackson.coelho.game.model.Position;
import org.jackson.coelho.game.service.ExploreService;
import org.jackson.coelho.game.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by jackson on 12/10/17.
 */
@Component
public class ExploreMenu extends AbstractMenu {

    private final MapService mapService;

    private final SaveMenu saveMenu;

    private final ExploreService exploreService;

    @Autowired
    public ExploreMenu(MapService mapService, SaveMenu saveMenu, ExploreService exploreService) {
        this.mapService = mapService;
        this.saveMenu = saveMenu;
        this.exploreService = exploreService;
    }

    @Override
    public void init() {
        try {
            mapService.buildMap();
        } catch (IOException e) {
            System.err.println("CRITICAL ERROR LOADING THE MAP FILE");
        }
        Position currentPostion = getCurrentPersona().getCurrentPosition();

        if (currentPostion.getHorizontal().equals(0) && currentPostion.getVertical().equals(0)) {
            System.out.println("We are on the beginning of the adventure");
        }

        chooseActionToTake();
    }

    private void chooseActionToTake() {

        if (mapService.hasEnemiesOnGame()) {
            buildActionOptions();
            try {
                int action = getScanner().nextInt();
                if (action == 0) {
                    saveGameAction();
                } else {
                    System.out.println("How many steps do you want to walk?");
                    int steps = getScanner().nextInt();
                    exploreService.explore(getCurrentPersona(), steps, Direction.getValueByIndex(--action));

                    if (getCurrentPersona().isAlive()) {
                        chooseActionToTake();
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid option, try again!");
                chooseActionToTake();
            }
        }
    }

    private void saveGameAction() {
        saveMenu.setCurrentPersona(getCurrentPersona());
        saveMenu.init();
        chooseActionToTake();
    }

    private void buildActionOptions() {
        System.out.println("Choose the action that you would like to do");
        Position currentPosition = getCurrentPersona().getCurrentPosition();
        System.out.println("(0) SAVE GAME");
        if (currentPosition.getVertical() > 0) {
            System.out.println("(1) MOVE LEFT");
        }

        if (currentPosition.getVertical() < mapService.getMaxVertical()) {
            System.out.println("(2) MOVE RIGHT");
        }

        if (currentPosition.getHorizontal() > 0) {
            System.out.println("(3) MOVE UP");
        }

        if (currentPosition.getHorizontal() < mapService.getMaxHorizontal()) {
            System.out.println("(4) MOVE DOWN");
        }
    }

}
