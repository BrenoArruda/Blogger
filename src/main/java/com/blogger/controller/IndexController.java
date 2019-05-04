package com.blogger.controller;

import com.blogger.features.exceptions.DaoException;
import com.blogger.model.post.PostBe;
import com.blogger.model.post.PostVo;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexController implements Serializable {

    private PostVo postsVo;
    private PostBe postBe;

    public IndexController() {
        if (postsVo == null) {
            postsVo = new PostVo();
        }
        listarPosts();
    }

    public void listarPosts() {
        try {
            this.postsVo = getPostBe().listarPosts();
        } catch (DaoException e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }

    public String flowIndex() {
        return "/index";
    }

    public PostVo getPostsVo() {
        return postsVo;
    }

    public void setPostsVo(PostVo postsVo) {
        this.postsVo = postsVo;
    }

    public PostBe getPostBe() {
        if (postBe == null) {
            postBe = new PostBe();
        }
        return postBe;
    }

    public void setPostBe(PostBe postBe) {
        this.postBe = postBe;
    }

    public String flowVisualizarPostCompleto(final String postId) {
        return "/post/post-completo?faces-redirect=true&post_id=".concat(postId);
    }
}
