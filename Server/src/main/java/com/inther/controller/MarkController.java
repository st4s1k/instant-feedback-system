package com.inther.controller;

import com.inther.domain.Mark;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class MarkController
{
    @RequestMapping(value = "/user/addMark", method = RequestMethod.PUT)
    public Map<String, Object> addMark(@RequestBody Mark markToAdd)
    {
        return null;
    }
}