package com.epf.rentmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;

@Service
public class ReservationService {

	private ReservationDao reservationDao;

	private ReservationService(ReservationDao reservationDao) {

		this.reservationDao = reservationDao;
	}

	/*
	 * public static ReservationService instance;
	 * 
	 * private ReservationService() {
	 * 
	 * this.reservationDao = ReservationDao.getInstance(); }
	 * 
	 * public static ReservationService getInstance() { if (instance == null) {
	 * instance = new ReservationService(); }
	 * 
	 * return instance; }
	 */

	public long create(Reservation reservation) throws ServiceException {

		try {

			return this.reservationDao.create(reservation);

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
		// TODO: créer un client

	}

	// Suppression d'un client

	public long delete(int id) throws ServiceException {

		try {

			return this.reservationDao.delete(id);

		} catch (DaoException e) {

			e.printStackTrace();

		}
		return 0;

	}

	public long update(Reservation reservation) throws ServiceException {

		try {

			return this.reservationDao.update(reservation);

		} catch (DaoException e) {

			e.printStackTrace();
		}

		return 0;

	}

	public int count() throws ServiceException {

		try {

			return this.reservationDao.count();

		} catch (DaoException e) {

			e.printStackTrace();
		}

		return 0;

	}

	public Reservation findResaByClientId(long idClient) throws ServiceException {

		try {

			return this.reservationDao.findResaByClientId(idClient).get();

		} catch (DaoException e) {

			e.printStackTrace();

		}
		return null;
		// TODO: récupérer un client par son id

	}

	public Reservation findResaByVehicleId(long idVehicle) throws ServiceException {

		try {

			return this.reservationDao.findResaByVehicleId(idVehicle).get();

		} catch (DaoException e) {

			e.printStackTrace();

		}
		return null;
		// TODO: récupérer un client par son id

	}

	public List<Reservation> findAll() throws DaoException {
		try {

			return this.reservationDao.findAll();

			// TODO: récupérer tous les clients

		} catch (DaoException e) {

			e.printStackTrace();

		}
		return null;

	}

}
