package com.yaamma.roomservice.controllers;

import com.yaamma.roomservice.forms.CheckRoomForm;
import com.yaamma.roomservice.services.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


@RestController
@Slf4j
public class CheckRoomController {

    /**
     *  look com/yaamma/roomservice/services/impl/RoomServiceImpl.java
     */

    @Autowired
    private Map<Integer,Integer> cache;

    @Autowired
    private RoomService roomService;


    @GetMapping("/check")
    public ResponseEntity<?> checkRoom(CheckRoomForm checkRoomForm){

        int keyId = checkRoomForm.getKeyId();
        int roomId = checkRoomForm.getRoomId();

        if(keyId % roomId !=0){
            return new ResponseEntity<Error>(HttpStatus.FORBIDDEN);
        }
        boolean entrace = checkRoomForm.isEntrace();

        boolean isUserInRoom = roomService.isUserInRoom(keyId);

        if(entrace){
            if (!isUserInRoom) {
                log.info(String.format("userid:%s entered in roomid:%d ",keyId,roomId));
                roomService.enterToRoom(keyId,roomId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                log.info(String.format("userid:%s in another roomid:%d ",keyId,roomId));
                return new ResponseEntity<Error>(HttpStatus.FORBIDDEN);
            }
        } else {
            if (!isUserInRoom) {
                log.info(String.format("userid:%s user not in room roomid:%d ",keyId,roomId));
                return new ResponseEntity<Error>(HttpStatus.FORBIDDEN);
            } else {
                log.info(String.format("userid:%s exit from roomid:%d ",keyId,roomId));
                roomService.exitFromRoom(keyId,roomId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }



    }



}
