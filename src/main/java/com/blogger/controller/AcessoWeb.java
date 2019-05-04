package com.blogger.controller;

import com.blogger.features.security.ControleAcesso;
import com.blogger.model.usuario.UsuarioVo;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AcessoWeb implements Serializable{

    private UsuarioVo usuario;

    public AcessoWeb() {
    }

    public UsuarioVo getUsuario() {
        if (this.usuario == null) {
            this.usuario = ControleAcesso.getUsuarioLogado();
        }
        return usuario;
    }

    public boolean isUsuarioLogado() {
        return ControleAcesso.isUsuarioLogado();

    }
}
