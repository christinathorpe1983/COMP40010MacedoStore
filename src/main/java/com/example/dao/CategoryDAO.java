package com.example.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.models.Category;
import com.example.models.Location;
import com.example.util.HibernateUtil;

public class CategoryDAO {
	
	public Category getCategoryById(Long id) {
		
		Session session = null;
		Category category = null;
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				category = (Category) session.get(Category.class, id);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}	
			
		return category;
	}
	
	public List<Category> getAllCategories() {
		
		Session session = null;
		List<Category> categoryList = null;
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
		        session.beginTransaction();
		        Query q = session.createQuery("From Category ");
		        categoryList = q.list();
			} catch (Exception ex) {
				if (session != null) {
					session.getTransaction().rollback();
				}
				
			} finally {
				if (session != null) {
					session.close();
				}
			}	
		
		return categoryList;
	}
	
	public boolean saveCategory(Category category) {
		
		Session session = null;
		boolean hasErrors = false;
		
		if (category != null) {
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
		        session.beginTransaction();
		        session.saveOrUpdate(category);
		        session.getTransaction().commit();
			} catch (Exception ex) {
				if (session != null) {
					session.getTransaction().rollback();
				}
				hasErrors = true;
				
			} finally {
				if (session != null) {
					session.close();
				}
			}	
		}
		
		return hasErrors;
	}

}
