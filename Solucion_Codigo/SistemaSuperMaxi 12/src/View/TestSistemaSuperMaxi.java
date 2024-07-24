package View;

import Controller.*;
import Model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Map;

public class TestSistemaSuperMaxi {
    private static GestorProducto gestorProducto = new GestorProducto();
    private static EstadisticasVentas estadisticasVentas = new EstadisticasVentas();

    public static void main(String[] args) {
        Scanner tc = new Scanner(System.in);
        System.out.println("===================================================");
        System.out.println("          BIENVENIDO AL SISTEMA SUPERMAXI          ");
        System.out.println("===================================================\n");

        OUTER:
        while (true) {
            System.out.println("\nMENÚ PRINCIPAL");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Nueva Factura");
            System.out.println("4. Mostrar estadísticas de ventas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = tc.nextInt();
            tc.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("\nAGREGAR NUEVO PRODUCTO");
                    System.out.print("Nombre del producto: ");
                    String nombre = tc.nextLine();

                    System.out.print("Precio normal: ");
                    double precioNormal = tc.nextDouble();

                    System.out.print("Precio promocional: ");
                    double precioPromocional = tc.nextDouble();

                    System.out.print("Cantidad en stock: ");
                    int cantidadEnStock = tc.nextInt();
                    tc.nextLine(); // Consumir el salto de línea

                    System.out.print("Fecha de caducidad (yyyy-MM-dd): ");
                    String fechaCaducidadStr = tc.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate fechaCaducidad = LocalDate.parse(fechaCaducidadStr, formatter);

                    System.out.print("Categoría (1: Vivienda, 2: Educación, 3: Alimentación, 4: Vestimenta, 5: Salud): ");
                    int categoriaInt = tc.nextInt();
                    tc.nextLine(); // Consumir el salto de línea
                    CategoriaProducto categoria = CategoriaProducto.values()[categoriaInt - 1];

                    Producto producto = new Producto(nombre, fechaCaducidad, precioNormal, precioPromocional, cantidadEnStock, categoria);
                    gestorProducto.agregarProducto(producto);

                    System.out.println("\nProducto agregado con éxito.");
                    break;

                case 2:
                    System.out.println("\nMOSTRAR PRODUCTOS");
                    if (gestorProducto.getProductos().isEmpty()) {
                        System.out.println("No hay productos disponibles.");
                    } else {
                        for (Producto prod : gestorProducto.getProductos()) {
                            System.out.println(prod);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nCREAR NUEVA FACTURA");
                    System.out.print("Ingrese el nombre: ");
                    String nombreUsuario = tc.nextLine();

                    System.out.print("Ingrese el RUC o cédula: ");
                    String rucCedula = tc.nextLine();

                    System.out.print("Ingrese la dirección: ");
                    String direccion = tc.nextLine();

                    System.out.print("Ingrese el número de teléfono: ");
                    String telefono = tc.nextLine();
                    
                    System.out.print("¿Es un cliente frecuente? (s/n): ");
                    String esFrecuente = tc.nextLine();

                    Cliente cliente;
                    if (esFrecuente.equalsIgnoreCase("s")) {
                        System.out.print("Ingrese los puntos acumulados: ");
                        int puntosAcumulados = tc.nextInt();
                        tc.nextLine(); // Consumir el salto de línea
                        cliente = new ClienteFrecuente(nombreUsuario, rucCedula, direccion, telefono, puntosAcumulados);
                    } else {
                        System.out.print("Ingrese el nombre de la empresa: ");
                        String nombreEmpresa = tc.nextLine();
                        cliente = new ClienteEmpresa(nombreEmpresa, nombreUsuario, rucCedula, direccion, telefono);
                    }

                    Factura factura = new Factura(cliente);

                    while (true) {
                        System.out.println("\nPRODUCTOS DISPONIBLES");
                        for (int i = 0; i < gestorProducto.getProductos().size(); i++) {
                            Producto prod = gestorProducto.getProductos().get(i);
                            System.out.println((i + 1) + ". " + prod + " (Stock: " + prod.getStock() + ")");
                        }
                        System.out.print("Seleccione un producto por número (o 0 para terminar): ");
                        int productoSeleccionado = tc.nextInt();
                        tc.nextLine(); // Consumir el salto de línea

                        if (productoSeleccionado == 0) {
                            break;
                        }

                        Producto productoSeleccionadoObj = gestorProducto.getProductos().get(productoSeleccionado - 1);

                        if (productoSeleccionadoObj.getStock() > 0) {
                            productoSeleccionadoObj.reducirStock(1); // Reducir el stock en 1
                            if (productoSeleccionadoObj.getStock() == 0) {
                                gestorProducto.eliminarProducto(productoSeleccionadoObj); // Eliminar producto si el stock es 0
                            }
                            factura.agregarProducto(productoSeleccionadoObj);
                            estadisticasVentas.registrarVenta(productoSeleccionadoObj);
                            System.out.println("Producto agregado al carrito.");
                        } else {
                            System.out.println("Stock insuficiente para el producto seleccionado.");
                        }
                    }

                    
                    System.out.println("\nFACTURA GENERADA");
                    System.out.println(factura);
                    System.out.printf("Total con descuento: %.2f\n", factura.getTotalConDescuento());
                    break;

                case 4:
                    System.out.println("\nESTADÍSTICAS DE VENTAS");
                    System.out.println("Ventas Totales: " + estadisticasVentas.getVentasTotales());
                    System.out.println("Ventas por Producto:");
                    for (Map.Entry<String, Integer> entry : estadisticasVentas.getVentasPorProducto().entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    System.out.println("Ventas por Categoría:");
                    for (Map.Entry<CategoriaProducto, Integer> entry : estadisticasVentas.getVentasPorCategoria().entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;

                case 5:
                    System.out.println("\nGracias por usar el sistema SuperMaxi. ¡Hasta luego!");
                    break OUTER;

                default:
                    System.out.println("\nOpción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }

        tc.close();
    }
}
