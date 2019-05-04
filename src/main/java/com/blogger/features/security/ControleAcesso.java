package com.blogger.features.security;

import com.blogger.model.usuario.UsuarioVo;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class ControleAcesso {

    public static final String USUARIO_LOGADO = "USUARIO_LOGADO";

    public static boolean isUsuarioLogado(final HttpSession sessao) {
        try {
            if (sessao != null) {
                UsuarioVo usuarioVo = (UsuarioVo) sessao.getAttribute(USUARIO_LOGADO);
                return (usuarioVo != null && usuarioVo.getId() > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isUsuarioLogado() {
        try {
            return getUsuarioLogado() != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static UsuarioVo getUsuarioLogado() {
        HttpSession sessao = getSessao();
        if (sessao != null) {
            UsuarioVo usuarioVo = (UsuarioVo) sessao.getAttribute(USUARIO_LOGADO);
            if (usuarioVo != null && usuarioVo.getId() != null) {
                return usuarioVo;
            }
        }
        return null;
    }

    public static void login(final UsuarioVo usuarioVo) {
        HttpSession sessao = criarSessao();
        if (sessao != null) {
            if (usuarioVo != null && usuarioVo.getId() != null) {
                sessao.setAttribute(ControleAcesso.USUARIO_LOGADO, usuarioVo);
            }
        }
    }

    public static void logout(HttpSession sessao) {
        if (sessao != null) {
            sessao.removeAttribute(USUARIO_LOGADO);
            sessao.invalidate();
        }
    }

    private synchronized static HttpSession getSessao() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            if (fc != null) {
                ExternalContext ec = fc.getExternalContext();
                if (ec != null) {
                    return (HttpSession) ec.getSession(false);
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("ControleAcesso->getSessao->".concat(e.getMessage()));
        }
        return null;
    }

    private static HttpSession criarSessao() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static Set<String> acessoLivre() {
        Set<String> paginas = new CopyOnWriteArraySet<>();
        paginas.add("index.xhtml");
        paginas.add("login.xhtml");
        paginas.add("usuario/novo-usuario.xhtml");
        paginas.add("error.xhtml");
        paginas.add(".css");
        paginas.add(".js");
        paginas.add(".svg");
        paginas.add(".gif");
        paginas.add(".jpg");
        paginas.add(".png");
        paginas.add(".woff");
        paginas.add(".js.xhtml");
        paginas.add(".svg.xhtml");
        paginas.add(".css.xhtml");
        paginas.add(".gif");
        return paginas;
    }

    public static Set<String> paginaComAcessoRestrito() {
        Set<String> paginas = new CopyOnWriteArraySet<>();
        paginas.add("/post/novo-post.xhtml");
        paginas.add("/post/alterar-post.xhtml");

        return paginas;
    }

    public static boolean validarAcessoEmPaginaRestrita(final String uri) {
        if (uri == null || uri.trim().isEmpty()) {
            return false;
        }
        for (String pagina : paginaComAcessoRestrito()) {
            if (uri.contains(pagina)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se a url de acesso é livre
     *
     * @param url
     * @return true se e somente se a url esta presente na lista de paginas de
     * acesso livre.
     */
    public synchronized static boolean isUrlAcessoLivre(final String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }

        return false;
    }
}
