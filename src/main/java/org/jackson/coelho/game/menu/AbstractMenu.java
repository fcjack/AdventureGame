package org.jackson.coelho.game.menu;

import org.jackson.coelho.game.model.Enemy;
import org.jackson.coelho.game.model.Persona;

import java.util.Scanner;

/**
 * Created by jackson on 12/10/17.
 */
public abstract class AbstractMenu {

    private Persona currentPersona;
    private Enemy currentEnemy;
    private Scanner scanner = new Scanner(System.in);

    public abstract void init();

    public Persona getCurrentPersona() {
        return currentPersona;
    }

    public void setCurrentPersona(Persona currentPersona) {
        this.currentPersona = currentPersona;
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
