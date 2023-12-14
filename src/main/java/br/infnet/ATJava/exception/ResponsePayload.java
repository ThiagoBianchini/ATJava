package br.infnet.ATJava.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponsePayload {


    String msg;
    LocalDateTime dateTime;

    public ResponsePayload(String msg) {

        this.msg = msg;
        this.dateTime = LocalDateTime.now();
    }




}
