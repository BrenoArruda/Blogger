package com.blogger.model.post;

import com.blogger.features.exceptions.DaoException;
import com.blogger.features.security.ControleAcesso;
import com.blogger.model.abstracts.AbstractBe;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PostBe extends AbstractBe {

    private PostDao postDao;

    public PostDao getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }
    
    public void cadastrarPost(PostVo postVo) {

        EntityManager conexao = getConexao();

        EntityTransaction tx = conexao.getTransaction();
        tx.begin();

        postVo.setDataCriacao(new Date ());
        postVo.setDataPublicacao(new Date ());
        postVo.setAutor(ControleAcesso.getUsuarioLogado());
        conexao.persist(postVo);

        tx.commit();

        conexao.close();

    }

    public PostVo listarPosts() throws DaoException {
        EntityManager em = getConexao();
        PostVo posts = new PostVo();
        try {
            posts = new PostDao(em).listarPosts();
        } catch (DaoException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            close(em);
        }
        return posts;
    }

    public PostVo consultarPostPorId(final PostVo postVo) throws DaoException {
        EntityManager em = getConexao();
        PostVo posts = new PostVo();
        try {
            posts = new PostDao(em).consultarPostPorId(postVo);
        } catch (DaoException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            close(em);
        }
        return posts;
    }

    public void excluirPost(final PostVo postVo) throws Exception {
        EntityManager em = getConexao();
        EntityTransaction tx = getTransacao();
        try {
            begin(tx);
            PostDao postDao = new PostDao(em);
            postDao.excluirPost(postDao.consultarPostPorId(postVo));
            commit(tx);
        } catch (DaoException e) {
            rollback(tx);
            throw e;
        } catch (Exception e) {
            rollback(tx);
            throw e;
        } finally {
            close(em);
        }
    }
    public void salvarAlteracaoPost(PostVo postVo) throws Exception {
        EntityManager em = getConexao();
        EntityTransaction tx = getTransacao();
        try {
            this.postDao = new PostDao(em);

            begin(tx);
            getPostDao().salvarAlteracaoPost(postVo);
            commit(tx);
        } catch (DaoException e) {
            rollback(tx);
            throw e;
        } catch (Exception e) {
            rollback(tx);
            throw e;
        } finally {
            close(em);
        }
    }
}
