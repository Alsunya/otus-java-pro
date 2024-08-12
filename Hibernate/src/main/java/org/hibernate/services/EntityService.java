package org.hibernate.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.entities.Messages;
import org.hibernate.utils.EntityUtil;

import java.io.BufferedReader;
import java.util.logging.Logger;

@RequiredArgsConstructor
public abstract class EntityService<T> {
    protected static final Logger logger = Logger.getLogger(EntityService.class.getName());
    protected final SessionFactory sessionFactory;
    private final Class<T> cls;

    public abstract void addNew(BufferedReader reader);

    public void printAll(){
        var list = EntityUtil.findAll(sessionFactory, cls);
        list.forEach(item->logger.info(item.toString()));
    }

    public void findById(Long id){
        var customer = EntityUtil.findById(sessionFactory, cls, id);
        logger.info(customer.toString());
    }

    public void deleteById(Long id){
        try {
            EntityUtil.deleteById(sessionFactory, cls, id);
        }
        catch (IllegalArgumentException e){
            logger.warning(String.valueOf(Messages.BAD_OPERATION));
        }
    }
}
