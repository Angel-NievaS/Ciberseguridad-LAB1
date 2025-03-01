package cl.lab.inventario.crud.services;

import cl.lab.inventario.crud.exceptions.CrudException;
import cl.lab.inventario.crud.model.UsuarioModel;
import cl.lab.inventario.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UsuarioService usuarioService;

    @Autowired
    public LoginServiceImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UsuarioModel login(String usuario, String password) {
        UsuarioModel usuarioModel = null;
        try {
            usuarioModel = this.usuarioService.obtenerUsuario(usuario, password);

            if (null == usuarioModel) {
                throw new CrudException("Usuario o Password incorrectos.");
            }
        } catch (Exception e) {
            throw new CrudException("Usuario o Password incorrectos.");
        }
        return usuarioModel;
    }
}
