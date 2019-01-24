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

    @RequestMapping(value = "/user/editSelfMessage", method = RequestMethod.PATCH)
    public Map<String, Object> editSelfMessage(@RequestBody Message messageToEdit)
    {
        return null;
    }

    @RequestMapping(value = "/user/deleteSelfMessage", method = RequestMethod.DELETE)
    public Map<String, Object> deleteSelfMessage(@RequestParam(value = "messageId") Integer messageId)
    {
        return null;
    }

    @RequestMapping(value = "/admin/editAnyMessage", method = RequestMethod.PATCH)
    public Map<String, Object> editAnyMessage(@RequestBody Message messageToEdit)
    {
        return null;
    }

    @RequestMapping(value = "/admin/deleteAnyMessage", method = RequestMethod.DELETE)
    public Map<String, Object> editAnyMessage(@RequestParam(value = "messageId") Integer messageId)
    {
        return null;
    }
}