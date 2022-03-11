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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ResaVoitureService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents/editer")

public class ReservationUpdateServlet extends HttpServlet {

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
	 * private VehicleService vehicleService= VehicleService.getInstance();
	 * 
	 * 
	 * private ClientService clientService= ClientService.getInstance(); private
	 * ResaVoitureService resaService= ResaVoitureService.getInstance(); private
	 * ReservationService reservationS= ReservationService.getInstance();
	 */

//private ReservationService reservationService= ReservationService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("idReser", id);

			List<Reservation> rese = this.reservationS.findAll();

			request.setAttribute("listvehicles", this.vehicleService.findAll());

			request.setAttribute("listUsers", this.clientService.findAll());

	

			List<ResaVoiture> resavoitures = new ArrayList<ResaVoiture>();
			// List<Vehicle> listVeh = new ArrayList<Vehicle>();

			for (ResaVoiture reservation : this.resaService.findAll()) {

				if (id == reservation.getId()) {
					// on recupere la marque et le modèle (valeur) ayany par exemple pour id =?
					ResaVoiture resa = new ResaVoiture(reservation.getId(), reservation.getVoiture(),
							reservation.getClient(), reservation.getDateStart(), reservation.getDateEnd());
					resavoitures.add(resa);
					request.setAttribute("reservations", resavoitures);
					request.setAttribute("date_debut", reservation.getDateStart());
					request.setAttribute("date_fin", reservation.getDateEnd());

				}

				for (Reservation id_voiture_reserv : rese) {
					if (id == id_voiture_reserv.getId()) {
						request.setAttribute("id_vehicule", id_voiture_reserv.getIdVehicule());


					}
				}

				for (Reservation id_client_resev : rese) {
					if (id == id_client_resev.getId()) {
						request.setAttribute("id_client", id_client_resev.getIdClient());
					}
				}


				// on ajoute les information de l'objet resa à la liste resavoitures

			}


			 
			RequestDispatcher r = request.getRequestDispatcher("/WEB-INF/views/rents/editer.jsp");

			r.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	// private ReservationService reservationService=
	// ReservationService.getInstance();

	@Autowired
	ReservationService reservationService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// pour recuperer et lire les données saisies apr l'utilisateur
			int id = Integer.parseInt(request.getParameter("id"));
			int idClient = Integer.parseInt(request.getParameter("idClient"));
			int idVehicle = Integer.parseInt(request.getParameter("idVehicle"));
			String datestart = (String) request.getParameter("begin");
			LocalDate start = LocalDate.parse(datestart);
			String dateend = (String) request.getParameter("end");
			LocalDate end = LocalDate.parse(dateend);

			Reservation reservation = new Reservation();

			// pour modifier les données de l'utilisaturs
			reservation.setId(id);
			reservation.setIdVehicule(idVehicle);
			reservation.setIdClient(idClient);
			reservation.setDateStart(start);
			reservation.setDateEnd(end);

			this.reservationService.update(reservation);

			response.sendRedirect(request.getContextPath() + "/rents");

	

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
