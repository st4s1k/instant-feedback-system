package com.inther.controller;

import com.inther.domain.Message;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MessageController
{
    @RequestMapping(value = "/api/user/addMessage", method = RequestMethod.PUT)
    public Map<String, Object> addMessage(@RequestBody Message messageToAdd)
    {
        return null;
    }

    @RequestMapping(value = "/api/user/editSelfMessage", method = RequestMethod.PATCH)
    public Map<String, Object> editSelfMessage(@RequestBody Message messageToEdit)
    {
        return null;
    }

    @RequestMapping(value = "/api/user/deleteSelfMessage", method = RequestMethod.DELETE)
    public Map<String, Object> deleteSelfMessage(@RequestParam(value = "messageId") Integer messageId)
    {
        return null;
    }

    @RequestMapping(value = "/api/admin/editAnyMessage", method = RequestMethod.PATCH)
    public Map<String, Object> editAnyMessage(@RequestBody Message messageToEdit)
    {
        return null;
    }

    @RequestMapping(value = "/api/admin/deleteAnyMessage", method = RequestMethod.DELETE)
    public Map<String, Object> editAnyMessage(@RequestParam(value = "messageId") Integer messageId)
    {
        return null;
    }
}