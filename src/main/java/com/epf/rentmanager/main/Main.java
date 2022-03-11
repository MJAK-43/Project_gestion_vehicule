package com.epf.rentmanager.main;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = new
				AnnotationConfigApplicationContext(AppConfiguration.class);
				ClientService clientService = context.getBean(ClientService.class);
				//VehicleService vehicleService = context.getBean(VehicleService.class);
			

		
/*
		try {
			// Client clients = new Client();
			Scanner scanner = new Scanner(System.in);

			System.out.print("Ecrire un Nom :  ");
			// clients.setName(scanner.nextLine());
			String nom = scanner.nextLine();

			System.out.print("Ecrire un Prenom :  ");
			// clients.setLastname(scanner.nextLine());
			String prenom = scanner.nextLine();

			System.out.print("Ecrire un Email :  ");
			// clients.setEmail(scanner.nextLine());
			String email = scanner.nextLine();
			Client clients = new Client(5, nom, prenom, email, LocalDate.parse("2000-09-26"));
			
			ClientService.getInstance().create(clients);
			System.out.println(ClientService.getInstance().findAll());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		/*
		 * try { Client clients = new Client(); Scanner scanner = new
		 * Scanner(System.in);
		 * 
		 * // Affiche "Ecrire un nombre: "
		 * 
		 * 
		 * //System.out.println(ClientService.getInstance().findById(1));
		 * System.out.println(ClientService.getInstance().findAll());
		 * 
		 * }catch(ServiceException e) { e.printStackTrace(); }
		 */

	}
}