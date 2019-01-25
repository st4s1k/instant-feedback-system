package com.inther.beans;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthorityValidator
{
    public Boolean validateAdminAuthority()
    {
        for (GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities())
        {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
            {
                return true;
            }
        }
        return false;
    }
}