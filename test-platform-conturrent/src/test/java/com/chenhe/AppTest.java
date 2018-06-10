package com.chenhe;

import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

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

    @Test
    public void test3(){
        Map map1 = new HashMap();
        map1.put(1,"2");

        Gson gson = new Gson();
        Map<Integer, String> map = gson.fromJson(gson.toJson(map1), Map.class);
        System.out.println(map.get("1")); // 输出 null
    }
}
