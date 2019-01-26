package com.inther.controller;

import com.inther.domain.Message;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/message")
public class MessageController
{
    @PutMapping
    public Map<String, Object> addMessage(@RequestBody Message messageToAdd)
    {
        return null;
    }

    @PatchMapping
    public Map<String, Object> editMessage(@RequestBody Message messageToEdit)
    {
        return null;
    }

    @DeleteMapping
    public Map<String, Object> deleteMessage(@RequestParam(value = "messageId") Integer messageId)
    {
        return null;
    }
}