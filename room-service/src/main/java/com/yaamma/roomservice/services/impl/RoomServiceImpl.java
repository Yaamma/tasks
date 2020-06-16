package com.yaamma.roomservice.services.impl;

import com.yaamma.roomservice.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoomServiceImpl  implements RoomService {


    /**
     *  key : keyId
     *  value : roomId
     */

    @Autowired
    private Map<Integer,Integer> cache;

    @Override
    public void enterToRoom(int keyId, int roomId) {
        cache.put(keyId,roomId);
    }

    @Override
    public void exitFromRoom(int keyId, int roomId) {
        cache.remove(keyId);
    }

    @Override
    public boolean isUserInRoom(int keyId) {
        return cache.containsKey(keyId);
    }


}
