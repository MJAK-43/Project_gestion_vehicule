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

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.ResaVoiture;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ResaVoitureService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/users/details")
public class DetailsUserServlet extends HttpServlet {

	public static final long serialVersionUID = 1L;
	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationS;
	@Autowired
	ResaVoitureService resaService;
	@Autowired
	VehicleService vehicleService;

	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/*
	 * private ClientService clientService= ClientService.getInstance(); private
	 * ResaVoitureService resaService= ResaVoitureService.getInstance(); private
	 * ReservationService reservationS= ReservationService.getInstance(); private
	 * VehicleService vehicleService= VehicleService.getInstance();
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		request.setAttribute("idUser", id);

		try {
			List<Reservation> rese = new ArrayList<Reservation>();
			List<ResaVoiture> resavoitures = new ArrayList<ResaVoiture>();
			List<Reservation> reserva = new ArrayList<Reservation>();
			List<Vehicle> listV = new ArrayList<Vehicle>();

			try {
				rese = this.reservationS.findAll();
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// request.setAttribute ("reservations",this.resaService.findAll());
			// request.setAttribute ("listUsers",this.clientService.findAll());

			for (Client client : this.clientService.findAll()) {

				if (id == client.getId()) {
					request.setAttribute("name", client.getName());
					request.setAttribute("lastname", client.getLastname());
					request.setAttribute("email", client.getEmail());
				}

			}

			int count = 0;

			for (Reservation id_reserv_client : rese) {

				if (id == id_reserv_client.getIdClient()) {
					// request.setAttribute ("id_vehicule", id_reserv_client.getId());

					for (ResaVoiture reservation : this.resaService.findAll()) {
						if (id_reserv_client.getId() == reservation.getId()) {
							ResaVoiture resa = new ResaVoiture(reservation.getId(), reservation.getVoiture(),
									reservation.getClient(), reservation.getDateStart(), reservation.getDateEnd()); 
							resavoitures.add(resa);
							count++;
						}
					}

				}
			}
			request.setAttribute("reservations", resavoitures);
			request.setAttribute("compter", count);

			for (Reservation id_reserv_c : rese) {
				if (id == id_reserv_c.getIdClient()) {
					Reservation resat = new Reservation(id_reserv_c.getId(), id_reserv_c.getIdClient(),
							id_reserv_c.getIdVehicule(), id_reserv_c.getDateStart(), id_reserv_c.getDateEnd());
					reserva.add(resat);
				}

			}

			int cnt = 0;
			for (Vehicle vehicule : this.vehicleService.findAll()) {
				int i = 0;
				for (Reservation id_reserv_v : reserva) {

					if (vehicule.getId() == id_reserv_v.getIdVehicule() & i < 1) {
						Vehicle vehicle = new Vehicle(vehicule.getId(), vehicule.getConstructor(), vehicule.getModel(),
								vehicule.getNumPlace());
						listV.add(vehicle);
						i++;
						cnt++;
					}
				}
				request.setAttribute("listve", listV);
				request.setAttribute("cnt", cnt);

			}

			request.setAttribute("listvehicles", resavoitures);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher r = request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");

		r.forward(request, response);
	}
}
