package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		//create the SessionFactory
		SessionFactory factory=new Configuration()
				               .configure("hibernate.cfg.xml")
				               .addAnnotatedClass(Student.class)
				               .buildSessionFactory();
		//create the session 
		Session session = factory.getCurrentSession();
		
		try {
			int studentId=1;
			
			//start/begin a transaction
			session.beginTransaction();
			
			//get the student with give Id:primary key
			System.out.println("retrieving the student with id :"+studentId);
			Student theStudent = session.get(Student.class, studentId);
			
			//update the student
			System.out.println("update the student with id :"+studentId);
			theStudent.setFirstName("Scooby");

			//commit the transaction
			session.getTransaction().commit();
			
			//NEW CODE TO BULK UPDATE	
			//create a new session and transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//update the email address of all the students
			int rowUpdate = session.createQuery("update Student s set s.email='foobar.gmail.com' ").executeUpdate();
			System.out.println("\n\nUpdate email address for :"+rowUpdate+" students");
			
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
