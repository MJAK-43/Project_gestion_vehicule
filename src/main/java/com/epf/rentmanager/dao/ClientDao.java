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
public class ClientDao {

	// private static ClientDao instance = null;

	private ClientDao() {
	}

	/*
	 * public static ClientDao getInstance() { if(instance == null) { instance = new
	 * ClientDao(); } return instance; }
	 */

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?,email=?, naissance=? WHERE id=?;";
	private static final String COUNT_CLIENTS = "SELECT COUNT(email) as total FROM Client;";

	public long create(Client client) throws DaoException {

		try {
			// on se connecte à la bd
			Connection conn = ConnectionManager.getConnection();
			// une requête sql
			PreparedStatement pstmt = conn.prepareStatement(CREATE_CLIENT_QUERY);

			pstmt.setString(1, client.getName());
			pstmt.setString(2, client.getLastname());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthDate()));

			// recupère les données
			// ResultSet rs =pstmt.executeQuery();

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

	public long delete(int id) throws DaoException {

		try {
			// on se connecte à la bd
			Connection conn = ConnectionManager.getConnection();
			// une requête sql
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY);
			//
			pstmt.setInt(1, id);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long update(Client client) throws DaoException {

		try {

			Connection com = ConnectionManager.getConnection();
			PreparedStatement pstmt = com.prepareStatement(UPDATE_CLIENT_QUERY);

			pstmt.setInt(5, client.getId());
			pstmt.setString(1, client.getName());
			pstmt.setString(2, client.getLastname());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthDate()));

			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public Optional<Client> findById(int id) throws DaoException {

		try {
			// on se connecte à la bd
			Connection conn = ConnectionManager.getConnection();
			// une requête sql
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);
			//
			pstmt.setInt(1, id);
			// recupère les données
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			// int clientId = rs.getInt("id");
			String clientLastname = rs.getString("nom");
			String clientFirstname = rs.getString("prenom");
			String clientEmail = rs.getString("email");
			LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();

			Client client = new Client(id, clientLastname, clientFirstname, clientEmail, clientBirthdate);

			return Optional.of(client);

			// System.out.println(rs);

		} catch (SQLException e) {
			// throw new DaoException(e.getMessage());
			e.printStackTrace();
		}

		return Optional.empty();
	}

	public List<Client> findAll() throws DaoException {
		try {
			List<Client> listeClient = new ArrayList<Client>();

			// on se connecte à la bd
			Connection conn = ConnectionManager.getConnection();
			// une requête sql
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENTS_QUERY);

			// pstmt.setInt(1, id);
			// recupère les données
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// récuperer le contenue du résultat pas à pas
				int clientId = rs.getInt("id");
				String clientLastname = rs.getString("nom");
				String clientFirstname = rs.getString("prenom");
				String clientEmail = rs.getString("email");
				LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();

				// ------------- option1 //------------ajoute un nouveau client dans la liste
				// listeClient.add(new Client(clientId, clientLastname, clientFirstname,
				// clientEmail, clientBirthdate));

				// ------------fait la même chose---option 2
				listeClient.add(this.findById(clientId).get());
			}

			return listeClient;

			// System.out.println(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int count() throws DaoException {

		try {

			Connection com = ConnectionManager.getConnection();

			PreparedStatement pstmt = com.prepareStatement(COUNT_CLIENTS);

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int a1 = rs.getInt("total");
			// on parcour le résultat du tableau

			/*
			 * while(rs.next()){
			 * 
			 * a++; }
			 */
			return a1;

		} catch (SQLException e) {
			e.addSuppressed(e);
		}
		return 0;
	}

}
