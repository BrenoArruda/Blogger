package com.blogger.controller;

import com.blogger.model.post.PostBe;
import com.blogger.model.post.PostVo;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastrarPostController implements Serializable {

    private PostVo postVo;
    private PostBe postBe;

    public CadastrarPostController() {
        if (postVo == null) {
            postVo = new PostVo();
        }
    }

    public void cadastrarPost() {
        try {

            getPostBe().cadastrarPost(postVo);

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Post Salvo", null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        } catch (Exception e) {

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

    }

    public String flowCadastrarPost() {
        return "/post/cadastro-post";
    }

    public PostVo getPostVo() {
        return postVo;
    }

    public void setPostVo(PostVo postVo) {
        this.postVo = postVo;
    }

    public PostBe getPostBe() {
        if (this.postBe == null) {
            this.postBe = new PostBe();
        }
        return postBe;
    }

    public void setPostBe(PostBe postBe) {
        this.postBe = postBe;
    }

}
