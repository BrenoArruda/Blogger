package com.blogger.model.abstracts;

import java.io.Serializable;
import javax.persistence.EntityManager;

public abstract class AbstractDao implements Serializable {

    private EntityManager em;

    public AbstractDao(EntityManager em) {
        this.em = em;
    }

    protected EntityManager getEm() {
        return em;
    }

    protected void setEm(EntityManager em) {
        this.em = em;
    }
}
