package com.entsa.rentmanager;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import com.epf.rentmanager.model.Client;

public class UsersTest {
	   @Test
	  public void isLegal_should_return_true_when_age_is_over_18() {
	       // Given
	       Client legalUser = new Client(18,"John", "Doe", "john.doe@ensta.fr",LocalDate.of(1999, 06, 12));
	        
	       // Then
	       assertTrue(Client.isLegal(legalUser));
	   }

	   @Test
	 public  void isLegal_should_return_false_when_age_is_under_18() {
	        // Given   
	        Client illegaUser = new Client(11,"John", "Doe", "john.doe@ensta.fr", LocalDate.of(1999, 06, 12));
	        
	       // Then
	     assertFalse(Client.isLegal(illegaUser));  ;
	   }

}
