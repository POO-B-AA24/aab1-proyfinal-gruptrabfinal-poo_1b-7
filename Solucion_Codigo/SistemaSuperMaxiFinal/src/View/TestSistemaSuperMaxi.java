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

                    Producto producto = null;
                    switch (categoriaInt) {
                        case 1:
                            producto = new ProductoVivienda(nombre, fechaCaducidad, precioNormal, precioPromocional, cantidadEnStock);
                            break;
                        case 2:
                            producto = new ProductoEducacion(nombre, fechaCaducidad, precioNormal, precioPromocional, cantidadEnStock);
                            break;
                        case 3:
                            producto = new ProductoAlimentacion(nombre, fechaCaducidad, precioNormal, precioPromocional, cantidadEnStock);
                            break;
                        case 4:
                            producto = new ProductoVestimenta(nombre, fechaCaducidad, precioNormal, precioPromocional, cantidadEnStock);
                            break;
                        case 5:
                            producto = new ProductoSalud(nombre, fechaCaducidad, precioNormal, precioPromocional, cantidadEnStock);
                            break;
                        default:
                            System.out.println("Categoría no válida");
                            continue OUTER;
                    }

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
                    System.out.println("Seleccione el tipo de cliente:");
                    System.out.println("1. Cliente Nuevo");
                    System.out.println("2. Cliente Frecuente");
                    System.out.println("3. Cliente Empresa");
                    int tipoCliente = tc.nextInt();
                    tc.nextLine(); // Consumir el salto de línea

                    System.out.print("Ingrese el nombre: ");
                    String nombreUsuario = tc.nextLine();

                    System.out.print("Ingrese el ruc o cedula: ");
                    int ruc_cedula = tc.nextInt();
                    tc.nextLine();

                    System.out.print("Ingrese la direccion: ");
                    String direccion = tc.nextLine();

                    System.out.print("Ingrese el numero de telefono: ");
                    int telefono = tc.nextInt();
                    tc.nextLine();

                    Cliente cliente = null;
                    switch (tipoCliente) {
                        case 1:
                            cliente = new ClienteNuevo(nombreUsuario, ruc_cedula, direccion, telefono);
                            break;
                        case 2:
                            cliente = new ClienteFrecuente(nombreUsuario, ruc_cedula, direccion, telefono);
                            break;
                        case 3:
                            cliente = new ClienteEmpresa(nombreUsuario, ruc_cedula, direccion, telefono);
                            break;
                        default:
                            System.out.println("Tipo de cliente no válido");
                            continue OUTER;
                    }

                    Factura factura = new Factura(cliente);

                    while (true) {
                        System.out.println("Productos disponibles:");
                        for (int i = 0; i < gestorProducto.getProductos().size(); i++) {
                            Producto prod = gestorProducto.getProductos().get(i);
                            System.out.println((i + 1) + ". " + prod + " (Stock: " + prod.stock + ")");
                        }
                        System.out.println("Seleccione un producto por número (o 0 para terminar): ");
                        int productoSeleccionado = tc.nextInt();
                        tc.nextLine(); // Consumir el salto de línea

                        if (productoSeleccionado == 0) {
                            break;
                        }

                        Producto productoSeleccionadoObj = gestorProducto.getProductos().get(productoSeleccionado - 1);

                        if (productoSeleccionadoObj.stock > 0) {
                            productoSeleccionadoObj.reducirStock(1); // Reducir el stock en 1
                            if (productoSeleccionadoObj.stock == 0) {
                                gestorProducto.eliminarProducto(productoSeleccionadoObj); // Eliminar producto si el stock es 0
                            }
                            factura.agregarProducto(productoSeleccionadoObj);
                            estadisticasVentas.registrarVenta(productoSeleccionadoObj);
                            System.out.println("Producto agregado al carrito.");
                        } else {
                            System.out.println("Stock insuficiente para el producto seleccionado.");
                        }
                    }

                    // Acumular puntos para ClienteFrecuente si la compra supera los $25
                    if (factura.total > 25 && cliente instanceof ClienteFrecuente) {
                        ((ClienteFrecuente) cliente).acumularPuntos(10);
                    }
                    
                    factura.guardarFactura();
                    System.out.println(factura);
                    break;
                case 4:
                    System.out.println("Estadísticas de ventas:");
                    for (Map.Entry<Class<? extends Producto>, Integer> entry : estadisticasVentas.getVentasPorCategoria().entrySet()) {
                        System.out.println("Categoría: " + entry.getKey().getSimpleName() + ", Ventas: " + entry.getValue());
                    }
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break OUTER;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}

