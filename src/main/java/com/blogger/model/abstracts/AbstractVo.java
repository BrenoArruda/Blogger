package com.blogger.model.abstracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVo<T> implements Serializable {

    protected List<T> listVo;

    public List<T> getListVo() {
        if (this.listVo == null) {
            this.listVo = new ArrayList<>();
        }
        return listVo;
    }

    public void setListVo(List<T> colls) {
        this.listVo = colls;
    }

}
