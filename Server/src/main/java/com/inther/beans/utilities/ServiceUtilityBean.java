package com.inther.beans.utilities;

import com.inther.entities.Entities;
import com.inther.entities.implementation.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Component
public class ServiceUtilityBean
{
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthorityUtilityBean authorityUtilityBean;

    public UserEntity encodeUserEntityPassword(UserEntity userEntity)
    {
        if (userEntity.getPassword() != null)
        {
            userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        }
        return userEntity;
    }
    public <T extends Entities> T setAuthenticatedEmailPropertyValue(T targetEntity) throws Exception
    {
        targetEntity.getClass().getMethod("setEmail", String.class).invoke(targetEntity, authorityUtilityBean.getCurrentAuthenticationEmail());
        return targetEntity;
    }
    public <T extends Entities> T patchEntity(T targetEntity, T patchingEntity) throws Exception
    {
        if (targetEntity.getClass().equals(patchingEntity.getClass()))
        {
            for(Method method : targetEntity.getClass().getDeclaredMethods())
            {
                if (method.getName().startsWith("get") && (patchingEntity.getClass().getMethod(method.getName()).invoke(patchingEntity) != null))
                {
                    Class<?> argumentClass = targetEntity.getClass().getMethod(method.getName()).getReturnType();
                    targetEntity.getClass().getMethod("set" + method.getName().substring(3), argumentClass)
                            .invoke(targetEntity, patchingEntity.getClass().getMethod(method.getName()).invoke(patchingEntity));
                }
            }
        }
        else
        {
            throw new ReflectiveOperationException("These are objects of different classes");
        }
        return targetEntity;
    }

    @Autowired
    public ServiceUtilityBean(BCryptPasswordEncoder bCryptPasswordEncoder, AuthorityUtilityBean authorityUtilityBean)
    {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authorityUtilityBean = authorityUtilityBean;
    }
}