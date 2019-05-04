package com.blogger.model.usuario;

import com.blogger.features.exceptions.DaoException;
import com.blogger.features.persistence.PersistenceProperties;
import com.blogger.features.security.ControleAcesso;
import com.blogger.model.abstracts.AbstractBe;
import com.blogger.model.post.PostDao;
import com.blogger.model.post.PostVo;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UsuarioBe extends AbstractBe {

    private UsuarioDao usuarioDao;

    public void cadastrarUsuario(UsuarioVo usuarioVo) {

        validarCadastroUsuario(usuarioVo);

        EntityManager conexao = getConexao();

        EntityTransaction tx = conexao.getTransaction();

        tx.begin();

        conexao.persist(usuarioVo);

        System.out.println(
                "Cadastrado Gravado: " + usuarioVo.getNome());

        tx.commit();

        close(conexao);

    }


    public void pesquisarUsuarioPorEmailSenha(UsuarioVo param) throws DaoException {

        EntityManager em = getConexao();
        try {
            this.usuarioDao = new UsuarioDao(em);
            UsuarioVo usuario = usuarioDao.pesquisarUsuarioPorEmailSenha(param);

            ControleAcesso.login(usuario);

        } catch (NoResultException e) {
            throw new DaoException("Verifique os dados informados", e);
        } catch (DaoException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            close(em);
        }
    }

    private void validarCadastroUsuario(UsuarioVo usuario) {

        if (usuario.getNome().equals("")) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

    }
}
