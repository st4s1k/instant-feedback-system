package com.inther.beans;

import com.inther.entities.Entities;
import com.inther.entities.implementation.PresentationEntity;
import com.inther.entities.implementation.UserAuthorityEntity;
import com.inther.entities.implementation.UserEntity;
import com.inther.exceptions.NestedFieldValueException;
import com.inther.repositories.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceUtilityBean
{
    private final PresentationRepository presentationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private List<UserAuthorityEntity> setStandartUserAuthorityValues(String userAuthorityEmail)
    {
        List<UserAuthorityEntity> userAuthorityEntityList = new ArrayList<>();
        UserAuthorityEntity userAuthorityEntity = new UserAuthorityEntity();
        userAuthorityEntity.setEmail(userAuthorityEmail);
        userAuthorityEntity.setAuthority("ROLE_USER");
        userAuthorityEntityList.add(userAuthorityEntity);
        return userAuthorityEntityList;
    }

    public UserEntity completeRegistrationEntity(UserEntity userEntity)
    {
        userEntity.setEnabled(1);
        userEntity.setUserAuthorities(setStandartUserAuthorityValues(userEntity.getEmail()));
        return userEntity;
    }
    public Optional<List<PresentationEntity>> getPresentationsWithOrWithoutFilter(String email)
    {
        if (email != null)
        {
            return presentationRepository.findPresentationEntityByEmail(email);
        }
        else
        {
            return presentationRepository.findPresentationEntityByEmail(email);

            //return presentationRepository.findPresentationEntity();
        }
    }
    public UserEntity encodeUserPassword(UserEntity userEntity)
    {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        return userEntity;
    }
    public UserEntity nestedFieldValueCheck(UserEntity userEntity) throws Exception
    {
        for (UserAuthorityEntity userAuthorityEntity : userEntity.getUserAuthorities())
        {
            if (!userAuthorityEntity.getEmail().equals(userEntity.getEmail()))
            {
                throw new NestedFieldValueException("Authority email does not match user email");
            }
        }
        return userEntity;
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
                    targetEntity.getClass().getMethod("set" + method.getName().substring(3), argumentClass).invoke(targetEntity, patchingEntity.getClass().getMethod(method.getName()).invoke(patchingEntity));
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
    public ServiceUtilityBean(PresentationRepository presentationRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.presentationRepository = presentationRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}