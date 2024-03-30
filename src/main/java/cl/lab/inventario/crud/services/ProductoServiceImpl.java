package cl.lab.inventario.crud.services;

import cl.lab.inventario.crud.entities.Producto;
import cl.lab.inventario.crud.exceptions.CrudException;
import cl.lab.inventario.crud.model.ProductoModel;
import cl.lab.inventario.crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void crearProductos(ProductoModel model) {
        Producto producto = Producto.builder()
                .costo(BigDecimal.valueOf(model.getCosto()))
                .nombre(model.getNombre())
                .descripcion(model.getDescripcion())
                .build();

        productoRepository.save(producto);
    }

    @Override
    public void modificarProducto(ProductoModel model, Long id) {

        Optional<Producto> productoOpt = this.productoRepository.findById(id);
        if (productoOpt.isEmpty()) {
            throw new CrudException("producto no encontrado.");
        }

        Producto producto = productoOpt.get();
        producto.setCosto(BigDecimal.valueOf(model.getCosto()));
        producto.setNombre(model.getNombre());
        producto.setDescripcion(model.getDescripcion());

        this.productoRepository.save(producto);

    }

    @Override
    public List<ProductoModel> listarProductos(String nombre) {
        return this.productoRepository.listarProductos(nombre);
    }

    @Override
    public void eliminarProducto(Long id) {
        Optional<Producto> opt = this.productoRepository.findById(id);
        opt.ifPresent(this.productoRepository::delete);
    }
}
