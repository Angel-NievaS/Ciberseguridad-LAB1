package cl.lab.inventario.crud.model;

import cl.lab.inventario.crud.entities.Servicio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicioModel {
	
    private Long id;
    private Double costo;
    private String descripcion;
    private String nombre;
    private String profesional;
    
    public ServicioModel(Servicio s) {
        super();
        this.id = s.getId();
        this.costo = s.getCosto().doubleValue();
        this.descripcion = s.getDescripcion();
        this.nombre = s.getNombre();
        this.profesional = s.getProfesional();
    }

}
