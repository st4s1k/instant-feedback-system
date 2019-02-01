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
    private Integer presentationId;

    @Size(groups = {RequestDataValidator.PutPresentation.class}, max = 255)
    @Email(groups = {RequestDataValidator.PutPresentation.class})
    @Null(groups = {RequestDataValidator.PatchPresentation.class})
    @NotBlank(groups = {RequestDataValidator.PutPresentation.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutPresentation.class, RequestDataValidator.PatchPresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutPresentation.class})
    private String presentationTitle;

    @Size(groups = {RequestDataValidator.PutPresentation.class, RequestDataValidator.PatchPresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutPresentation.class})
    private String presentationDescription;

    @NotNull(groups = {RequestDataValidator.PutPresentation.class})
    private Date presentationStartDate;

    @NotNull(groups = {RequestDataValidator.PutPresentation.class})
    private Date presentationEndDate;

    @Size(groups = {RequestDataValidator.PutPresentation.class, RequestDataValidator.PatchPresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutPresentation.class})
    private String presentationPlace;

    public Integer getPresentationId()
    {
        return presentationId;
    }
    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPresentationTitle()
    {
        return presentationTitle;
    }
    public void setPresentationTitle(String presentationTitle)
    {
        this.presentationTitle = presentationTitle;
    }
    public String getPresentationDescription()
    {
        return presentationDescription;
    }
    public void setPresentationDescription(String presentationDescription)
    {
        this.presentationDescription = presentationDescription;
    }
    public Date getPresentationStartDate()
    {
        return presentationStartDate;
    }
    public void setPresentationStartDate(Date presentationStartDate)
    {
        this.presentationStartDate = presentationStartDate;
    }
    public Date getPresentationEndDate()
    {
        return presentationEndDate;
    }
    public void setPresentationEndDate(Date presentationEndDate)
    {
        this.presentationEndDate = presentationEndDate;
    }
    public String getPresentationPlace()
    {
        return presentationPlace;
    }
    public void setPresentationPlace(String presentationPlace)
    {
        this.presentationPlace = presentationPlace;
    }
}