package View;

import Controller.*;
import Model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestSistemaSuperMaxi {
    private static GestorProducto gestorProducto = new GestorProducto();
    private static EstadisticasVentas estadisticasVentas = new EstadisticasVentas();

    public static void main(String[] args) {
        Scanner tc = new Scanner(System.in);
        System.out.println("BIENVENIDO AL SISTEMA SUPERMAXI");
        System.out.println("===============================\n");

        OUTER:
        while (true) {
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

                    System.out.println("Categoría (1: Vivienda, 2: Educación, 3: Alimentación, 4: Vestimenta, 5: Salud): ");
                    int categoriaInt = tc.nextInt();
                    tc.nextLine(); // Consumir el salto de línea
                    CategoriaProducto categoria = CategoriaProducto.values()[categoriaInt - 1];

                    Producto producto = new Producto(nombre, fechaCaducidad, precioNormal, precioPromocional, cantidadEnStock, categoria);
                    gestorProducto.agregarProducto(producto);

                    System.out.println("Producto agregado con éxito.");
                    break;
                case 2:
                    if (gestorProducto.getProductos().isEmpty()) {
                        System.out.println("No hay productos disponibles.");
                    } else {
                        for (Producto prod : gestorProducto.getProductos()) {
                            System.out.println(prod);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el nombre: ");
                    String nombreUsuario = tc.nextLine();

                    System.out.print("Ingrese el ruc o cedula: ");
                    String ruc_cedula = tc.nextLine();

                    System.out.print("Ingrese la direccion: ");
                    String direccion = tc.nextLine();

                    System.out.print("Ingrese el numero de telefono: ");
                    String telefono = tc.nextLine();

                    Cliente cliente = new Cliente(nombreUsuario, ruc_cedula, direccion, telefono);
                    Factura factura = new Factura(cliente);

                    while (true) {
                        System.out.println("Productos disponibles:");
                        for (int i = 0; i < gestorProducto.getProductos().size(); i++) {
                            Producto prod = gestorProducto.getProductos().get(i);
                            System.out.println((i + 1) + ". " + prod + " (Stock: " + prod.getStock() + ")");
                        }
                        System.out.println("Seleccione un producto por número (o 0 para terminar): ");
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

                    System.out.println(factura);
                    break;
                case 4:
                    estadisticasVentas.mostrarEstadisticas();
                    break;
                case 5:
                    break OUTER;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        tc.close();
    }
}


