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
import projet.data.Hierarchie;


public class DaoHierarchie {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( Hierarchie hierarchie ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO hierarchie ( libelle ) VALUES( ? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, hierarchie.getLibelle() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			hierarchie.setId( rs.getObject( 1, Integer.class) );
			return hierarchie.getId();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Hierarchie hierarchie ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE hierarchie SET libelle = ? WHERE idhierarchie =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, hierarchie.getLibelle() );
			stmt.setObject( 2, hierarchie.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idhierarchie ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM hierarchie WHERE idhierarchie = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, idhierarchie );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Hierarchie retrouver( int idhierarchie ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM hierarchie WHERE idhierarchie = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(1, idhierarchie);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireHierarchie( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Hierarchie> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM hierarchie ORDER BY libelle";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Hierarchie> hierarchies = new LinkedList<>();
			while (rs.next()) {
				hierarchies.add( construireHierarchie( rs ) );
			}
			return hierarchies;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Hierarchie construireHierarchie( ResultSet rs ) throws SQLException {
		Hierarchie hierarchie = new Hierarchie();
		hierarchie.setId( rs.getObject( "idHierarchie", Integer.class ) );
		hierarchie.setLibelle( rs.getObject( "libelle", String.class ) );
		return hierarchie;
	}

}
