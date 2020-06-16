package com.yaamma.roomservice.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRoomForm {

    private boolean entrace;
    private int keyId;
    private int roomId;

}
