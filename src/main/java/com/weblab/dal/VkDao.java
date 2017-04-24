package com.weblab.dal;

import com.weblab.model.VkConnection;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amowel on 20.04.17.
 */

@Repository
@Slf4j
@Getter
@Setter
public class VkDao implements SocialDao<VkConnection> {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional(readOnly = true)
    public List<VkConnection> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from VkConnection ")
                .list();
    }

    public VkConnection findByVkId(String vkId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VkConnection.class);
        return (VkConnection) criteria
                .add(Restrictions.eq("vkId", vkId))
                .uniqueResult();
    }

    @Override
    public VkConnection findById(String id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VkConnection.class);
        return (VkConnection) criteria
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public VkConnection save(VkConnection connection) {
        sessionFactory.getCurrentSession().saveOrUpdate(connection);
        return connection;
    }

    @Override
    public void delete(VkConnection connection) {
        sessionFactory.getCurrentSession().delete(connection);
    }

    public List<String> getUserTokens() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VkConnection.class);
        return criteria
                .setProjection(Projections.property("token"))
                .list();
    }
}
