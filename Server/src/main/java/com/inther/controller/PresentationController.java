package com.inther.controller;

import com.inther.domain.Presentation;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class PresentationController
{
    @RequestMapping(value = "/addPresentation", method = RequestMethod.PUT)
    public Map<String, Object> addPresentation(@RequestBody Presentation presentationToAdd)
    {
        return null;
    }

    @RequestMapping(value = "/getPresentations", method = RequestMethod.GET)
    public Map<String, Object> getPresentations()
    {
        return null;
    }

    @RequestMapping(value = "/editPresentation", method = RequestMethod.PATCH)
    public Map<String, Object> editPresentation(@RequestBody Presentation presentationToEdit)
    {
        return null;
    }

    @RequestMapping(value = "/deletePresentation", method = RequestMethod.DELETE)
    public Map<String, Object> deletePresentation(@RequestParam(value = "presentationId") Integer presentationId)
    {
        return null;
    }
}