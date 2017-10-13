package org.jackson.coelho.game.menu;

import org.jackson.coelho.game.service.FightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackson on 12/10/17.
 */
@Component
public class FightMenu extends AbstractMenu {

    private final FightService fightService;

    @Autowired
    public FightMenu(FightService fightService) {
        this.fightService = fightService;
    }

    @Override
    public void init() {
        System.out.println("You found a enemy");
        buildSummaryAndOptions();

    }

    private void buildSummaryAndOptions() {
        System.out.println("Enemy details: ");
        System.out.println(getCurrentEnemy());
        System.out.println("Persona details:");
        System.out.println(String.format("My class is %s with the attack %d and defense %d, at least my HP is %d",
                getCurrentPersona().getTypeClass(),
                getCurrentPersona().getAttackFactor(),
                getCurrentPersona().getDefenseFactor(),
                getCurrentPersona().getCurrentHealth()));

        System.out.println("What do you want to do?");
        System.out.println("(1) Fight");
        System.out.println("(2) Run");

    }

    public boolean processFightAction() {
        try {
            int option = getScanner().nextInt();
            if (option < 1 || option > 2) {
                throw new IllegalArgumentException();
            }

            if (option == 1) {
                return fightService.fight(getCurrentPersona(), getCurrentEnemy());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid option, try again!");
            buildSummaryAndOptions();
        }
        return false;
    }


}
