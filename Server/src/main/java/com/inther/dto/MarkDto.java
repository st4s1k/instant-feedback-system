package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.*;
import java.io.Serializable;

public class MarkDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PutMark.class})
    @NotNull(groups = {RequestDataValidator.PutMark.class})
    private Integer presentationId;

    @Positive(groups = {RequestDataValidator.PutMark.class})
    @Max(groups = {RequestDataValidator.PutMark.class}, value = 5)
    @NotNull(groups = {RequestDataValidator.PutMark.class})
    private Integer mark;

    public Integer getPresentationId()
    {
        return presentationId;
    }
    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
    }
    public Integer getMark()
    {
        return mark;
    }
    public void setMark(Integer mark)
    {
        this.mark = mark;
    }
}