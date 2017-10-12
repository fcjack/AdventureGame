package org.jackson.coelho.game.service;

import org.jackson.coelho.game.model.Enemy;
import org.jackson.coelho.game.model.Persona;

/**
 * Created by jackson on 12/10/17.
 */
public interface FightService {

    boolean fight(Persona currentPersona, Enemy enemy);
}
