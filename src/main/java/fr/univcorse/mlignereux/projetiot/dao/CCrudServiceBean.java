package fr.univcorse.mlignereux.projetiot.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

/**
 * Created by asus on 04/10/2015.
 */
public class CCrudServiceBean<T> implements ICrudServiceBean<T> {


    @PersistenceContext(name = "connection")
    EntityManager em = CDAO.getEntityManager();

    public T create(T t) throws Exception {

        EntityTransaction transaction = this.em.getTransaction();
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
            throw e;
        }
        assert (transaction == this.em.getTransaction());

        return t;
    }

    public T find(Class type, Object id) {
        return (T) this.em.find(type, id);
    }

    public T update(T t) {
        try {
            if (!this.em.getTransaction().isActive()) {
                this.em.getTransaction().begin();
            }
            t = this.em.merge(t);
            this.em.getTransaction().commit();
            this.em.refresh(t);
        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//	        System.out.println("rollback");
            if (this.em.getTransaction().isActive()) {
                this.em.getTransaction().rollback();
            }
            throw e;
        }
        return t;
    }

    public void delete(T t) {
        if (t == null) {
            throw new IllegalArgumentException();
        }
        EntityTransaction transation = this.em.getTransaction();
        try {
            if (!transation.isActive()) {
                transation.begin();
            }
            this.em.remove(t);
//			this.em.flush();
            transation.commit();
        } catch (IllegalArgumentException e) {
            if (transation.isActive()) {
                transation.rollback();
            }
            throw e;
        }
    }
}
