package com.clinikktv.service.dao;

import com.clinikktv.service.entity.AudioContentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AudioContentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AudioContentEntity> getAllAudioContents() {
        return entityManager.createNamedQuery("getAllAudioContents", AudioContentEntity.class).getResultList();
    }

    public List<AudioContentEntity> getAllFreeAudioContents() {
        return entityManager.createNamedQuery("getAllFreeAudioContents", AudioContentEntity.class).getResultList();
    }

    public List<AudioContentEntity> getAllPaidAudioContents() {
        return entityManager.createNamedQuery("getAllPaidAudioContents", AudioContentEntity.class).getResultList();
    }

    public AudioContentEntity getAudioContentById(final String uuid) {
        try {
            return entityManager
                    .createNamedQuery("audiobyId", AudioContentEntity.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
