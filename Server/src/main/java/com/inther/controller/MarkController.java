package com.inther.controller;

import com.inther.domain.Mark;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/mark")
public class MarkController
{
    @PutMapping
    public Map<String, Object> addMark(@RequestBody Mark markToAdd)
    {
        return null;
    }
}