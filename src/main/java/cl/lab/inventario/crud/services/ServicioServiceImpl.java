package cl.lab.inventario.crud.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.lab.inventario.crud.repository.ServicioRepository;
import cl.lab.inventario.crud.entities.Servicio;
import cl.lab.inventario.crud.exceptions.CrudException;
import cl.lab.inventario.crud.model.ServicioModel;


@Service
public class ServicioServiceImpl implements ServicioService {
	
	private final ServicioRepository servicioRepository;
	
    @Autowired
    public ServicioServiceImpl(ServicioRepository ServicioRepository) {
        this.servicioRepository = ServicioRepository;
    }


	@Override
	public void crearServicio(ServicioModel model) {
		Servicio servicio = Servicio.builder()
                .costo(BigDecimal.valueOf(model.getCosto()))
                .nombre(model.getNombre())
                .descripcion(model.getDescripcion())
                .profesional(model.getProfesional())
                .build();
		servicioRepository.save(servicio);
	}

	@Override
	public void modificarServicio(ServicioModel model, Long id) {
        Optional<Servicio> servicioOpt = this.servicioRepository.findById(id);
        if (servicioOpt.isEmpty()) {
            throw new CrudException("servicio no encontrado.");
        }

        Servicio servicio = servicioOpt.get();
        servicio.setCosto(BigDecimal.valueOf(model.getCosto()));
        servicio.setNombre(model.getNombre());
        servicio.setDescripcion(model.getDescripcion());
        servicio.setProfesional(model.getProfesional());

        this.servicioRepository.save(servicio);
		
	}

	@Override
	public List<ServicioModel> listarServicios(String nombre) {
		return this.servicioRepository.listarServicios(nombre);
	}

	@Override
	public void eliminarServicio(Long id) {
        Optional<Servicio> opt = this.servicioRepository.findById(id);
        opt.ifPresent(this.servicioRepository::delete);
		
	}

}
