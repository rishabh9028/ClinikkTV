package com.clinikktv.service.dao;

import com.clinikktv.service.entity.VideoContentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VideoContentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<VideoContentEntity> getAllVideoContents() {
        return entityManager.createNamedQuery("getAllVideoContents", VideoContentEntity.class).getResultList();
    }

    public List<VideoContentEntity> getAllFreeVideoContents() {
        return entityManager.createNamedQuery("getAllFreeVideoContents", VideoContentEntity.class).getResultList();
    }

    public List<VideoContentEntity> getAllPaidVideoContents() {
        return entityManager.createNamedQuery("getAllPaidVideoContents", VideoContentEntity.class).getResultList();
    }

    public VideoContentEntity getVideoContentById(final String uuid) {
        try {
            return entityManager
                    .createNamedQuery("videobyId", VideoContentEntity.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
