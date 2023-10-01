package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.TbUsuario;

public class Pruebas {
	
	public static void main(String[] args) {
        System.out.println("===JPA CRUD====");
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("EP1_JPA");
        EntityManager em = fabrica.createEntityManager();

        try {
            // Realizar una consulta de prueba
            TbUsuario usuario = em.find(TbUsuario.class, 2); // Cambia el ID seg�n tu base de datos
            System.out.println("Usuario recuperado: " + usuario.getNomUsua());
        } catch (Exception e) {
            System.err.println("Error al realizar la prueba de conexi�n: " + e.getMessage());
        } finally {
            // Cerrar el EntityManager y la f�brica
            em.close();
            fabrica.close();
        }
    }

}
