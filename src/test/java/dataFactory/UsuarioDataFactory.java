package dataFactory;

import pojo.UsuarioPojo;

public class UsuarioDataFactory {
    public static UsuarioPojo setarUsuario(String login, String senha){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin(login);
        usuario.setUsuarioSenha(senha);

        return usuario;
    }

    public static UsuarioPojo criarNovoUsuario(String nome, String login, String senha){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioNome(nome);
        usuario.setUsuarioLogin(login);
        usuario.setUsuarioSenha(senha);

        return usuario;
    }
}
