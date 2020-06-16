package com.yaamma.roomservice.services.impl;

import com.yaamma.roomservice.services.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomServiceImplTest {

    /**
     *     !!!  checking how RoomServiceImpl changes the cache  !!!
     *         this is why the cache variable is used here
     */

    @Autowired
    private Map<Integer,Integer> cache;

    @Autowired
    private RoomService roomService;


    /**
     * test values (keyID)
     * roomId = 2
     */
    private int [] arr = new int[]{10, 12, 14};

    @Test
    void enterToRoom() {
        for (int i = 0; i < arr.length ; i++) {
            roomService.enterToRoom(arr[i],2);
        }
        Set<Integer> integers = cache.keySet();
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < arr.length ; i++) {
            assertEquals(true,cache.containsKey(iterator.next()));
        }
    }

    @Test
    void exitFromRoom() {

        // adding values
        for (int i = 0; i < arr.length ; i++) {
            roomService.enterToRoom(arr[i],2);
        }
        // removing values
        for (int i = 0; i < arr.length ; i++) {
            roomService.exitFromRoom(arr[i],2);
        }
        // check for the presence of values
        for (int i = 0; i < arr.length ; i++) {
            assertEquals(false,cache.containsKey(arr[i]));
        }

    }

    @Test
    void isUserInRoom() {
        int userId = 10;
        roomService.enterToRoom(10,2);
        assertEquals(true,roomService.isUserInRoom(userId));
    }
}