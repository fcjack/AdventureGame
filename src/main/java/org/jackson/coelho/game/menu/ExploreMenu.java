package org.jackson.coelho.game.menu;

import org.jackson.coelho.game.enums.Direction;
import org.jackson.coelho.game.model.MapPoint;
import org.jackson.coelho.game.model.Position;
import org.jackson.coelho.game.service.MapService;
import org.jackson.coelho.game.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by jackson on 12/10/17.
 */
@Component
public class ExploreMenu extends AbstractMenu {

    private final MapService mapService;

    private final PersonaService personaService;

    private final FightMenu fightMenu;

    private final SaveMenu saveMenu;

    @Autowired
    public ExploreMenu(MapService mapService, PersonaService personaService, FightMenu fightMenu, SaveMenu saveMenu) {
        this.mapService = mapService;
        this.personaService = personaService;
        this.fightMenu = fightMenu;
        this.saveMenu = saveMenu;
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

        chooseDirection();
    }

    private void chooseDirection() {

        if (mapService.hasEnemiesOnGame()) {
            buildActionOptions();
            try {
                int option = getScanner().nextInt();
                if (option == 0) {
                    saveGameAction();
                } else {
                    System.out.println("How many steps do you want to walk?");
                    int steps = getScanner().nextInt();
                    MapPoint mapPoint = mapService.movePersona(getCurrentPersona().getCurrentPosition(),
                            Direction.getValueByIndex(--option), steps);

                    getCurrentPersona().setCurrentPosition(mapPoint.getPosition());
                    if (mapPoint.hasEnemy()) {
                        System.out.println(getCurrentPersona());
                        fightMenu.setCurrentPersona(getCurrentPersona());
                        fightMenu.setCurrentEnemy(mapPoint.getEnemy());
                        fightMenu.init();
                        boolean win = fightMenu.processOption();
                        if (win) {
                            personaService.improvePersona(getCurrentPersona(), mapPoint.getEnemy());
                            mapService.removeEnemy(mapPoint);
                        } else if (!getCurrentPersona().isAlive()) {
                            System.out.println("You lost the fight! It is a game over! :(");
                        }
                    } else if (mapPoint.isRecover()) {
                        System.out.println("You found a recover item on map, your HP will be restored!");
                        getCurrentPersona().recoverHP();
                    }

                    if (getCurrentPersona().isAlive()) {
                        chooseDirection();
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid option, try again!");
                chooseDirection();
            }
        }
    }

    private void saveGameAction() {
        saveMenu.setCurrentPersona(getCurrentPersona());
        saveMenu.init();
        chooseDirection();
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

        System.out.println("(9) EXIT GAME");
    }

}
