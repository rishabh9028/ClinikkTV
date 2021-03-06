package com.clinikktv.service.business;

import com.clinikktv.service.dao.UserDao;
import com.clinikktv.service.entity.UserEntity;
import com.clinikktv.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupBusinessSevice {

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    @Autowired
    private UserDao userDao;

    /*This Service Method uses DAO object to create new User in DB*/
    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity createUser(final UserEntity userEntity) throws SignUpRestrictedException {
        String User_provided_username = userEntity.getUserName();
        String User_provided_email = userEntity.getEmail();

/*Implementation is we check if User provide Username already exists in DB. if not present then we create else Exception
is thrown. Similarly we check the same for User provided Email
 */
        if (userDao.getUserByUsername(User_provided_username) != null) {

            throw new SignUpRestrictedException("SGR-001", "Try any other Username, this Username has already been taken");

        } else if (userDao.getUserByEmail(User_provided_email) != null) {
            throw new SignUpRestrictedException("SGR-002", "This user has already been registered, try with any other emailId");
        } else {
            String password = userEntity.getPassword();
            if (password == null) {
                userEntity.setPassword("password");
            }
            String[] encryptedText = cryptographyProvider.encrypt(userEntity.getPassword());
            userEntity.setSalt(encryptedText[0]);
            userEntity.setPassword(encryptedText[1]);
            return userDao.createUser(userEntity);

        }
    }
}
