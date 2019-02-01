package com.inther.services;

import com.inther.beans.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.entities.implementation.UserAuthorityEntity;
import com.inther.exceptions.*;
import com.inther.repositories.UserAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
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
                responseBean.setResponse("Authority with role: '" + userAuthorityEntity.getAuthority()
                        + "', for user with email: '" + userAuthorityEntity.getEmail() + "' successfully added");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new DuplicatedEntryException("Authority with role: '" + userAuthorityEntity.getAuthority()
                    + "', for user with email: '" + userAuthorityEntity.getEmail() + "' already exists");
        }
        return responseBean;
    }
    public ResponseBean deleteUserAuthority(Integer authorityId) throws Exception
    {
        Optional<UserAuthorityEntity> optionalUserAuthorityEntity = userAuthorityRepository.findUserAuthorityEntityByAuthorityId(authorityId);
        if (optionalUserAuthorityEntity.isPresent())
        {
            if ((!authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalUserAuthorityEntity.get().getEmail())
                    || !optionalUserAuthorityEntity.get().getAuthority().equals("ROLE_ADMIN")) && authorityUtilityBean.validateAdminAuthority())
            {
                Optional<List<UserAuthorityEntity>> optionalUserAuthorityEntityList = userAuthorityRepository
                        .findUserAuthorityEntityByEmail(optionalUserAuthorityEntity.get().getEmail());
                if (optionalUserAuthorityEntityList.isPresent() && optionalUserAuthorityEntityList.get().size() > 1)
                {
                    userAuthorityRepository.deleteUserAuthorityEntityByAuthorityId(authorityId);
                    responseBean.setHeaders(httpHeaders);
                    responseBean.setStatus(HttpStatus.OK);
                    responseBean.setResponse("Authority with id: '" + authorityId + "' successfully deleted");
                }
                else
                {
                    throw new DeleteLastAuthorityException("You cannot delete user's last authority");
                }
            }
            else if ((authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalUserAuthorityEntity.get().getEmail())
                    && optionalUserAuthorityEntity.get().getAuthority().equals("ROLE_ADMIN")) && authorityUtilityBean.validateAdminAuthority())
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
            throw new NotFoundEntryException("Authority with id: '" + authorityId + "' not found");
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