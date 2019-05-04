package com.blogger.controller;

import com.blogger.features.Jsf.Jsf;
import com.blogger.features.exceptions.DaoException;
import com.blogger.model.post.PostBe;
import com.blogger.model.post.PostVo;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PostCompletoController implements Serializable {

    private PostVo postsVo;
    private PostBe postBe;
    private FacesContext fc;

    public PostCompletoController() {

    }

    @PostConstruct
    private void init() {
        this.postsVo = new PostVo();
        this.postBe = new PostBe();
        fc = FacesContext.getCurrentInstance();
    }

    public void consultarPost() {
        try {
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            PostVo param = new PostVo();
            param.setId(Long.valueOf(params.get("post_id")));
          
            setPostsVo(getPostBe().consultarPostPorId(param));
        } catch (DaoException e) {
            Jsf.Msg.erro(e);
        }
    }

    public String excluirPost() {
        try {
            getPostBe().excluirPost(getPostsVo());
            return "/index?faces-redirect=true";
        } catch (Exception e) {
            Jsf.Msg.erro(e.getMessage());
        }
        return null;
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
        return postBe;
    }

    public void setPostBe(PostBe postBe) {
        this.postBe = postBe;
    }

}