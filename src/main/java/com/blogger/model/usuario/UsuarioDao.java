package com.blogger.model.usuario;

import com.blogger.features.exceptions.DaoException;
import com.blogger.model.abstracts.AbstractDao;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UsuarioDao extends AbstractDao {

    public UsuarioDao(EntityManager em) {
        super(em);

    }

    public UsuarioVo pesquisarUsuarioPorEmailSenha(final UsuarioVo usuarioVo) throws DaoException {
        try {
            TypedQuery<UsuarioVo> query = getEm().createQuery("SELECT u  FROM UsuarioVo u WHERE u.email = :email AND u.senha = :senha", UsuarioVo.class);
            query.setParameter("email", usuarioVo.getEmail());
            query.setParameter("senha", usuarioVo.getSenha());
            UsuarioVo usuario = query.getSingleResult();
            return usuario;
        } catch (NoResultException e) {
            throw new DaoException("Verifique os dados informados", e);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public UsuarioVo pesquisarUsuarioPorEmail(final UsuarioVo usuarioVo) throws DaoException {
        try {
            TypedQuery<UsuarioVo> query = getEm().createQuery("SELECT u FROM UsuarioVo u WHERE u.email = :email", UsuarioVo.class);
            query.setParameter("email", usuarioVo.getEmail());
            UsuarioVo usuario = query.getSingleResult();
            return usuario;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
