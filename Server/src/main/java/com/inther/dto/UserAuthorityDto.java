package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.*;

public class UserAuthorityDto
{
    @Positive(groups = {RequestDataValidator.PatchRequest.class})
    @Null(groups = {RequestDataValidator.PutRequest.class})
    @NotNull(groups = {RequestDataValidator.PatchRequest.class})
    private Integer authorityId;

    @Size(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String authority;

    public Integer getAuthorityId()
    {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId)
    {
        this.authorityId = authorityId;
    }

    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }
}