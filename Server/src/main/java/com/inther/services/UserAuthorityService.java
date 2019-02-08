package com.inther.services;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.entities.UserAuthorityEntity;
import com.inther.entities.UserEntity;
import com.inther.exceptions.*;
import com.inther.repositories.UserAuthorityRepository;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAuthorityService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean putUserAuthority(UserAuthorityEntity userAuthorityEntity) throws Exception
    {
        Optional<UserEntity> optionalUserEntity = userRepository.findUserEntityByEmail(userAuthorityEntity.getEmail());
        if (optionalUserEntity.isPresent())
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
        }
        else
        {
            throw new NotFoundEntryException("User with email: '" + userAuthorityEntity.getEmail() + "' not found");
        }
        return responseBean;
    }
    public ResponseBean deleteUserAuthority(UUID id) throws Exception
    {
        Optional<UserAuthorityEntity> optionalUserAuthorityEntity = userAuthorityRepository.findUserAuthorityEntityById(id);
        if (optionalUserAuthorityEntity.isPresent())
        {
            if ((!authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalUserAuthorityEntity.get().getEmail())
                    || !optionalUserAuthorityEntity.get().getAuthority().equals("ROLE_ADMIN")) && authorityUtilityBean.validateAdminAuthority())
            {
                Optional<List<UserAuthorityEntity>> optionalUserAuthorityEntityList = userAuthorityRepository
                        .findUserAuthorityEntityByEmail(optionalUserAuthorityEntity.get().getEmail());
                if (optionalUserAuthorityEntityList.isPresent() && optionalUserAuthorityEntityList.get().size() > 1)
                {
                    userAuthorityRepository.deleteUserAuthorityEntityById(id);
                    responseBean.setHeaders(httpHeaders);
                    responseBean.setStatus(HttpStatus.OK);
                    responseBean.setResponse("Authority with id: '" + id + "' successfully deleted");
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
            throw new NotFoundEntryException("Authority with id: '" + id + "' not found");
        }
        return responseBean;
    }

    @Autowired
    public UserAuthorityService(AuthorityUtilityBean authorityUtilityBean, UserRepository userRepository,
                                UserAuthorityRepository userAuthorityRepository, ResponseBean responseBean,
                                HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.userRepository = userRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}