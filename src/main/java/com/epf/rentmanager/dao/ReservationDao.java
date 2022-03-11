package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {
	
	private ReservationDao() {}

	/*
	 * private static ReservationDao instance = null; private String vehicleID;
	 * public static ReservationDao getInstance() { if(instance == null) { instance
	 * = new ReservationDao(); } return instance; }
	 */
	
	private static final String CREATE_RESERVATION_QUERY = " INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?); ";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = " SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String UPDATE_RESERVATION_QUERY="UPDATE reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";
    private static final String COUNT_RESERVATIONS = "SELECT COUNT(id) as total FROM reservation;";
	
	public long create(Reservation reservation) throws DaoException {
		
		try {
			Connection com= ConnectionManager.getConnection();
			PreparedStatement pstmt=com.prepareStatement(CREATE_RESERVATION_QUERY);
		
			pstmt.setInt(1, reservation.getIdClient());
			pstmt.setInt(2, reservation.getIdVehicule());
			pstmt.setDate(3, Date.valueOf(reservation.getDateStart())); 
			pstmt.setDate(4, Date.valueOf(reservation.getDateEnd())); 
		
			return pstmt.executeUpdate();
		    
		}
	
		catch (SQLException e){
			e.printStackTrace();
		}
		return 0;
		
		
	}
	
	public long delete(int id) throws DaoException {
			
			
			try {
				
				Connection com =ConnectionManager.getConnection();
				PreparedStatement pstmt= com.prepareStatement(DELETE_RESERVATION_QUERY);
						
						pstmt.setInt(1,id);
						
				
				return pstmt.executeUpdate();
				
			} catch(SQLException e){
				e.printStackTrace();
				
				
			}
			return 0;
			
		}

	 public long update(Reservation reservation) throws DaoException {   //creation d'un client
			
			try {
				Connection com= ConnectionManager.getConnection();
				PreparedStatement pstmt=com.prepareStatement(UPDATE_RESERVATION_QUERY);
				
				pstmt.setInt(5,reservation.getId());
			    pstmt.setInt(1, reservation.getIdClient());
			    pstmt.setInt(2, reservation.getIdVehicule());
				pstmt.setDate(3, Date.valueOf(reservation.getDateStart())); 
				pstmt.setDate(4, Date.valueOf(reservation.getDateEnd())); 
			
				
				
				return pstmt.executeUpdate();
			    
			}
		
			catch (SQLException e){
				e.printStackTrace();
			}
			return 0;
			
		}

	 public int count() throws DaoException{
		try { 
		 Connection com=  ConnectionManager.getConnection();
		 PreparedStatement pstmt=com.prepareStatement(COUNT_RESERVATIONS);
		 
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			int a= rs.getInt("total");
	          // on parcour le résultat du tableau
			
				/*
				 * while(rs.next()){
				 * 
				 * a++; }
				 */
			return a;
		}  catch (SQLException e) {
			e.addSuppressed(e);
		}
			return 0;
	 } 
	 
	 
	 
	public Optional<Reservation> findResaByClientId(long idClient) throws DaoException {
		
		
		
		try {
			Connection com= ConnectionManager.getConnection();
			PreparedStatement pstmt=com.prepareStatement( FIND_RESERVATIONS_BY_CLIENT_QUERY );
			
			pstmt.setInt(2, (int) idClient);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
		
		    int clientID=rs.getInt("client_id");
			int vehicleID=rs.getInt("vehicle_id");
			LocalDate datestart=rs.getDate("date_start").toLocalDate();
			LocalDate dateend=rs.getDate("date_end").toLocalDate();
		    
		    
			Reservation reservation = new Reservation (clientID,vehicleID,datestart,dateend);
			return Optional.of(reservation);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	
		
		
		
		return null;
	}
		

	
	public Optional<Reservation> findResaByVehicleId(long idVehicle) throws DaoException {

		try {
			Connection com= ConnectionManager.getConnection();
			PreparedStatement pstmt=com.prepareStatement( FIND_RESERVATIONS_BY_CLIENT_QUERY );
			
			pstmt.setInt(3, (int) idVehicle);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
		
		    int clientID=rs.getInt("client_id");
			int vehicleID=rs.getInt("vehicle_id");
			LocalDate datestart=rs.getDate("debut").toLocalDate();
			LocalDate dateend=rs.getDate("fin").toLocalDate();
		    
		    
			Reservation reservation = new Reservation (clientID,vehicleID,datestart,dateend);
			return Optional.of(reservation);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	public List <Reservation> findAll() throws DaoException {

		try {
			
		
			
			
			List<Reservation> listReservation = new ArrayList<Reservation>();
		
			Connection com=  ConnectionManager.getConnection();
			
			PreparedStatement pstmt=com.prepareStatement(FIND_RESERVATIONS_QUERY);
			
			
			ResultSet rs = pstmt.executeQuery();
			
	
			while(rs.next()){
				
				
				/*String clientLastName=rs.getString("nom");
				String clientFirstname=rs.getString("prenom");
				String vehicleConstructor=rs.getString("constructeur");
				String vehicleModel=rs.getString("modele");*/
				int id=rs.getInt("id");
			    int clientID =rs.getInt("client_id");
				int vehicleID =rs.getInt("vehicle_id");
				//String resDebut = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rs.getTimestamp("debut"));
				//String resFin= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rs.getTimestamp("fin"));
			    LocalDate datestart=rs.getDate("debut").toLocalDate();
				LocalDate dateend=rs.getDate("fin").toLocalDate();
				    
				
				Reservation reservation = new Reservation (id,clientID,vehicleID,datestart,dateend);
			

		
				listReservation.add(reservation);
			}
		
			
			return listReservation;
			
	
	}  catch (SQLException e) {
		e.addSuppressed(e);
	}
		return null;
   
}}
