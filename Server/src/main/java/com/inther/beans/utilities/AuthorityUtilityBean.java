package com.inther.beans.utilities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorityUtilityBean
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
    public String getCurrentAuthenticationEmail()
    {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}