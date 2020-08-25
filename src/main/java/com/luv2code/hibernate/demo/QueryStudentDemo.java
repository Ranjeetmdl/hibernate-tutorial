package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.entities.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		//create the SessionFactory
		SessionFactory factory=new Configuration()
				               .configure("hibernate.cfg.xml")
				               .addAnnotatedClass(Student.class)
				               .buildSessionFactory();
		//create the session 
		Session session = factory.getCurrentSession();
		
		try {
			//start/begin a transaction
			session.beginTransaction();
			
			//query students
			Query<Student> theQuery = session.createQuery("from Student");
			List<Student> theStudents = theQuery.getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query student : last name='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			//display the student
			System.out.println("\n\nStudents with last name='Doe'");
			displayStudents(theStudents);
			
			//query students whose lastName='Doe' OR first Name='Daffy'
			theStudents=session.createQuery("from Student s where s.lastName='Doe'"
					+ " OR s.firstName='Daffy'").getResultList();
			System.out.println("\n\nStudents with last name='Doe' OR first name='Daffy'");
			displayStudents(theStudents);
			
			//query students whose email LIKE '%gmail.com'
			theStudents=session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
			System.out.println("\n\nStudents whose email LIKE '%gmail.com'");
			displayStudents(theStudents);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
		}finally{
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

}
