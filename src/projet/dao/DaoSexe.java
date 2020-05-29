package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Sexe;


public class DaoSexe {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( Sexe sexe ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO sexe ( libelle ) VALUES( ? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, sexe.getLibelle() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			sexe.setId( rs.getObject( 1, Integer.class) );
			return sexe.getId();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Sexe sexe ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE sexe SET libelle = ? WHERE idsexe =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, sexe.getLibelle() );
			stmt.setObject( 2, sexe.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idsexe ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM sexe WHERE idsexe = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, idsexe );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Sexe retrouver( int idsexe ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM sexe WHERE idsexe = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(1, idsexe);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireSexe( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Sexe> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM sexe ORDER BY libelle";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Sexe> sexes = new LinkedList<>();
			while (rs.next()) {
				sexes.add( construireSexe( rs ) );
			}
			return sexes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Sexe construireSexe( ResultSet rs ) throws SQLException {
		Sexe sexe = new Sexe();
		sexe.setId( rs.getObject( "idsexe", Integer.class ) );
		sexe.setLibelle( rs.getObject( "libelle", String.class ) );
		return sexe;
	}

}
