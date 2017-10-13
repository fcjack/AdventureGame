package org.jackson.coelho.game.service.impl;

import org.jackson.coelho.game.enums.Direction;
import org.jackson.coelho.game.menu.FightMenu;
import org.jackson.coelho.game.model.MapPoint;
import org.jackson.coelho.game.model.Persona;
import org.jackson.coelho.game.service.ExploreService;
import org.jackson.coelho.game.service.MapService;
import org.jackson.coelho.game.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jackson on 12/10/17.
 */
@Service
public class ExploreServiceImpl implements ExploreService {

    private final MapService mapService;

    private final FightMenu fightMenu;

    private final PersonaService personaService;

    @Autowired
    public ExploreServiceImpl(MapService mapService, FightMenu fightMenu, PersonaService personaService) {
        this.mapService = mapService;
        this.fightMenu = fightMenu;
        this.personaService = personaService;
    }

    @Override
    public void explore(Persona currentPersona, int steps, Direction direction) {
        MapPoint mapPoint = mapService.movePersona(currentPersona.getCurrentPosition(), direction, steps);
        currentPersona.setCurrentPosition(mapPoint.getPosition());

        if (mapPoint.hasEnemy()) {
            System.out.println(currentPersona);
            fightMenu.setCurrentPersona(currentPersona);
            fightMenu.setCurrentEnemy(mapPoint.getEnemy());
            fightMenu.init();
            boolean win = fightMenu.processFightAction();
            if (win) {
                personaService.improvePersona(currentPersona, mapPoint.getEnemy());
                mapService.removeEnemy(mapPoint);
            } else if (!currentPersona.isAlive()) {
                System.out.println("You lost the fight! It is a game over! :(");
            }
        } else if (mapPoint.isRecover()) {
            System.out.println("You found a recover item on map, your HP will be restored!");
            currentPersona.recoverHP();
        }
    }
}
