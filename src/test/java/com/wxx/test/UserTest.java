package com.wxx.test;

import java.util.EnumSet;

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
	    export.create(EnumSet.of(TargetType.DATABASE),metadata);  
	}

	@Test
	public void add() {
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
		user.setId(101);
		user.setName("Weision");
		user.setPhone("3322326");

		session.persist(user);// persisting the object

		t.commit();// transaction is committed
		session.close();

		System.out.println("successfully saved");

	}
}
