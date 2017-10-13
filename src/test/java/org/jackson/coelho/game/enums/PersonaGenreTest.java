package org.jackson.coelho.game.enums;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jackson on 12/10/17.
 */
public class PersonaGenreTest {

    @Test
    public void getGenreBySymbol() throws Exception {
        Assert.assertEquals(PersonaGenre.MALE, PersonaGenre.getGenreBySymbol('M'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getGenreBySymbolException() throws Exception {
        PersonaGenre.getGenreBySymbol('K');
    }

}