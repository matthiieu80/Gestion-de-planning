package dao;

import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserJpaDao implements UserDao {

    @Override
    public void save(User user) {
        System.out.println("passage save1");
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(user);
            System.out.println("passage save persist");
            et.commit();
        } catch (Exception e) {
            System.out.println("passage save catch" + e);
            if (et.isActive()) {
                System.out.println("passage save catch2");
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User pastry) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.merge(pastry);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }

    }

    @Override
    public void delete(User pastry) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.remove(em.contains(pastry) ? pastry : em.merge(pastry));
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<User> get(Long id) {
        Optional<User> result = Optional.empty();
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            result = Optional.of(em.find(User.class, id));
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        List<User> pastryList = new ArrayList<>();
        et.begin();
        try {
            TypedQuery<User> gamesQuery = em.createQuery("SELECT p FROM User p", User.class);
            //UserList = gamesQuery.getResultList();
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return pastryList;
    }

}
