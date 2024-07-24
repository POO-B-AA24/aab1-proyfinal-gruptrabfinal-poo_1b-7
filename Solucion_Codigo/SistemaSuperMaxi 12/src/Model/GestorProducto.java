package Model;

import Controller.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorProducto {
    private static final String FILE_NAME = "productos.dat";
    private List<Producto> productos;

    public GestorProducto() {
        productos = new ArrayList<>();
        cargarProductos();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        guardarProductos();
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        guardarProductos();
    }

    private void guardarProductos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarProductos() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                productos = (List<Producto>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }
}


