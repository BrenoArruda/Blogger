package com.blogger.model.post;

import com.blogger.model.abstracts.AbstractVo;
import com.blogger.model.usuario.UsuarioVo;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb01_post")
public class PostVo extends AbstractVo{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb01_post_sequence")
    @SequenceGenerator(name = "tb01_post_sequence", sequenceName = "seq_tb01_post")
    @Column(name = "id")
    private Long id;
    
    @Column(name = "titulo")
    private String titulo ;
    
    @Column(name = "resumo")
    private String resumo ;
    
    @Column(name = "texto")
    private String texto ;
    
    @Column(name = "data_publicacao")
    private Date dataCriacao;
    
    @Column(name = "data_criacao")
    private Date dataPublicacao;

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    
    @ManyToOne
    @JoinColumn(name = "autor")
     private UsuarioVo autor;

    public UsuarioVo getAutor() {
        return autor;
    }

    public void setAutor(UsuarioVo autor) {
        this.autor = autor;
    }
    
    public PostVo (){
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final PostVo other = (PostVo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
