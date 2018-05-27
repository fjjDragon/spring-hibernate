package com.wxx.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.wxx.entity.User;

public class Client {
	public static void main(String[] args) {
		// creating configuration object
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the configuration file

		// creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = factory.openSession();

		// creating transaction object
		Transaction t = session.beginTransaction();

		User user = new User();
		user.setId(100);
		user.setName("Weision");
		user.setPhone("3322326");

		session.persist(user);// persisting the object

		t.commit();// transaction is committed
		session.close();

		System.out.println("successfully saved");
	}
}
