package com.blogger.model.usuario;

import com.blogger.model.abstracts.AbstractVo;
import com.blogger.model.post.PostVo;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb02_usuario")
public class UsuarioVo extends AbstractVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    /*
    private Bolean status;
    private Date criacao;
    private Date ativacao;
    private String tokenAtivacao;
    private Date tokenCriacao;*/
    public UsuarioVo() {

    }

    public Long getId() {
        return id;
    }

    /*  public String getTokenAtivacao() {
        return tokenAtivacao;
    }

    public void setTokenAtivacao(String tokenAtivacao) {
        this.tokenAtivacao = tokenAtivacao;
    }

    public Date getTokenCriacao() {
        return tokenCriacao;
    }

    public void setTokenCriacao(Date tokenCriacao) {
        this.tokenCriacao = tokenCriacao;
    }*/
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostVo> posts;

    public List<PostVo> getPosts() {
        return posts;
    }

    public void setPosts(List<PostVo> posts) {
        this.posts = posts;
    }
    
    /*
    public Bolean getStatus() {
        return status;
    }

    public void setStatus(Bolean status) {
        this.status = status;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Date getAtivacao() {
        return ativacao;
    }

    public void setAtivacao(Date ativacao) {
        this.ativacao = ativacao;
    }*/
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioVo other = (UsuarioVo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - NOME: " + nome;

    }

}
