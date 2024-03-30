package cl.lab.inventario.crud.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@SequenceGenerator(name = "usuario_rol_sequence", sequenceName = "usuario_rol_sequence", allocationSize = 1, initialValue = 4)
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_rol_sequence")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private Rol rol;

}
