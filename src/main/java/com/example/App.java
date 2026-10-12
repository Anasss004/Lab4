package com.example;

import com.example.model.joined.Developpeur;
import com.example.model.joined.Employe;
import com.example.model.joined.Manager;
import com.example.model.singletable.Moto;
import com.example.model.singletable.Vehicule;
import com.example.model.singletable.Voiture;
import com.example.model.tableperclass.Electronique;
import com.example.model.tableperclass.Livre;
import com.example.model.tableperclass.Produit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-inheritance");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // 1. Stratégie SINGLE_TABLE
            Voiture voiture = new Voiture("Toyota", 4);
            Moto moto = new Moto("Harley", true);
            em.persist(voiture);
            em.persist(moto);

            // 2. Stratégie JOINED
            Developpeur dev = new Developpeur("Alice", "Java");
            Manager manager = new Manager("Bob", 3);
            em.persist(dev);
            em.persist(manager);

            // 3. Stratégie TABLE_PER_CLASS
            Livre livre = new Livre("Clean Code", "Robert C. Martin");
            Electronique electronique = new Electronique("Smartphone", 24);
            em.persist(livre);
            em.persist(electronique);

            tx.commit();

            // Affichage
            System.out.println("\n--- Liste des véhicules (SINGLE_TABLE) ---");
            em.createQuery("SELECT v FROM Vehicule v", Vehicule.class)
                    .getResultList()
                    .forEach(v -> System.out.println(v.getClass().getSimpleName() + " : " + v.getConstructeur()));

            System.out.println("\n--- Liste des employés (JOINED) ---");
            em.createQuery("SELECT e FROM Employe e", Employe.class)
                    .getResultList()
                    .forEach(e -> System.out.println(e.getClass().getSimpleName() + " : " + e.getNom()));

            System.out.println("\n--- Liste des produits (TABLE_PER_CLASS) ---");
            em.createQuery("SELECT p FROM Produit p", Produit.class)
                    .getResultList()
                    .forEach(p -> System.out.println(p.getClass().getSimpleName() + " : " + p.getNom()));

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}