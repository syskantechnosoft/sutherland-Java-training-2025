package com.example.day27jpademo;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TrainerCRUD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Costly Resource (If a resource consume more memory or time or both) 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("day27jpademo");
		EntityManager em = emf.createEntityManager();
		
		try {
			//Insert Operation
//			Trainer t1 = new Trainer("trainer1@gmail.com","trainer1","M","sample");
//			Trainer t2 = new Trainer("trainer2@gmail.com","trainer2","F","test");
//			
//			em.getTransaction().begin();
//			em.persist(t1);
//			em.persist(t2);
//			em.getTransaction().commit();
//			System.out.println("Records Successfully Inserted!!!");
			
			//Find By Id
			em.getTransaction().begin();
			Trainer trainer = em.find(Trainer.class, 2);
			System.out.println("Selected Trainer Details :" + trainer);
			em.getTransaction().commit();
			
			//Update Operation
			em.getTransaction().begin();
			trainer.setLastName("data");
			trainer.setEmail("trainer2data@gmail.com");
			em.merge(trainer);
			em.getTransaction().commit();
			System.out.println("Record Updated Successfully");
			
//			
//			//Delete By Id
//			em.getTransaction().begin();
//			em.remove(trainer);
//			System.out.println("Record Deleted Successfully!!!");
//			em.getTransaction().commit();
			
			//Find ALL
			em.getTransaction().begin();
			List<Trainer> trainers = em.createNamedQuery("Trainer.findAll").getResultList();
			System.out.println("Trainers :" + trainers);
			em.getTransaction().commit();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
