package com.inther.beans.utilities;

//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorityUtilityBean
{
    public Boolean validateAdminAuthority()
    {
//        for (GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities())
//        {
//            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
//            {
//                return true;
//            }
//        }
        return true;
    }
    public UUID getCurrentUserId()
    {
        return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}