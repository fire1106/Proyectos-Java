package com.synergyj.bookmule.persistence.dao;

import java.util.Set;

import com.synergyj.bookmule.core.domain.Libro; //Podemos utilizar la clase Libro del core-domain por que lo declaramos en la dependencia

public interface LibroDAO {
	
	/**
	 * Metodo que persistira un libro
	 * @param libro
	 */
	void crea(Libro libro); 
	
	/**
	 * Busca un conjunto de libros de acuerdo al criterio de un libro
	 * @param libro
	 * @return
	 */
	Set<Libro> busca(Libro libro);

}
