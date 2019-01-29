package com.inther.services;

import com.inther.beans.AdminAuthorityValidatorBean;
import com.inther.beans.ResponseBean;
import com.inther.entities.UserEntity;
import com.inther.exceptions.AccessDeniedException;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private final AdminAuthorityValidatorBean adminAuthorityValidatorBean;
    private final UserRepository userRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean putUser(UserEntity userEntity) throws Exception
    {
        if (adminAuthorityValidatorBean.validateAdminAuthority())
        {

        }
        else
        {
            throw new AccessDeniedException("Access denied for you authority");
        }
        //userRepository.save(userEntity);
        System.out.println(userEntity);
        return responseBean;
    }



    @Autowired
    public UserService(AdminAuthorityValidatorBean adminAuthorityValidatorBean, UserRepository userRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.adminAuthorityValidatorBean = adminAuthorityValidatorBean;
        this.userRepository = userRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}