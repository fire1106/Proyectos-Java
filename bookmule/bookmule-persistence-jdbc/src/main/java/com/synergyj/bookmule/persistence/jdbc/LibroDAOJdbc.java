package com.synergyj.bookmule.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.synergyj.bookmule.core.domain.Libro;
import com.synergyj.bookmule.persistence.dao.LibroDAO;

@Repository("libroDAO") //Estamos declarando un bean de Spring
public class LibroDAOJdbc extends GenericJdbcDAO implements LibroDAO {
	
	//Queries
	private static final String queryCreaLibro = "insert into libro (titulo,edicion,isbn,codigo_color,"
			+ "codigo_clasificacion,numero_ejemplares,precio_venta,editorial_id,status_libro_id)"
			+ " values(?,?,?,?,?,?,?,?,?)";

	//private static final String queryBuscaLibrosPrefix = "select libro_id,titulo,edicion,isbn,"
	//		+ "codigo_color,codigo_clasificacion,numero_ejemplares,precio_venta,editorial_id,"
	//		+ "status_libro_id from libro  where ";

	@Override
	public void crea(final Libro libro) {
		
		//Me va a almacenar la llave primaria que se genera en la incercion
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		
		//Generamos un insert enviandole un preparedStament declarando la interface y el keyholder
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override //Sobre escribimos el metodo
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				//Me regresara la llave generada
				PreparedStatement ps = con.prepareStatement(queryCreaLibro,Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				//Le mandamos los parametros
				ps.setString(index++, libro.getTitulo());
				ps.setInt(index++, libro.getEdicion());
				ps.setString(index++, libro.getIsbn());
				ps.setString(index++, libro.getCodigoColor());
				ps.setString(index++, libro.getCodigoClasificacion());
				ps.setInt(index++, libro.getNumeroEjemplares());
				ps.setFloat(index++, libro.getPrecioVenta());
				ps.setLong(index++, libro.getEditorial().getId());
				ps.setInt(index++, libro.getStatusLibro().getId());
				return ps;
			}
		},keyHolder);
		
		//inserta la llave que se guardara en el keyHolder
		libro.setId(keyHolder.getKey().longValue());
		
}

	@Override
	public Set<Libro> busca(Libro libro) {
		// TODO Auto-generated method stub
		return null;
	}

}
