package com.weblab.dal;

import com.weblab.model.InstagramConnection;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amowel on 20.04.17.
 */
@Transactional
@Getter
@Setter
@Repository
public class InstagramDao implements SocialDao<InstagramConnection> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<InstagramConnection> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from InstagramConnection")
                .list();
    }


    public InstagramConnection findByUsername(String username) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(InstagramConnection.class);
        return (InstagramConnection) criteria
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    @Override
    public InstagramConnection findById(String id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(InstagramConnection.class);
        return (InstagramConnection) criteria
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public InstagramConnection save(InstagramConnection connection) {
        sessionFactory.getCurrentSession().saveOrUpdate(connection);
        return connection;
    }

    @Override
    public void delete(InstagramConnection connection) {
        sessionFactory.getCurrentSession().delete(connection);
    }
}
