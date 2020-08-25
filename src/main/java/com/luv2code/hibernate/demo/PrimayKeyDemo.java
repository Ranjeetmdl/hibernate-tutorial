package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Student;

public class PrimayKeyDemo {

	public static void main(String[] args) {
		
		// create the SessionFactory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create the session
		Session session = factory.getCurrentSession();

		try {
			// create 3 new student object
			System.out.println("creating 3 new Student Object");
			Student theStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student theStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
			Student theStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

			// start/begin a transaction
			session.beginTransaction();

			// save the student objects into the db
			System.out.println("saving students to the db");
			session.save(theStudent1);
			session.save(theStudent2);
			session.save(theStudent3);

			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");

		} catch (Exception e) {
			System.out.println("Issue while saving the student objects!");
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
