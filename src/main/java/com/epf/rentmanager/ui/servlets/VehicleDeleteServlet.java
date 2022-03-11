package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/vehicles/delete")

public class VehicleDeleteServlet extends HttpServlet {

	@Autowired
	VehicleService vehicleService;

	@Autowired
	ReservationService reservationService;

	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	// public VehicleService vehicleService= VehicleService.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int id = Integer.parseInt(request.getParameter("id"));
			// System.out.println(id);

			try {
				for (Reservation reservation : this.reservationService.findAll()) {
					if (id == reservation.getIdVehicule()) {
						this.reservationService.delete(reservation.getId());
					}
				}
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.vehicleService.delete(id);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/vehicles");
		// RequestDispatcher r =
		// request.getRequestDispatcher("WEB-INF/views/users/list.jsp");

		// r.forward(request, response);

	}

}
