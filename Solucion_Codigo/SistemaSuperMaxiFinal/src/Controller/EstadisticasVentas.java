package Controller;

import java.util.HashMap;
import java.util.Map;
import Model.*;

public class EstadisticasVentas {
    private Map<String, Integer> ventasPorProducto;
    private Map<Class<? extends Producto>, Integer> ventasPorCategoria;
    private double ventasTotales;

    public EstadisticasVentas() {
        ventasPorProducto = new HashMap<>();
        ventasPorCategoria = new HashMap<>();
        ventasTotales = 0.0;
    }

    public void registrarVenta(Producto producto) {
        ventasPorProducto.put(producto.nombreProducto, ventasPorProducto.getOrDefault(producto.nombreProducto, 0) + 1);
        ventasPorCategoria.put(producto.getClass(), ventasPorCategoria.getOrDefault(producto.getClass(), 0) + 1);
        ventasTotales += producto.getPrecioFinal();
    }

    public double getVentasTotales() {
        return ventasTotales;
    }

    public Map<String, Integer> getVentasPorProducto() {
        return ventasPorProducto;
    }

    public Map<Class<? extends Producto>, Integer> getVentasPorCategoria() {
        return ventasPorCategoria;
    }
}


