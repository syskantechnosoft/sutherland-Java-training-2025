package com.example.day28hbndemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.day28hbndemo.model.Airline;

public class App 
{
    public static void main( String[] args )
    {
    	Configuration configuration = new Configuration();
    	configuration.configure("hibernate.cfg.xml");
    	
    	SessionFactory sessionFactory = configuration.buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	
    	//Insert Operation (Persist)
    	Airline airline = new Airline("indigo", "indigo.com", 9878675645l);
    	
    	session.getTransaction().begin();
    	session.persist(airline);
    	session.getTransaction().commit();
    	
    	//Find by ID
    	session.getTransaction().begin();
    	Airline a1 = session.find(Airline.class, 1);
    	System.out.println("Selected Airline :" + a1);
    	session.getTransaction().commit();
    	
    	//Update 
    	session.getTransaction().begin();
    	
    	a1.setUrl("https://www.indigo.com");
    	session.merge(a1);
    	session.getTransaction().commit();
    	
    	
    	session.close();
    	sessionFactory.close();
    }
}
