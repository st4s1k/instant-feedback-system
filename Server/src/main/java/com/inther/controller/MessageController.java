package com.inther.controller;

import com.inther.domain.Message;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class MessageController
{
    @RequestMapping(value = "/user/addMessage", method = RequestMethod.PUT)
    public Map<String, Object> addMessage(@RequestBody Message messageToAdd)
    {
        return null;
    }

    @RequestMapping(value = "/user/editMessage", method = RequestMethod.PATCH)
    public Map<String, Object> editMessage(@RequestBody Message messageToEdit)
    {
        return null;
    }

    @RequestMapping(value = "/user/deleteSelMessage", method = RequestMethod.DELETE)
    public Map<String, Object> deleteMessage(@RequestParam(value = "messageId") Integer messageId)
    {
        return null;
    }
}