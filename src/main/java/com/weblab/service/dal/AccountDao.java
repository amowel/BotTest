package com.weblab.service.dal;

import com.weblab.model.Account;
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
@Repository
@Transactional
@Setter
@Getter
public class AccountDao implements SocialDao<Account> {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private InstagramDao instagramDao;
    @Autowired
    private VkDao vkDao;


    @Override
    public List<Account> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Account ")
                .list();
    }

    public Account findByUsername(String username) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Account.class);
        return (Account) criteria
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    @Override
    public Account findById(String id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Account.class);
        return (Account) criteria
                .add(Restrictions.eq("id", id))
                .uniqueResult();

    }

    @Override
    public Account save(Account connection) {
        sessionFactory.getCurrentSession().saveOrUpdate(connection);
        return connection;
    }

    @Override
    public void delete(Account connection) {
        sessionFactory.getCurrentSession().delete(connection);
    }
    public Account findByVkId(String vkId)
    {
        return vkDao.findByVkId(vkId).getAccount();
    }
    public Account findByInstagramUsername(String username)
    {
        return instagramDao.findByUsername(username).getAccount();
    }

}
