package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/vehicles/search")

public class FindVehicleServlet extends HttpServlet {

	@Autowired
	VehicleService vehicleService;

	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	// private VehicleService vehicleService= VehicleService.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String search = (String) request.getParameter("search"); // on recupere le mot entré pour la recherche(nom
																		// ou prenom)

			List<Vehicle> listVehicle1 = this.vehicleService.findAll(); // on recupere la liste de tous les utilisateurs
																		// en bd
			List<Vehicle> listVehicle = new ArrayList<Vehicle>(); // on crée la liste des utilisateurs qui sera envoyée
																	// comme résultat de la recherche

			/**
			 * on parcour la liste des utilsateurs et si la valeur du mot entré correspond
			 * en minuscule à tout ou partie du nom ou prenom alors on l'ajoute dans la
			 * liste de clients qui se renvoyée comme résultat de recherche
			 */

			for (Vehicle vehicle : listVehicle1) {

				if ((vehicle.getConstructor().toLowerCase().contains(search))
						|| (vehicle.getModel().toLowerCase().contains(search))) {
					listVehicle.add(vehicle);
				}
			}

			request.setAttribute("listvehicles", listVehicle); 
			RequestDispatcher r = request.getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp"); 
			r.forward(request, response);

		} catch (ServiceException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}

	}

}
