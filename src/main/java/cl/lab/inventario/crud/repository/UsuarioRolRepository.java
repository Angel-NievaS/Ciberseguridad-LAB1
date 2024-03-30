package cl.lab.inventario.crud.repository;

import cl.lab.inventario.crud.entities.Usuario;
import cl.lab.inventario.crud.entities.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

    @Query("SELECT ur FROM UsuarioRol ur WHERE ur.usuario.username = :username ")
    UsuarioRol findByUsuario(String username);

}
