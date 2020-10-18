package com.clinikktv.service.business;

import com.clinikktv.service.dao.AudioContentDao;
import com.clinikktv.service.dao.UserAuthDao;
import com.clinikktv.service.dao.UserDao;
import com.clinikktv.service.entity.AudioContentEntity;
import com.clinikktv.service.entity.UserAuthEntity;
import com.clinikktv.service.exception.AuthorizationFailedException;
import com.clinikktv.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioContentService {

    @Autowired
    private UserAuthDao userAuthDao;

    @Autowired
    private AudioContentDao audioContentDao;

    @Autowired
    private UserDao userDao;

    public List<AudioContentEntity> getAllAudioContent(final String accessToken)
            throws AuthorizationFailedException {
        String[] bearerToken = accessToken.split("Bearer ");
        UserAuthEntity userAuthEntity = userAuthDao.getUserAuthByToken(bearerToken[1]);
        if (userAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
        } else if (userAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException(
                    "ATHR-002", "User is signed out.Sign in first to get all questions");
        }
        return audioContentDao.getAllAudioContents();
    }

    public List<AudioContentEntity> getAllFreeAudioContent(final String accessToken)
            throws AuthorizationFailedException {
        String[] bearerToken = accessToken.split("Bearer ");
        UserAuthEntity userAuthEntity = userAuthDao.getUserAuthByToken(bearerToken[1]);
        if (userAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
        } else if (userAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException(
                    "ATHR-002", "User is signed out.Sign in first to get all questions");
        }
        return audioContentDao.getAllFreeAudioContents();
    }

    public List<AudioContentEntity> getAllPaidAudioContent(final String accessToken)
            throws AuthorizationFailedException {
        String[] bearerToken = accessToken.split("Bearer ");
        UserAuthEntity userAuthEntity = userAuthDao.getUserAuthByToken(bearerToken[1]);
        if (userAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
        } else if (userAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException(
                    "ATHR-002", "User is signed out.Sign in first to get all questions");
        }
        return audioContentDao.getAllPaidAudioContents();
    }


    public AudioContentEntity getAllAudioContentById(String uuid, String accessToken)
            throws AuthorizationFailedException, UserNotFoundException {
        String[] bearerToken = accessToken.split("Bearer ");
        UserAuthEntity userAuthEntity = userAuthDao.getUserAuthByToken(bearerToken[1]);
        if (userAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
        } else if (userAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException(
                    "ATHR-002",
                    "User is signed out.Sign in first to get all questions posted by a specific user");
        }

        AudioContentEntity audioContentEntity = audioContentDao.getAudioContentById(uuid);

        if(audioContentEntity.getPaid()){
            UserAuthEntity user = userAuthDao.getUserAuthByToken(bearerToken[1]);
            if(user.getUserEntity().isSubscribed() == false){
                throw new UserNotFoundException("USR-002","Please subscribe to view this audio");
            }
        }
        if (audioContentEntity == null) {
            throw new UserNotFoundException(
                    "USR-001", "Audio with entered uuid does not exist");
        }
        return audioContentEntity;
    }
}
