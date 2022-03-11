package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import com.epf.rentmanager.model.ResaVoiture;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/vehicles/editer")
public class VehicleUpdateservlet extends HttpServlet {

	public static final long serialVersionUID = 1L;
	@Autowired
	VehicleService vehicleService;

	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		List<Vehicle> vehcule = new ArrayList<Vehicle>();

		try {
			for (Vehicle vehicule : this.vehicleService.findAll()) {

				if (id == vehicule.getId()) {

					Vehicle vech = new Vehicle(vehicule.getId(), vehicule.getConstructor(), vehicule.getModel(),
							vehicule.getNumPlace());
					vehcule.add(vech);
					request.setAttribute("idvehicle", vehicule.getId());
					request.setAttribute("constructeur", vehicule.getConstructor());
					request.setAttribute("modele", vehicule.getModel());
					// String numplace = String.valueOf(vehicule.getNumPlace());
					request.setAttribute("numPlace", vehicule.getNumPlace());

				}
			}
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		RequestDispatcher r = request.getRequestDispatcher("/WEB-INF/views/vehicles/editer.jsp");
		r.forward(request, response);

	}

	// private VehicleService vehicleService= VehicleService.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// pour recuperer et lire les données saisies apr l'utilisateur
			int id = Integer.parseInt(request.getParameter("id"));
			String constructor = (String) request.getParameter("manufacturer");
			String Model = (String) request.getParameter("modele");
			int Numplace = Integer.parseInt(request.getParameter("seats"));

			Vehicle vehicle = new Vehicle();

			// pour modifier les données de l'utilisaturs

			vehicle.setId(id);
			vehicle.setConstructor(constructor);
			vehicle.setModel(Model);
			vehicle.setNumPlace(Numplace);

			this.vehicleService.update(vehicle);

			response.sendRedirect(request.getContextPath() + "/vehicles");

			/*
			 * RequestDispatcher r =
			 * request.getRequestDispatcher("/WEB-INF/views/users/list.jsp");
			 * 
			 * r.forward(request, response);
			 */

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
