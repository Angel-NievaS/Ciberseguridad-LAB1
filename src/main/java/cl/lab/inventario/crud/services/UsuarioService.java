package cl.lab.inventario.crud.services;

import cl.lab.inventario.crud.model.UsuarioModel;

import java.util.List;

public interface UsuarioService {

    void crearUsuario(final UsuarioModel model);

    void modificarUsuario(final UsuarioModel model, Long id);

    void cambiarContrasena(final UsuarioModel model);

    List<UsuarioModel> listarUsuarios(final String username, final String nombre, final String apellido);

    void eliminarUsuario(final Long id);

    UsuarioModel obtenerUsuario(String username, String password);


}
