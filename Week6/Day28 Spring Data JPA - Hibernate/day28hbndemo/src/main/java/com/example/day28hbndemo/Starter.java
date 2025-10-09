package com.example.day28hbndemo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.day28hbndemo.model.Address;
import com.example.day28hbndemo.model.Course;
import com.example.day28hbndemo.model.Department;
import com.example.day28hbndemo.model.Employee;
import com.example.day28hbndemo.model.Student;
import com.example.day28hbndemo.util.HibernateUtil;

public class Starter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// One-to-One
		Address addr = new Address();
		addr.setCity("Chennai");
		addr.setState("TN");
		addr.setCountry("India");

		Employee emp = new Employee();
		emp.setName("Alice");
		emp.setEmail("alice@example.com");
		emp.setAddress(addr);

		// One-to-Many
		Department dept = new Department();
		dept.setName("Engineering");
		emp.setDepartment(dept);
		dept.getEmployees().add(emp);

		// Many-to-Many
		Student s1 = new Student();
		s1.setName("John");

		Course c1 = new Course();
		c1.setTitle("Hibernate");
		Course c2 = new Course();
		c2.setTitle("Spring Boot");

		s1.getCourses().add(c1);
		s1.getCourses().add(c2);
		c1.getStudents().add(s1);
		c2.getStudents().add(s1);

		session.persist(emp);
		session.persist(addr);
		session.persist(dept);
		session.persist(s1);
		session.persist(c1);
		session.persist(c2);

		tx.commit();
		session.close();
		
		System.out.println("✅ Data saved successfully.");
	}

}
