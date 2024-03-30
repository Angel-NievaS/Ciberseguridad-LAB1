package cl.lab.inventario.crud.repository;

import cl.lab.inventario.crud.entities.Producto;
import cl.lab.inventario.crud.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT new cl.lab.inventario.crud.model.ProductoModel(p) " +
            "FROM Producto p " +
            "WHERE 1 = 1 " +
            "AND (:nombre IS NULL OR p.nombre LIKE %:nombre%) ")
    List<ProductoModel> listarProductos(@Param("nombre") String nombre);

}
