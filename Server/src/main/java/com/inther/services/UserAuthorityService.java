package com.inther.services;

import com.inther.beans.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.entities.implementation.UserAuthorityEntity;
import com.inther.exceptions.AccessDeniedException;
import com.inther.exceptions.DuplicatedEntryException;
import com.inther.exceptions.NotFoundEntryException;
import com.inther.exceptions.SelfDestructionException;
import com.inther.repositories.UserAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserAuthorityService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final UserAuthorityRepository userAuthorityRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean putUserAuthority(UserAuthorityEntity userAuthorityEntity) throws Exception
    {
        Optional<UserAuthorityEntity> optionalUserAuthorityEntity = userAuthorityRepository
                .findUserAuthorityEntityByEmailAndAuthority(userAuthorityEntity.getEmail(), userAuthorityEntity.getAuthority());
        if (!optionalUserAuthorityEntity.isPresent())
        {
            if (authorityUtilityBean.validateAdminAuthority())
            {
                userAuthorityRepository.save(userAuthorityEntity);
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.CREATED);
                responseBean.setResponse("User authority for " + userAuthorityEntity.getEmail() + " successfully added");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new DuplicatedEntryException("That authority for this user already exists");
        }
        return responseBean;
    }
    public ResponseBean deleteUserAuthority(Integer authorityId) throws Exception
    {
        Optional<UserAuthorityEntity> optionalUserAuthorityEntity = userAuthorityRepository.findUserAuthorityEntityByAuthorityId(authorityId);
        if (optionalUserAuthorityEntity.isPresent())
        {
            if ((!authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalUserAuthorityEntity.get().getEmail())
                    || !optionalUserAuthorityEntity.get().getAuthority().equals("ROLE_ADMIN"))
                    && authorityUtilityBean.validateAdminAuthority())
            {
                userAuthorityRepository.deleteUserAuthorityEntityByAuthorityId(authorityId);
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.OK);
                responseBean.setResponse("User authority with id " + authorityId + " successfully deleted");
            }
            else if ((authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalUserAuthorityEntity.get().getEmail())
                    && optionalUserAuthorityEntity.get().getAuthority().equals("ROLE_ADMIN"))
                    && authorityUtilityBean.validateAdminAuthority())
            {
                throw new SelfDestructionException("You cannot deactivate admin rights yourself");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new NotFoundEntryException("User authority with id " + authorityId + " not found");
        }
        return responseBean;
    }

    @Autowired
    public UserAuthorityService(AuthorityUtilityBean authorityUtilityBean, UserAuthorityRepository userRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.userAuthorityRepository = userRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}