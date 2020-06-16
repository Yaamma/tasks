package com.yaamma.roomservice.services;

import org.springframework.stereotype.Service;

@Service
public interface RoomService {

    void enterToRoom(int keyId,int roomId);
    void exitFromRoom(int keyId,int roomId);
    boolean isUserInRoom(int keyId);

}
