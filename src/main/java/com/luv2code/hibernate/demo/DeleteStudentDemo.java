package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Student;

public class DeleteStudentDemo {

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
			
			/*
			//get the student with give Id:primary key
			System.out.println("retrieving the student with id :"+studentId);
			Student theStudent = session.get(Student.class, studentId);
			
			//delete the student
			System.out.println("delete the student :"+theStudent);
			session.delete(theStudent);
			*/
			
			//delete the student with id(ie primary key):5
			System.out.println("\n\ndeleting the student with id :"+5);
			int rowDeleted = session.createQuery("delete from Student s where s.id=5").executeUpdate();
			System.out.println("deleted :"+rowDeleted+" students");

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
