package org.jackson.coelho.game.service;

import org.jackson.coelho.game.enums.Direction;
import org.jackson.coelho.game.model.Persona;

/**
 * Created by jackson on 12/10/17.
 */
public interface ExploreService {

    void explore(Persona currentPersona, int steps, Direction direction);
}
