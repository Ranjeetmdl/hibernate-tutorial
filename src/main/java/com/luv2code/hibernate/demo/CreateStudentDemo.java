package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		//create the SessionFactory
		SessionFactory factory=new Configuration()
				               .configure("hibernate.cfg.xml")
				               .addAnnotatedClass(Student.class)
				               .buildSessionFactory();
		//create the session 
		Session session = factory.getCurrentSession();
		
		try {
			//create a new student object
			System.out.println("creating a new Student Object");
			Student theStudent = new Student("Paul", "wall", "paul@luv2code.com");
			
			//start/begin a transaction
			session.beginTransaction();
			
			//save the student object into the db
			System.out.println("saving student to the db");
			session.save(theStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
		}finally{
			factory.close();
		}
	}

}
