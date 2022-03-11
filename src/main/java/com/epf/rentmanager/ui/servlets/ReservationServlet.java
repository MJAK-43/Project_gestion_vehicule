package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ResaVoitureService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents")
public class ReservationServlet extends HttpServlet {

	public static final long serialVersionUID = 1L;

	// private ReservationService reservationService=
	// ReservationService.getInstance();
	// private ResaVoitureService resaService= ResaVoitureService.getInstance();
	// private ReservationService resaServ= ReservationService.getInstance();

	@Autowired
	ReservationService reservationService;

	@Autowired
	ResaVoitureService resaService;

	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("reservations", this.resaService.findAll());

			RequestDispatcher r = request.getRequestDispatcher("WEB-INF/views/rents/list.jsp");

			r.forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int idClient = Integer.parseInt(request.getParameter("id"));

			this.reservationService.delete(idClient);

			// this.reservationService.delete(idVehicle);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/rents");

	}

}
