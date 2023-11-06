
package com.caochaojie.smartdoc.controller;


import com.caochaojie.smartdoc.base.BaseResult;
import com.caochaojie.smartdoc.model.Message;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/")
public class MessageController {

    @GetMapping(value = "messages")
    public List<Message> list() {
        return Collections.emptyList();
    }


    @PutMapping(value = "message")
    public Message modify(Message message) {
        return null;
    }

    @PatchMapping(value = "/message/text")
    public BaseResult<Message> patch(Message message) {
        return null;
    }

    @GetMapping(value = "message/{id}")
    public Message get(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping(value = "message/{id}")
    public void delete(@PathVariable("id") Long id) {
    }


}
