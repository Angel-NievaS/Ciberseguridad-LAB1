package cl.lab.inventario.crud.services;

import cl.lab.inventario.crud.entities.Rol;
import cl.lab.inventario.crud.entities.Usuario;
import cl.lab.inventario.crud.entities.UsuarioRol;
import cl.lab.inventario.crud.exceptions.CrudException;
import cl.lab.inventario.crud.model.RolModel;
import cl.lab.inventario.crud.model.UsuarioModel;
import cl.lab.inventario.crud.repository.RolRepository;
import cl.lab.inventario.crud.repository.UsuarioRepository;
import cl.lab.inventario.crud.repository.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioRolRepository usuarioRolRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public void crearUsuario(UsuarioModel model) {

        Rol rol = this.rolRepository.findById(model.getRol().getId())
                .get();

        Usuario usuario = Usuario.builder()
                .username(model.getUsername())
                .nombres(model.getNombres())
                .apellidos(model.getApellidos())
                .correo(model.getCorreo())
                .password(model.getPassword())
                .build();

        UsuarioRol uRol = this.usuarioRolRepository.findByUsuario(model.getUsername());
        if (null == uRol) {
            uRol = new UsuarioRol();
        }

        uRol.setUsuario(usuario);
        uRol.setRol(rol);

        this.usuarioRepository.save(usuario);
        this.usuarioRolRepository.save(uRol);

    }

    @Override
    public void modificarUsuario(UsuarioModel model, Long id) {

        Optional<Usuario> usuarioOpt = this.usuarioRepository.findById(id);

        if (usuarioOpt.isEmpty()) {
            throw new CrudException("Usuario no encontrado.");
        }

        Rol rol = this.rolRepository.findById(model.getRol().getId()).get();

        Usuario usuario = usuarioOpt.get();
        usuario.setApellidos(model.getApellidos());
        usuario.setNombres(model.getNombres());
        usuario.setCorreo(model.getCorreo());


        UsuarioRol uRol = this.usuarioRolRepository.findByUsuario(usuario.getUsername());


        uRol.setUsuario(usuario);
        uRol.setRol(rol);

        this.usuarioRolRepository.save(uRol);


        this.usuarioRepository.save(usuario);

    }

    @Override
    public void cambiarContrasena(UsuarioModel model) {
        Optional<Usuario> usuarioOpt = this.usuarioRepository.findById(model.getId());

        if (usuarioOpt.isPresent()) {
            throw new CrudException("Usuario no encontrado.");
        }

        Usuario usuario = usuarioOpt.get();
        usuario.setPassword(model.getPassword());

        this.usuarioRepository.save(usuario);
    }

    @Override
    public List<UsuarioModel> listarUsuarios(String username, String nombre, String apellido) {
        List<UsuarioModel> result = this.usuarioRepository.findUsuariosByFilters(nombre, apellido, username);
        result.forEach(this::completarRoles);

        return result;
    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = this.usuarioRepository.findById(id).get();
        UsuarioRol uRol = this.usuarioRolRepository.findByUsuario(usuario.getUsername());
        this.usuarioRolRepository.delete(uRol);
        this.usuarioRepository.delete(usuario);
    }

    @Override
    public UsuarioModel obtenerUsuario(String username, String password) {
        UsuarioModel u = this.usuarioRepository.obtenerUsuario(username, password);
        this.completarRoles(u);
        return u;
    }

    private void completarRoles(final UsuarioModel model) {
        UsuarioRol usuarioRol = this.usuarioRolRepository.findByUsuario(model.getUsername());
        Rol rol = usuarioRol.getRol();
        model.setRol(
                RolModel.builder()
                        .id(rol.getId())
                        .descripcion(rol.getDescripcion())
                        .build());
    }
}
