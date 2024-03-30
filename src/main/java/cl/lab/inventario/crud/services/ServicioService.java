package cl.lab.inventario.crud.services;

import java.util.List;

import cl.lab.inventario.crud.model.ServicioModel;

public interface ServicioService {

    void crearServicio(ServicioModel model);

    void modificarServicio(ServicioModel model, Long id);

    List<ServicioModel> listarServicios(String nombre);

    void eliminarServicio(Long id);
}
