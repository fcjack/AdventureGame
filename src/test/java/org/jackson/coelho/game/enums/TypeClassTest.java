package org.jackson.coelho.game.enums;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jackson on 12/10/17.
 */
public class TypeClassTest {

    @Test
    public void getValueByIndex() throws Exception {
        Assert.assertEquals(TypeClass.TECH, TypeClass.getValueByIndex(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getValueByIndexException() throws Exception {
        TypeClass.getValueByIndex(8);
    }

}