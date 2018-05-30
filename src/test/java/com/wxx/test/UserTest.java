package com.wxx.test;

import java.util.EnumSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

import com.wxx.entity.User;

public class UserTest {
	@Test
	public void createTable() {
		ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		Metadata metadata = new MetadataSources(registry).buildMetadata();
		SchemaExport export = new SchemaExport();
		export.create(EnumSet.of(TargetType.DATABASE), metadata);
	}

	@Test
	public void addUser() {
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
		user.setId(102);
		user.setName("Weision");
		user.setPhone("3322326");

		// persisting the object
		// session.persist(user);
		// session.saveOrUpdate(user);
		session.save(user);

		t.commit();// transaction is committed
		session.close();

		System.out.println("successfully saved");

	}

	@Test
	public void queryUser() {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		User user = session.get(User.class, 102);

		t.commit();
		session.close();

		System.out.println("successfully queryUser: name[" + user.getName() + "]。");
	}

	@Test
	public void queryUserList() {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery("from User").getResultList();
		int i = 0;
		for (User user : users) {
			System.out.println("queryUserList[" + i + "] name :[" + user.getName() + "]");
			i++;
		}
		t.commit();
		session.close();
	}

	@Test
	public void deleteUser() {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		User user = new User();
		user.setId(0);
		user.setName("Weision");
		user.setPhone("3322326");
		
		session.delete(user);
		t.commit();
		session.close();
		System.out.println("successfully deleted");

	}

	@Test
	public void updateUser() {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		User user = new User();
		user.setId(101);
		user.setName("Json");
		user.setPhone("3322326");
		
		session.update(user);
		t.commit();
		session.close();
		System.out.println("successfully deleted");

	}
}
