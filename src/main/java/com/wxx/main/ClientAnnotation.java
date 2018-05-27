package com.wxx.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.wxx.entity.Students;

public class ClientAnnotation {
	public static void main(String[] args) {
		// Session session = new
		// AnnotationConfiguration().configure().buildSessionFactory().openSession();
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		// 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		/**** 上面是配置准备，下面开始我们的数据库操作 ******/
		Session session = sessionFactory.openSession();// 从会话工厂获取一个session
		Transaction t = session.beginTransaction();

		Students s1 = new Students();
		s1.setId(1001);
		s1.setName("Xiao Ming");
		s1.setAge(18);

		Students s2 = new Students();
		s2.setId(1002);
		s2.setName("Xiao Hong");
		s2.setAge(19);

		session.persist(s1);
		session.persist(s2);

		t.commit();
		session.close();
		System.out.println("successfully saved");
	}
}
