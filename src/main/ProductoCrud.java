package main;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.TbProducto;

public class ProductoCrud {

    public static void main(String[] args) {
        System.out.println("=== JPA CRUD para Productos ===");
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("EP1_JPA");
        EntityManager em = fabrica.createEntityManager();

        // Listar todos los productos
        List<TbProducto> lstProductos = em.createQuery("SELECT p FROM TbProducto p", TbProducto.class).getResultList();
        System.out.println("==============================================");

        // Mostrar cantidad de productos
        System.out.println("Número de productos: " + lstProductos.size());
        System.out.println("==============================================");

        // Listar todos los productos
        System.out.println("Lista de productos:");
        System.out.println("-------------------");
        for (TbProducto producto : lstProductos) {
            System.out.println("ID: " + producto.getIdProd());
            System.out.println("Descripción: " + producto.getDesProd());
            System.out.println("Precio: " + producto.getPreProd());
            System.out.println("Stock: " + producto.getStkProd());
        }
        System.out.println("==============================================");

        // Búsqueda de producto por ID
        String productoIdABuscar = "P0001";
        System.out.println("Buscar producto con ID=" + productoIdABuscar);
        System.out.println("-----------------------------");
        TbProducto productoBuscado = em.find(TbProducto.class, productoIdABuscar);
        if (productoBuscado != null) {
            System.out.println("ID: " + productoBuscado.getIdProd());
            System.out.println("Descripción: " + productoBuscado.getDesProd());
            System.out.println("Precio: " + productoBuscado.getPreProd());
            System.out.println("Stock: " + productoBuscado.getStkProd());
        } else {
            System.out.println("Producto no encontrado.");
        }
        System.out.println("==============================================");

        /*
        // Insertar nuevo producto
        System.out.println("Insertar nuevo producto: Paracetamol");
        System.out.println("---------------------------------------------");
        em.getTransaction().begin();
        TbProducto nuevoProducto = new TbProducto();
        nuevoProducto.setIdProd("P0021");
        nuevoProducto.setDesProd("Paracetamol");
        nuevoProducto.setEstProd((byte) 1);
        nuevoProducto.setPreProd(new BigDecimal("5.50"));
        nuevoProducto.setStkProd(50);
        em.persist(nuevoProducto);
        em.getTransaction().commit();
        System.out.println("Producto insertado con éxito.");
        System.out.println("==============================================");
		*/
        
        // Actualizar producto
        System.out.println("Actualizar stock del producto con ID=P0002");
        System.out.println("---------------------------------------------");
        em.getTransaction().begin();
        TbProducto productoActualizar = em.find(TbProducto.class, "P0002");
        if (productoActualizar != null) {
            int nuevoStock = 50; // Establece el nuevo valor del stock
            productoActualizar.setStkProd(nuevoStock);
            em.getTransaction().commit();
            System.out.println("Stock del producto actualizado con éxito.");
            System.out.println("Nuevo stock del producto: " + productoActualizar.getStkProd());
        } else {
            System.out.println("Producto no encontrado para actualizar.");
        }
        System.out.println("==============================================");

		
        /*
        // Eliminar producto
        System.out.println("Eliminar producto con ID=P0021");
        System.out.println("---------------------------------------------");
        em.getTransaction().begin();
        TbProducto productoEliminar = em.find(TbProducto.class, "P0021");
        if (productoEliminar != null) {
            em.remove(productoEliminar);
            em.getTransaction().commit();
            System.out.println("Producto eliminado con éxito.");
        } else {
            System.out.println("Producto no encontrado para eliminar.");
        }
        System.out.println("==============================================");
		*/
        
        // Cerrando la conexión a la Base de datos
        fabrica.close();
        em.close();
        
      
    }
}
