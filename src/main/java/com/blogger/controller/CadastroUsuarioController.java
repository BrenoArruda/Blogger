package com.blogger.controller;

import com.blogger.model.usuario.UsuarioBe;
import com.blogger.model.usuario.UsuarioVo;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroUsuarioController implements Serializable {

    private UsuarioVo usuarioVo;
    private UsuarioBe usuarioBe;

    public UsuarioBe getUsuarioBe() {
        if (this.usuarioBe == null) {
            this.usuarioBe = new UsuarioBe();
        }
        return usuarioBe;
    }

    public CadastroUsuarioController() {

        if (this.usuarioVo == null) {
            this.usuarioVo = new UsuarioVo();
        }

    }

    public void cadastrarUsuario() {
        try {

            getUsuarioBe().cadastrarUsuario(usuarioVo);

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Salvo", null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        } catch (Exception e) {

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

    }

    public String flowCadastrarUsuario() {
        return "/usuario/cadastro-usuario";
    }

    public UsuarioVo getUsuarioVo() {
        return usuarioVo;
    }

    public void setUsuarioVo(UsuarioVo usuarioVo) {
        this.usuarioVo = usuarioVo;
    }

}
