package fr.univcorse.mlignereux.projetiot.dao;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by asus on 04/10/2015.
 */
public class CCrudServiceBean<T> implements ICrudServiceBean<T> {

    @PersistenceContext(name = "connection")
    EntityManager em = CDAO.getEntityManager();

    public  T create(T t) throws EntityExistsException, IllegalStateException,
            IllegalArgumentException, TransactionRequiredException {

        EntityTransaction transation = this.em.getTransaction();
        try {
            if (!this.em.getTransaction().isActive()) {
                this.em.getTransaction().begin();
            }
            this.em.persist(t);
            this.em.getTransaction().commit();
            this.em.refresh(t);
        } catch (Exception e) {
            if (this.em.getTransaction().isActive()) {
                this.em.getTransaction().rollback();
            }
            e.printStackTrace();
            try {
                throw e;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        assert (transation == this.em.getTransaction());

        return t;
    }

    public  T find(Class type,Object id)throws IllegalStateException,
            IllegalArgumentException {
        return (T) this.em.find(type, id);
    }

    public void delete(Class type,Object id) throws IllegalStateException,
            IllegalArgumentException, PersistenceException {

        EntityTransaction transation = this.em.getTransaction();
        try {
            T entitytFound = (T)this.em.find(type, id);
            if (!this.em.getTransaction().isActive()) {
                this.em.getTransaction().begin();
            }
            this.em.remove(entitytFound);
            this.em.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            if (this.em.getTransaction().isActive()) {
                this.em.getTransaction().rollback();
            }
            throw e;
        }

    }

    public final void delete(T object) throws IllegalStateException,
            IllegalArgumentException, PersistenceException {

        if (object == null) {
            throw new IllegalArgumentException();
        }
        EntityTransaction transation = this.em.getTransaction();
        try {
            if (!transation.isActive()) {
                transation.begin();
            }
            this.em.remove(object);
            transation.commit();
        } catch (IllegalArgumentException e) {
            if (transation.isActive()) {
                transation.rollback();
            }
            throw e;
        }

    }

    public  T update(T t) throws IllegalArgumentException, TransactionRequiredException {

        try {
            if (!this.em.getTransaction().isActive()) {
                this.em.getTransaction().begin();
            }
            t = this.em.merge(t);
            this.em.getTransaction().commit();
            this.em.refresh(t);
        } catch (IllegalArgumentException e) {
            if (this.em.getTransaction().isActive()) {
                this.em.getTransaction().rollback();
            }
            throw e;
        }
        return t;
    }

    public List<T> findWithNamedQuery(String namedQueryName){
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }



    public List<T> findWithNamedQuery(String namedQueryName, Map parameters){
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }


    public List<T> findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }


    public List<T> findByNativeQuery(String sql, Class type) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }


    public List<T> findWithNamedQuery(String namedQueryName, Map parameters,int resultLimit){
        Set<Map.Entry> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if(resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Map.Entry entry : rawParameters) {
            query.setParameter((String) entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    public List<T> findWithNamedQuery(Class type, String namedQueryName){
        return this.em.createNamedQuery(namedQueryName,type).getResultList();
    }

    public List<T> findWithNamedQuery(Class type, String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName, type).setMaxResults(resultLimit).getResultList();
    }

    public List<T> findWithNamedQuery(Class type, String namedQueryName, Map parameters){
        return findWithNamedQuery(type, namedQueryName, parameters, 0);
    }

    public List<T> findWithNamedQuery(Class type, String namedQueryName, Map parameters,int resultLimit){
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName, type);
        if(resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Map.Entry entry : rawParameters) {
            query.setParameter((String) entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
}
