package com.ann.kont.controller;

import com.ann.kont.model.Message;
import com.ann.kont.service.MessageSender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sender")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Validated
public class SenderController {

    MessageSender messageSender;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody @Valid Message message){
        if(messageSender.send(message)){
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
