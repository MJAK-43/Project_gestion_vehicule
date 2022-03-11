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

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ResaVoitureService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet {

	public static final long serialVersionUID = 1L;

	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationService;

	@Autowired
	VehicleService vehicleService;

	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Vehicle> listVoits = new ArrayList<Vehicle>();
			LocalDate today = LocalDate.now();
			// request.setAttribute ("listvehicles",this.vehicleService.findAll());

			for (Vehicle vehicule : this.vehicleService.findAll()) {
				int i = 0;
				int j = 0;
				for (Reservation reser : this.reservationService.findAll()) {

					if (vehicule.getId() == reser.getIdVehicule()) {
						j++;
						if (i == 0) {
							if (today.compareTo(reser.getDateEnd()) > 0) {
								listVoits.add(vehicule);
								// System.out.println("DATE: " + today.compareTo(reser.getDateEnd()));
								i++;
							}
						}

					}
				}

				if (j == 0) {
					listVoits.add(vehicule);
				}
			}
			request.setAttribute("listvehicles", listVoits);
			request.setAttribute("listUsers", this.clientService.findAll());

			RequestDispatcher r = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");

			r.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();

		}
		// this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int idCar = Integer.parseInt(request.getParameter("car"));
			int idUser = Integer.parseInt(request.getParameter("user"));

			String datestart = (String) request.getParameter("begin");
			LocalDate start = LocalDate.parse(datestart);
			String dateend = (String) request.getParameter("end");
			LocalDate end = LocalDate.parse(dateend);

			this.reservationService.create(new Reservation(idUser, idCar, start, end));

			response.sendRedirect(request.getContextPath() + "/rents ");

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
