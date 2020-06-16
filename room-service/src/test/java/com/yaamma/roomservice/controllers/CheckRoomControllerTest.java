package com.yaamma.roomservice.controllers;

import com.yaamma.roomservice.forms.CheckRoomForm;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckRoomControllerTest {

    @Autowired
    private CheckRoomController checkRoomController;

    @Test
    void checkRoom() {
        // bad requesst example
        CheckRoomForm form1 = new CheckRoomForm(true,23,12);
        Assert.assertEquals(new ResponseEntity<Error>(HttpStatus.FORBIDDEN),checkRoomController.checkRoom(form1));

        // if you use these parameters you will end up in a room
        CheckRoomForm form2 = new CheckRoomForm(true,24,12);
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.OK),checkRoomController.checkRoom(form2));

        // we can't go into the other room until we get out
        CheckRoomForm form3 = new CheckRoomForm(true,24,6);
        Assert.assertEquals(new ResponseEntity<Error>(HttpStatus.FORBIDDEN),checkRoomController.checkRoom(form3));

        // we leave the room
        form3.setEntrace(false);
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.OK),checkRoomController.checkRoom(form3));

        }
}