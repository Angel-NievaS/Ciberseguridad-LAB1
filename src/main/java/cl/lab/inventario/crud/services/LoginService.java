package cl.lab.inventario.crud.services;

import cl.lab.inventario.crud.model.UsuarioModel;

public interface LoginService {

    UsuarioModel login(String usuario, String password);

}
