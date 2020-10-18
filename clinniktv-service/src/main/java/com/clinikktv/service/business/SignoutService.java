package com.clinikktv.service.business;


import com.clinikktv.service.dao.UserAuthDao;
import com.clinikktv.service.dao.UserDao;
import com.clinikktv.service.entity.UserAuthEntity;
import com.clinikktv.service.entity.UserEntity;
import com.clinikktv.service.exception.SignOutRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;


@Service
public class SignoutService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAuthDao userAuthDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity logoutuser(final String accessToken) throws SignOutRestrictedException {
        UserAuthEntity userAuthEntity = userAuthDao.getUserAuthByToken(accessToken);

        if (userAuthEntity == null) {
            throw new SignOutRestrictedException("SGR-001", "User is not Signed in");
        } else {
            final ZonedDateTime now = ZonedDateTime.now();
            userAuthEntity.setLogoutAt(now);
            userDao.updateuserAuthentity(userAuthEntity);
            UserEntity userEntity = userAuthEntity.getUserEntity();
            return userEntity;
        }
    }
}
