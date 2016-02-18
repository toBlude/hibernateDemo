package com.hsy.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.hsy.entity.User;

public class UserTest {

	public static void main(String[] args) {
		addUser();
	}
	
	public static void createTable(){
		Configuration cfg=new Configuration().configure();
		
		SchemaExport se=new SchemaExport(cfg);
		se.create(true,true);
	}
	
	public static void addUser(){
		Configuration cfg=new Configuration().configure();
		SessionFactory factory=cfg.buildSessionFactory();
		
		Session session=null;
		
		try {
			session=factory.openSession();
			
			session.beginTransaction();
			
			User user=new User();
			user.setId(5);
			user.setName("lilei");
			user.setPassword("passwordtest");
			user.setEmail("lilei@gmail.com");
			
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session!=null)
			{
				if(session.isOpen())
				{
					session.close();
				}
			}
		}
	}
}
