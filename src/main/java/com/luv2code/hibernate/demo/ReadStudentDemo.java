package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Student;

public class ReadStudentDemo {

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
			Student theStudent = new Student("Daffy", "duck", "daffy@luv2code.com");
			
			//start/begin a transaction
			session.beginTransaction();
			
			//save the student object into the db
			System.out.println("saving student to the db");
			System.out.println(theStudent);
			session.save(theStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			//MY NEW CODE-FOR RETREIVE
		    //find out the student's id : primary key
			System.out.println("saved student's generated id :"+theStudent.getId());
			
			//start a new session & transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//get the student based on the student's id : primary key
			System.out.println("\nGetting student with Id :"+theStudent.getId());
			Student myStudent = session.get(Student.class, theStudent.getId());
			System.out.println("Get Completes :"+myStudent);
			
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
