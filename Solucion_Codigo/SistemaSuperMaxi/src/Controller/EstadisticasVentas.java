package Controller;

import java.util.HashMap;
import java.util.Map;

public class EstadisticasVentas {
    private Map<String, Integer> ventasPorProducto;
    private Map<CategoriaProducto, Integer> ventasPorCategoria;
    private double ventasTotales;

    public EstadisticasVentas() {
        ventasPorProducto = new HashMap<>();
        ventasPorCategoria = new HashMap<>();
        ventasTotales = 0.0;
    }

    public void registrarVenta(Producto producto) {
        ventasPorProducto.put(producto.getNombreProducto(), ventasPorProducto.getOrDefault(producto.getNombreProducto(), 0) + 1);
        ventasPorCategoria.put(producto.getCategoria(), ventasPorCategoria.getOrDefault(producto.getCategoria(), 0) + 1);
        ventasTotales += producto.getPrecioFinal();
    }

    public double getVentasTotales() {
        return ventasTotales;
    }

    public Map<String, Integer> getVentasPorProducto() {
        return ventasPorProducto;
    }

    public Map<CategoriaProducto, Integer> getVentasPorCategoria() {
        return ventasPorCategoria;
    }
}


