package com.blogger.model.abstracts;

import com.blogger.features.persistence.PersistenceProperties;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AbstractBe implements Serializable{

    private EntityManager em;

    protected EntityManager getConexao() {
        if (this.em == null || !this.em.isOpen()) {
            //em = Persistence.createEntityManagerFactory("BLOG_PG_PU", new PersistenceProperties().getConfigPersistence()).createEntityManager();
            em = Persistence.createEntityManagerFactory("BLOG_PG_PU").createEntityManager();
            System.out.println("Conexão aberta: " + em.hashCode());
        }
        return em;
    }

    protected EntityTransaction getTransacao() {
        return getConexao().getTransaction();
    }

    protected void commit(final EntityTransaction tx) {
        if (tx != null && tx.isActive()) {
            tx.commit();
        }
    }

    protected void begin(final EntityTransaction tx) {
        if (tx != null) {
            tx.begin();
        }
    }

    protected void rollback(final EntityTransaction tx) {
        if (tx != null) {
            tx.rollback();
        }
    }

    protected void close(final EntityManager em) {
        if (em != null) {
            if (em.isOpen()) {
                if (em.hashCode() == this.em.hashCode()) {
                    System.out.println("Conexão fechada: " + em.hashCode());
                    em.close();
                }
            }
        }
    }

}
