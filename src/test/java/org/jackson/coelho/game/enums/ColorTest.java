package org.jackson.coelho.game.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jackson on 12/10/17.
 */
public class ColorTest {

    @Test
    public void getValueByIndex() throws Exception {
        Assert.assertEquals(Color.RED, Color.getValueByIndex(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getException() throws Exception {
        Color.getValueByIndex(8);
    }

}