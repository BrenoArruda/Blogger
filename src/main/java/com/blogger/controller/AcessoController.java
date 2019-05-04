package com.blogger.controller;

import com.blogger.features.exceptions.DaoException;
import com.blogger.features.security.ControleAcesso;
import com.blogger.model.usuario.UsuarioBe;
import com.blogger.model.usuario.UsuarioVo;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AcessoController implements Serializable {

    private UsuarioVo usuarioVo;
    private UsuarioBe usuarioBe;

    public AcessoController() {
        if (usuarioVo == null) {
            usuarioVo = new UsuarioVo();
        }
    }

    public String login() {
        try {
            getUsuarioBe().pesquisarUsuarioPorEmailSenha(usuarioVo);
            return "/index";
        } catch (DaoException e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        return null;
    }
    
    public String flowLogin() {
        return "/login";
    }

    public UsuarioVo getUsuarioVo() {
        return usuarioVo;
    }

    public void setUsuarioVo(UsuarioVo usuarioVo) {
        this.usuarioVo = usuarioVo;
    }

    public UsuarioBe getUsuarioBe() {
        if (usuarioBe == null) {
            usuarioBe = new UsuarioBe();
        }
        return usuarioBe;
    }

}
