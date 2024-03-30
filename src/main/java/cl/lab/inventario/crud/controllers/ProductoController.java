package cl.lab.inventario.crud.controllers;

import cl.lab.inventario.crud.model.ProductoModel;
import cl.lab.inventario.crud.model.ResponseModel;
import cl.lab.inventario.crud.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @GetMapping("/productos/")
    ResponseModel<List<ProductoModel>> listarProductos(
            @RequestParam(name = "nombre", required = false) String nombre) {
        ResponseModel<List<ProductoModel>> result = new ResponseModel<>();
        result.setData(this.productoService.listarProductos(nombre));
        return result;
    }

    @PostMapping("/productos/")
    ResponseModel<Object> crearProducto(@RequestBody ProductoModel model) {
        this.productoService.crearProductos(model);
        return ResponseModel.builder()
                .message("Producto creado correctamente")
                .build();
    }

    @PutMapping("/productos/{id}")
    ResponseModel<Object> modificarProducto(
            @RequestBody ProductoModel model,
            @PathVariable("id") Long id) {
        this.productoService.modificarProducto(model, id);
        return ResponseModel.builder()
                .message("Producto modificado correctamente")
                .build();
    }

    @DeleteMapping("/productos/{id}")
    ResponseModel<Object> eliminarProducto(@PathVariable("id") Long id) {
        this.productoService.eliminarProducto(id);
        return ResponseModel.builder()
                .message("Producto eliminado correctamente")
                .build();
    }

}
