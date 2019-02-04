package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

public class PresentationDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PatchPresentation.class})
    @Null(groups = {RequestDataValidator.PutPresentation.class})
    @NotNull(groups = {RequestDataValidator.PatchPresentation.class})
    private Integer id;

    @Size(groups = {RequestDataValidator.PutPresentation.class, RequestDataValidator.PatchPresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutPresentation.class})
    private String title;

    @Size(groups = {RequestDataValidator.PutPresentation.class, RequestDataValidator.PatchPresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutPresentation.class})
    private String description;

    @NotNull(groups = {RequestDataValidator.PutPresentation.class})
    private Date startDate;

    @NotNull(groups = {RequestDataValidator.PutPresentation.class})
    private Date endDate;

    @Size(groups = {RequestDataValidator.PutPresentation.class, RequestDataValidator.PatchPresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutPresentation.class})
    private String place;

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public Date getStartDate()
    {
        return startDate;
    }
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }
    public Date getEndDate()
    {
        return endDate;
    }
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
    public String getPlace()
    {
        return place;
    }
    public void setPlace(String place)
    {
        this.place = place;
    }
}