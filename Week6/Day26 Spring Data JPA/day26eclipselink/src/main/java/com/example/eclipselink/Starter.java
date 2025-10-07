package com.example.eclipselink;

import java.math.BigInteger;
import java.util.List;

import com.example.eclipselink.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Starter {

	public static EntityManagerFactory createEntityManagerFactory() {
		// "my-persistence-unit" must match the name in persistence.xml
		return Persistence.createEntityManagerFactory("day26eclipselink");
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = createEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			// === 1️⃣ Create / Save ===
			em.getTransaction().begin();

			Employee e1 = new Employee(0, "test@example.com", new BigInteger("9876543210"), "test");
			Employee e2 = new Employee(0, "user@example.com", new BigInteger("9988776655"), "user");
			em.persist(e1);
			em.persist(e2);

			em.getTransaction().commit();
			System.out.println("✅ Employees saved successfully!\n");

			// === 2️⃣ Find All (Named Query) ===
			System.out.println("=== Employee List (findAll) ===");
			List<Employee> employees = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
			employees.forEach(System.out::println);

			// === 3️⃣ Find by ID ===
			System.out.println("\n=== Find by ID ===");
			Employee found = em.find(Employee.class, e1.getId());
			System.out.println("Found: " + found);

			// === 4️⃣ Update ===
			System.out.println("\n=== Updating Employee ===");
			em.getTransaction().begin();
			found.setName("John Updated");
			found.setEmail("john.updated@example.com");
			em.merge(found); // optional if managed
			em.getTransaction().commit();
			System.out.println("✅ Updated Employee: " + found);

			// === 5️ JPQL Query Example ===
			System.out.println("\n=== JPQL Query: Find by Name ===");
			Query jpqlQuery = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name");
			jpqlQuery.setParameter("name", "test");
			List<Employee> result = jpqlQuery.getResultList();
			result.forEach(System.out::println);

			// === 6️ Native Query Example ===
			System.out.println("\n=== Native SQL Query ===");
			Query nativeQuery = em.createNativeQuery("SELECT * FROM employee", Employee.class);
			List<Employee> nativeResults = nativeQuery.getResultList();
			nativeResults.forEach(System.out::println);

			// === 7️⃣ Delete ===
			System.out.println("\n=== Deleting Employee ===");
			em.getTransaction().begin();
			Employee toDelete = em.find(Employee.class, e2.getId());
			if (toDelete != null) {
				em.remove(toDelete);
				System.out.println("✅ Deleted Employee: " + toDelete);
			} else {
				System.out.println("⚠️ Employee not found for deletion.");
			}
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
