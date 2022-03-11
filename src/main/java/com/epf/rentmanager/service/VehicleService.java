package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;


@Service
public class VehicleService {

	private VehicleDao vehicleDao;
private VehicleService(VehicleDao vehicleDao) {
		
		this.vehicleDao = vehicleDao;
	}
	/*
	 * public static VehicleService instance;
	 * 
	 * private VehicleService() { this.vehicleDao = VehicleDao.getInstance(); }
	 * 
	 * public static VehicleService getInstance() { if (instance == null) { instance
	 * = new VehicleService(); }
	 * 
	 * return instance; }
	 */
	
public long create(Vehicle vehicle) throws ServiceException {
	
		
		try {
			
			return this.vehicleDao.create(vehicle);
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
		// TODO: créer un client   
		
	}
	

// Suppression d'un client 

public long delete( int id ) throws  ServiceException {
	
	try {
		 
		return this.vehicleDao.delete(id);
		
	}catch(DaoException e) {
		
		e.printStackTrace();
	
		
	}
	return 0;
	
}


public long update (Vehicle vehicle) throws ServiceException{
	
	try {
		 
		return this.vehicleDao.update(vehicle);
		
	}catch(DaoException e) {
		
		e.printStackTrace();
	}
	
		return 0;

	
}

public int count () throws ServiceException{
	
	try {
		 
		return this.vehicleDao.count();
		
	}catch(DaoException e) {
		
		e.printStackTrace();
	}
	
		return 0;

	
}

	
public Vehicle findById(long id) throws ServiceException {
	
	try {
		
		return this.vehicleDao.findById(id).get();
		
	} catch(DaoException e){
		
		e.printStackTrace();
		
	}
	return null;
	// TODO: récupérer un client par son id
	
}

public List<Vehicle> findAll() throws ServiceException {
	try {
	
	return this.vehicleDao.findAll();
	
	// TODO: récupérer tous les clients
	
} catch (DaoException e){
	

	e.printStackTrace();
	
}
return null;

}
}
