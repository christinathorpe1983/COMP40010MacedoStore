package com.example.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.models.Location;
import com.example.util.HibernateUtil;

public class LocationDAO {
	
	public Location getLocationById(Long id) {
		
		Session session = null;
		Location location = null;
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				location = (Location) session.get(Location.class, id);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}	
			
		return location;
	}
	
	public List<Location> getAllLocations() {
		
		Session session = null;
		List<Location> locationList = null;
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
		        session.beginTransaction();
		        Query q = session.createQuery("From Location ");
		        locationList = q.list();
			} catch (Exception ex) {
				if (session != null) {
					session.getTransaction().rollback();
				}
				
			} finally {
				if (session != null) {
					session.close();
				}
			}	
		
		return locationList;
	}
	
	public boolean saveLocation(Location location) {
		
		Session session = null;
		boolean hasErrors = false;
		
		if (location != null) {
			
			try {
				session = HibernateUtil.getSessionFactory().openSession();
		        session.beginTransaction();
		        session.saveOrUpdate(location);
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
