package cl.lab.inventario.crud.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name = "servicio_sequence", sequenceName = "servicio_sequence", allocationSize = 1, initialValue = 2)
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicio_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name ="costo")
    private BigDecimal costo;
    
    @Column(name = "profesional")
    private String profesional;
}
