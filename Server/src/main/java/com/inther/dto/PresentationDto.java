package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

public class PresentationDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PatchRequest.class})
    @Null(groups = {RequestDataValidator.PutRequest.class})
    @NotNull(groups = {RequestDataValidator.PatchRequest.class})
    private Integer presentationId;

    @Email(groups = {RequestDataValidator.PutRequest.class})
    @Size(groups = {RequestDataValidator.PutRequest.class}, max = 255)
    @Null(groups = {RequestDataValidator.PatchRequest.class})
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String presentationTitle;

    @Size(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String presentationDescription;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Size(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, max = 255)
    @Pattern(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, regexp = "^(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})$")
    @NotNull(groups = {RequestDataValidator.PutRequest.class})
    private Date presentationStartDate;

    @Size(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String presentationPlace;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Size(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, max = 255)
    @Pattern(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, regexp = "^(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})$")
    @NotNull(groups = {RequestDataValidator.PutRequest.class})
    private Date presentationEndDate;

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

    public String getPresentationPlace()
    {
        return presentationPlace;
    }

    public void setPresentationPlace(String presentationPlace)
    {
        this.presentationPlace = presentationPlace;
    }

    public Date getPresentationEndDate()
    {
        return presentationEndDate;
    }

    public void setPresentationEndDate(Date presentationEndDate)
    {
        this.presentationEndDate = presentationEndDate;
    }
}