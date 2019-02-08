package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class PresentationDto implements Serializable
{
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-ddTHH:mm");

    @Positive(groups = {RequestDataValidator.updatePresentation.class})
    @Null(groups = {RequestDataValidator.postPresentation.class})
    @NotNull(groups = {RequestDataValidator.updatePresentation.class})
    private String id;

    @Size(groups = {RequestDataValidator.postPresentation.class, RequestDataValidator.updatePresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.postPresentation.class})
    private String title;

    @Size(groups = {RequestDataValidator.postPresentation.class, RequestDataValidator.updatePresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.postPresentation.class})
    private String description;

    @NotNull(groups = {RequestDataValidator.postPresentation.class})
    private String startDate;

    @NotNull(groups = {RequestDataValidator.postPresentation.class})
    private String endDate;

    @Size(groups = {RequestDataValidator.postPresentation.class, RequestDataValidator.updatePresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.postPresentation.class})
    private String place;

    public Date getStartDateConverted() throws ParseException {
        return dateFormat.parse(startDate);
    }

    public void setStartDate(Date date) {
        this.startDate = dateFormat.format(date);
    }

    public Date getEndDateConverted() throws ParseException {
        return dateFormat.parse(endDate);
    }

    public void setEndDate(Date date) {
        this.endDate = dateFormat.format(date);
    }
}