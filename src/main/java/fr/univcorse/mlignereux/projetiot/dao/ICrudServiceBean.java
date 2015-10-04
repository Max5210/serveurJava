package fr.univcorse.mlignereux.projetiot.dao;

/**
 * Created by asus on 04/10/2015.
 */
public interface ICrudServiceBean<T> {
    public T create(T t) throws Exception;

    public T find(Class type, Object id);

    public T update(T t);

    public void delete(T t);
}
