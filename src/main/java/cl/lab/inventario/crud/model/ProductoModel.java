package cl.lab.inventario.crud.model;

import cl.lab.inventario.crud.entities.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoModel {

    private Long id;
    private Double costo;
    private String descripcion;
    private String nombre;
    
    public ProductoModel(Producto p) {
        super();
        this.id = p.getId();
        this.costo = p.getCosto().doubleValue();
        this.descripcion = p.getDescripcion();
        this.nombre = p.getNombre();
    }



}
