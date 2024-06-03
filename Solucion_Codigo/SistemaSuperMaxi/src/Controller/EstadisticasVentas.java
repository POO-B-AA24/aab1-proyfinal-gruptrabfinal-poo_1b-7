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

    public void mostrarEstadisticas() {
        System.out.println("Estadísticas de Ventas:");
        System.out.println("Ventas Totales: " + ventasTotales);
        System.out.println("Ventas por Producto:");
        for (Map.Entry<String, Integer> entry : ventasPorProducto.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Ventas por Categoría:");
        for (Map.Entry<CategoriaProducto, Integer> entry : ventasPorCategoria.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

