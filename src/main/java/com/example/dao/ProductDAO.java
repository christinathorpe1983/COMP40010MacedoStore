package com.example.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.models.Location;
import com.example.models.Product;
import com.example.util.HibernateUtil;

public class ProductDAO {
	
	public boolean saveProduct(Product product) {
		
		Session session = null;
		boolean hasErrors = false;
		
		if (product != null) {
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
		        session.beginTransaction();
		        session.saveOrUpdate(product);
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
	
	public List<Product> getAllProducts() {
		
		Session session = null;
		List<Product> productList = null;
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
		        session.beginTransaction();
		        Query q = session.createQuery("From Product ");
		        productList = q.list();
			} catch (Exception ex) {
				if (session != null) {
					session.getTransaction().rollback();
				}
				
			} finally {
				if (session != null) {
					session.close();
				}
			}	
		
		return productList;
	}
	
	public Product getProductById(Long id) {
		
		Session session = null;
		Product product = null;
		//List<Product> productList = null;
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				product = (Product) session.get(Product.class, id);
				
		        //session.beginTransaction();
		        //Query q = session.createQuery("From Product where id= :id");
		        //q.setParameter("id", id);
		        //productList = q.list();
			} catch (Exception ex) {
				ex.printStackTrace();
				//if (session != null) {
				//	session.getTransaction().rollback();
				//}
				
			} finally {
				if (session != null) {
					session.close();
				}
			}	
			
			//if (productList == null || productList.isEmpty()) {
			//	return null;
			//}
			
		//return productList.get(0);
		return product;
	}

}
