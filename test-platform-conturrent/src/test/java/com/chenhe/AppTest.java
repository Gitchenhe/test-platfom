package com.chenhe;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        Entity entity = new Entity();
        System.out.println(entity.id);
    }
    static class Entity{
        long id;
    }

    @Test
    public void test2(){
        String words = "HELLO";
        System.out.println(words.equals(WORD.HELLO));
    }
    static enum WORD {
        HELLO
    }
}
