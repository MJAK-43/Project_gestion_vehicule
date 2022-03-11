package com.epf.rentmanager.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

public class VoitureServiceTest {
	@RunWith(MockitoJUnitRunner.class)
	public class VehServiceTest {
		@InjectMocks
		private VehicleService vehicleService;

		@Mock
		private VehicleDao vehicleDao;

		@Test
		public void findAll_should_fail_when_dao_throws_exception() throws DaoException { // When
			when(this.vehicleDao.findAll()).thenThrow(DaoException.class);

			// Then
			assertThrows(ServiceException.class, () -> vehicleDao.findAll());

		}
	}

}
