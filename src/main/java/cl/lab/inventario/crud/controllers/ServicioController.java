package cl.lab.inventario.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.lab.inventario.crud.model.ResponseModel;
import cl.lab.inventario.crud.model.ServicioModel;
import cl.lab.inventario.crud.services.ServicioService;

@RestController
public class ServicioController {
	
	private final ServicioService servicioService;
	
    @Autowired
    public ServicioController(ServicioService servicioService) {
    	this.servicioService = servicioService;
    }
    
    @GetMapping("/servicios/")
    ResponseModel<List<ServicioModel>> listarServicios(
            @RequestParam(name = "nombre", required = false) String nombre) {
        ResponseModel<List<ServicioModel>> result = new ResponseModel<>();
        result.setData(this.servicioService.listarServicios(nombre));
        return result;
    }
    
    @PostMapping("/servicios/")
    ResponseModel<Object> crearServicio(@RequestBody ServicioModel model) {
        this.servicioService.crearServicio(model);
        return ResponseModel.builder()
                .message("Servicio creado correctamente")
                .build();
    }
    
    @PutMapping("/servicios/{id}")
    ResponseModel<Object> modificarServicio(
            @RequestBody ServicioModel model,
            @PathVariable("id") Long id) {
        this.servicioService.modificarServicio(model, id);
        return ResponseModel.builder()
                .message("Servicio modificado correctamente")
                .build();
    }
    
    @DeleteMapping("/servicios/{id}")
    ResponseModel<Object> eliminarServicio(@PathVariable("id") Long id) {
        this.servicioService.eliminarServicio(id);
        return ResponseModel.builder()
                .message("Servicio eliminado correctamente")
                .build();
    }

}
