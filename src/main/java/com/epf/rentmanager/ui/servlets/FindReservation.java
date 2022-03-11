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
import com.epf.rentmanager.model.ResaVoiture;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ResaVoitureService;

@WebServlet("/rents/search")

public class FindReservation extends HttpServlet {

	@Autowired
	ResaVoitureService resaService;

	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	// private ResaVoitureService resaService= ResaVoitureService.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String search = (String) request.getParameter("search");

			List<ResaVoiture> listResa = new ArrayList<ResaVoiture>();

			/**
			 * on parcour la liste des utilsateurs et si la valeur du mot entré correspond
			 * en minuscule à tout ou partie du nom ou prenom alors on l'ajoute dans la
			 * liste de clients qui se renvoyée comme résultat de recherche
			 */

			for (ResaVoiture reserver : this.resaService.findAll()) {

				if ((reserver.getVoiture().toLowerCase().contains(search))
						|| (reserver.getClient().toLowerCase().contains(search))) {
					listResa.add(reserver);

				}
			}

			request.setAttribute("reservations", listResa); 
			RequestDispatcher r = request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp");
			r.forward(request, response); 

		} catch (ServiceException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}

	}

}
