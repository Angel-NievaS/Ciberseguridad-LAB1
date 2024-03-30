package cl.lab.inventario.crud.services;

import cl.lab.inventario.crud.model.ProductoModel;

import java.util.List;

public interface ProductoService {

    void crearProductos(ProductoModel model);

    void modificarProducto(ProductoModel model, Long id);

    List<ProductoModel> listarProductos(String nombre);

    void eliminarProducto(Long id);


}
