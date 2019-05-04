package com.blogger.features.exceptions;

public class UsuarioNaoLogado extends RuntimeException{

    public UsuarioNaoLogado() {
        super("Usuario não está logado");
    }
    
    
}
