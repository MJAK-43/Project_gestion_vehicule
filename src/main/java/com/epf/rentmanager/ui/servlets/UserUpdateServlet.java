package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.sql.Date;
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
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;

//la servlet permet de lire les donnéers de la requete

@WebServlet("/users/editer")

public class UserUpdateServlet extends HttpServlet {
	
	@Autowired
	ClientService clientService;

	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request: pour lire les données saisies par l'utilisateur
		// response: permeet d'envoyer une reponse provenant du serveur
		// post:permet d'nvoyer les informations provenant du serveur
		// get : search,filtering,sorting

		int id = Integer.parseInt(request.getParameter("id")); 
		
		

		request.setAttribute("idUser", id); 
		
		try {
			for (Client clients : this.clientService.findAll()) {

				if (id == clients.getId()) {					
					request.setAttribute("name", clients.getName());
					request.setAttribute("lastname", clients.getLastname());
					request.setAttribute("email", clients.getEmail());
					request.setAttribute("birthday", clients.getBirthDate());
				}
			}
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		RequestDispatcher r = request.getRequestDispatcher("/WEB-INF/views/users/editer.jsp");

		r.forward(request, response);
	}


	// private ClientService clientService= ClientService.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// pour recuperer et lire les données saisies apr l'utilisateur

			int id = Integer.parseInt(request.getParameter("id"));
			String lastname = (String) request.getParameter("last_name");
			String first_name = (String) request.getParameter("first_name");
			String email = (String) request.getParameter("email");
			String birthdate = (String) request.getParameter("datenaissance");
			LocalDate birthday = LocalDate.parse(birthdate);

			Client client = new Client();

			// pour modifier les données de l'utilisaturs
			client.setId(id);
			client.setName(first_name);
			client.setLastname(lastname);
			client.setEmail(email);
			client.setBirthDate(birthday);

			this.clientService.update(client);

			response.sendRedirect(request.getContextPath() + "/users");

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
