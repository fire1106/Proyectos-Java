package com.synergyj.bookmule.persistence.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //Le indicamos a JUnit que el manejador sera spring
@ContextConfiguration("/jdbcTestApplicationContext.xml") //Declaramos el applicationContext que utilizaremos en las pruebas
public class DataSourceTestCase {
	
	//Configuramos el logger
	Logger logger = LoggerFactory.getLogger(DataSourceTestCase.class);	
	
	@Autowired
	DataSource dataSource; //Le indicamos a espring que busque un bean llamado dataSource y lo incerte a esta variable
	
	@Value("#{propiedades['bd.username']}") //Le indicamos por medio de spell que del bean propiedades inserte el valor bd.username
	String usuarioBD;
	
	@Value("#{propiedades['bd.password']}") //Le indicamos por medio de spell que del bean propiedades inserte el valor bd.password
	String passwordBD;
	 
	
	@Test
	public void pruebaDataSource() {
		
		//Probamos que se inserte correctamente el bean y no nos regrese null
		assertNotNull(dataSource);
		
		//Probamos que se inserten los valores
		assertNotNull(usuarioBD);
		logger.debug("El usuario de la BD de datos es: {}",usuarioBD);
		assertNotNull(passwordBD);
		logger.debug("El password de la BD de datos es: {}",passwordBD);
		
		try {
			//Probamos que exista la conexion
			Connection connection = dataSource.getConnection();
			assertNotNull(connection);
		} catch (SQLException e) {
			logger.error("Hubo un problema en la conexion",e);
			fail("Existio una excepcion.");
		}
		
	}

}
