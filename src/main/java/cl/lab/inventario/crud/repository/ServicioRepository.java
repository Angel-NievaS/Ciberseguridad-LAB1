package cl.lab.inventario.crud.repository;

import cl.lab.inventario.crud.entities.Servicio;
import cl.lab.inventario.crud.model.ServicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query("SELECT new cl.lab.inventario.crud.model.ServicioModel(s) " +
            "FROM Servicio s " +
            "WHERE 1 = 1 " +
            "AND (:nombre IS NULL OR s.nombre LIKE %:nombre%) ")
    List<ServicioModel> listarServicios(@Param("nombre") String nombre);
}

