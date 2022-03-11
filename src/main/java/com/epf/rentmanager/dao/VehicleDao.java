package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class VehicleDao {

	private VehicleDao() {
	}

	/*
	 * private static VehicleDao instance = null; public static VehicleDao
	 * getInstance() { if(instance == null) { instance = new VehicleDao(); } return
	 * instance; }
	 */
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, model, nb_places) VALUES(?, ? , ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, model, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, model, nb_places FROM Vehicle;";
	private static final String UPDATE_VEHICLES_QUERY = "UPDATE Vehicle SET constructeur=?,model=?,nb_places=? WHERE id=?;";
	private static final String COUNT_VEHICLES = "SELECT COUNT(DISTINCT model) as total FROM Vehicle;";

	public long create(Vehicle vehicle) throws DaoException { // creation d'un client

		try {
			Connection com = ConnectionManager.getConnection();
			PreparedStatement pstmt = com.prepareStatement(CREATE_VEHICLE_QUERY);

			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setString(2, vehicle.getModel());
			pstmt.setInt(3, vehicle.getNumPlace());

			return pstmt.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public long delete(int id) throws DaoException {

		try {

			Connection com = ConnectionManager.getConnection();
			PreparedStatement pstmt = com.prepareStatement(DELETE_VEHICLE_QUERY);

			pstmt.setInt(1, id);

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return 0;

	}

	public long update(Vehicle vehicle) throws DaoException { // creation d'un client

		try {
			Connection com = ConnectionManager.getConnection();
			PreparedStatement pstmt = com.prepareStatement(UPDATE_VEHICLES_QUERY);
			
			pstmt.setInt(4, vehicle.getId());
			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setString(2, vehicle.getModel());
			pstmt.setInt(3, vehicle.getNumPlace());

			return pstmt.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public int count() throws DaoException {

		try {

			Connection com = ConnectionManager.getConnection();

			PreparedStatement pstmt = com.prepareStatement(COUNT_VEHICLES);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			int a = rs.getInt("total");
			// on parcour le résultat du tableau

			/*
			 * while(rs.next()){
			 * 
			 * a++; }
			 */
			return a;

		} catch (SQLException e) {
			e.addSuppressed(e);
		}
		return 0;
	}

	public Optional<Vehicle> findById(long id) throws DaoException {

		try {
			Connection com = ConnectionManager.getConnection();
			PreparedStatement pstmt = com.prepareStatement(FIND_VEHICLE_QUERY);

			pstmt.setInt(1, (int) id);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			int vehicleId = rs.getInt("id");
			String vehicleConstructor = rs.getString("constructeur");
			String vehicleModel = rs.getString("model");
			int VehicleNumPlace = rs.getInt("nb_places");

			Vehicle vehicle = new Vehicle(vehicleId, vehicleConstructor, vehicleModel, VehicleNumPlace);
			return Optional.of(vehicle);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Vehicle> findAll() throws DaoException {

		try {

			List<Vehicle> listVehicle = new ArrayList<Vehicle>();

			Connection com = ConnectionManager.getConnection();

			PreparedStatement pstmt = com.prepareStatement(FIND_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int vehicleId = rs.getInt("id");
				String vehicleConstructor = rs.getString("constructeur");
				String vehicleModel = rs.getString("model");
				int VehicleNumPlace = rs.getInt("nb_places");

				Vehicle vehicle = new Vehicle(vehicleId, vehicleConstructor, vehicleModel, VehicleNumPlace);

				listVehicle.add(vehicle);
			}

			return listVehicle;

		} catch (SQLException e) {
			e.addSuppressed(e);
		}
		return null;

	}

}
