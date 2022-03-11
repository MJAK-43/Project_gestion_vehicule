package com.entsa.rentmanager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;

public class VehicleTest {
	  @Test
	  public void isCorrect_should_return_true_when_nb_place_is_over_2() {
	       // Given
	       Vehicle CorrectPlace = new Vehicle(1,"Nissan", "Qasqhai", 3);
	        
	       // Then
	       assertTrue(Vehicle.isCorrect(CorrectPlace));
	   }

	   @Test
	 public  void isCorrect_should_return_false_when_nb_place_is_under_2() {
	        // Given   
	        Vehicle inCorrectPlace = new Vehicle(5,"Peugeot", "Doe", 1);
	        
	       // Then
	     assertFalse(Vehicle.isCorrect(inCorrectPlace));  ;
	   }
}
