package org.example.hibernateController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;

public class GenericHibernate {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public GenericHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public <T> List<T> getAll(Class<T> entityClass) {
        List<T> list = new ArrayList<>();
        try{
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaQuery query = entityManager.getCriteriaBuilder().createQuery();
            query.select(query.from(entityClass));
            Query q = entityManager.createQuery(query);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(entityManager != null) {entityManager.close();}
        }
        return list;
    }

    public <T> T getByID(Class<T> entityClass, int id){
        T entity = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entity = entityManager.find(entityClass, id);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
        return entity;
    }

    public <T> void create(T entity) {
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error during CREATE operation: " + e.getMessage());
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

}
