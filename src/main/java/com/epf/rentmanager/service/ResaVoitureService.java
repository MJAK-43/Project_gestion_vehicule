package com.epf.rentmanager.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.ResaVoiture;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

@Service
public class ResaVoitureService {

	private ClientDao clientDao;
	private ReservationDao reservationDao;
	private VehicleDao vehicleDao;

	private ResaVoitureService(ClientDao clientDao, ReservationDao reservationDao, VehicleDao vehicleDao) {

		this.clientDao = clientDao;
		this.reservationDao = reservationDao;
		this.vehicleDao = vehicleDao;
	}

	/*
	 * public static ResaVoitureService instance;
	 * 
	 * public static ResaVoitureService getInstance() { if (instance == null) {
	 * instance = new ResaVoitureService(); }
	 * 
	 * return instance; }
	 */

	public List<ResaVoiture> findAll() throws ServiceException {

		try {

			List<Reservation> reservations = this.reservationDao.findAll(); 
			List<Client> clients = this.clientDao.findAll();
			List<Vehicle> voitures = this.vehicleDao.findAll();
			List<ResaVoiture> resavoitures = new ArrayList<ResaVoiture>(); 

			HashMap<Integer, String> customer = new HashMap(); 
			HashMap<Integer, String> vehicle = new HashMap();

			for (Client client : clients) {
				customer.put(client.getId(), client.getLastname() + " " + client.getName());
			}

			for (Vehicle voiture : voitures) {
				vehicle.put(voiture.getId(), voiture.getConstructor() + " " + voiture.getModel());
			}

			for (Reservation reservation : reservations) {
				String clientName = null;
				String vehicleName = null;

				for (Vehicle voiture : voitures) {
					if (voiture.getId() == reservation.getIdVehicule()) {
						vehicleName = vehicle.get(voiture.getId()); 
					}

				}

				for (Client client : clients) {
					if (client.getId() == reservation.getIdClient()) { 
						clientName = customer.get(client.getId());
					}
				}
				ResaVoiture resa = new ResaVoiture(reservation.getId(), vehicleName, clientName,
						reservation.getDateStart(), reservation.getDateEnd()); 
				resavoitures.add(resa);
			}

			return resavoitures;
			// TODO: récupérer tous les clients

		} catch (DaoException e) {

			e.printStackTrace();

		}
		return null;

	}

}
