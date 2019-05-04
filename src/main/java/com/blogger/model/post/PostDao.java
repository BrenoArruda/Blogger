package com.blogger.model.post;

import com.blogger.features.exceptions.DaoException;
import com.blogger.model.abstracts.AbstractDao;
import com.blogger.model.usuario.UsuarioVo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PostDao extends AbstractDao{

    public PostDao(EntityManager em) {
        super(em);
    }

    public void salvarPost(final PostVo postVo) throws DaoException {
        try {
            getEm().persist(postVo);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public void salvarAlteracaoPost(final PostVo postVo) throws DaoException {
        try {
            getEm().merge(postVo);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public PostVo consultarPostPorId(final PostVo postVo) throws DaoException {
        try {
            return getEm().find(PostVo.class, postVo.getId());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public PostVo listarPosts() throws DaoException {
        try {
            TypedQuery query = getEm().createQuery("SELECT p FROM PostVo p", PostVo.class);
            List<PostVo> posts = query.getResultList();
            PostVo postVo = new PostVo();
            postVo.setListVo(posts);
            return postVo;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public PostVo listarMeusPosts(final UsuarioVo usuarioVo) throws DaoException {
        try {
            TypedQuery query = getEm().createQuery("SELECT p FROM PostVo p WHERE p.autor=:autor", PostVo.class);
            System.out.println("AUTOR: " + usuarioVo.getId() + " : " + usuarioVo.getNome());
            query.setParameter("autor", usuarioVo);
            List<PostVo> posts = query.getResultList();
            PostVo postVo = new PostVo();
            postVo.setListVo(posts);
            System.out.println("POSTS: " + postVo.getListVo().size());
            return postVo;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public void excluirPost(final PostVo postVo) throws DaoException {
        try {
            getEm().remove(postVo);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
