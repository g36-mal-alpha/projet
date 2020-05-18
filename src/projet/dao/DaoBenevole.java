package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Benevole;

public class DaoBenevole {


	// Champs

	@Inject
	private DataSource		dataSource;


	// Actions 

	public int inserer( Benevole benevole ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO service ( nom, prenom, date_naissance, permis_conduire, mineurs, permanent ) VALUES( ?, ?, ?, ?, ?, ? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, benevole.getNom() );
			stmt.setObject( 2, benevole.getPrenom() );
			stmt.setObject( 3, benevole.getDate_naissance() );
			stmt.setObject( 4, benevole.getPermis_conduire() );
			stmt.setObject( 5, benevole.getMineurs() );
			stmt.setObject( 6, benevole.getPermanent() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			benevole.setId( rs.getObject( 1, Integer.class) );
			return benevole.getId();

		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Benevole benevole ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE service SET nom = ?, prenom = ?, date_naissance = ?, permis_conduire = ?, mineurs = ?, permanent = ? WHERE idservice =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, benevole.getNom() );
			stmt.setObject( 2, benevole.getPrenom() );
			stmt.setObject( 3, benevole.getDate_naissance() );
			stmt.setObject( 4, benevole.getPermis_conduire() );
			stmt.setObject( 5, benevole.getMineurs() );
			stmt.setObject( 6, benevole.getPermanent() );
			stmt.setObject( 7, benevole.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idBenevole ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM benevole WHERE idbenevole = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idBenevole );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Benevole retrouver( int idBenevole ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM beneovle WHERE idbenevole = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idBenevole);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireBenevole( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Benevole> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM benevole ORDER BY nom";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Benevole> benevoles = new LinkedList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole( rs ) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	// Méthodes auxiliaires

	private Benevole construireBenevole( ResultSet rs ) throws SQLException {
		Benevole benevole = new Benevole();
		benevole.setId( rs.getObject( "idservice", Integer.class ) );
		benevole.setNom( rs.getObject( "nom", String.class ) );
		benevole.setPrenom( rs.getObject( "prenom", String.class ) );
		benevole.setDate_naissance( rs.getObject( "date_naissance", LocalDate.class ) );
		benevole.setPermis_conduire( rs.getObject( "permis_conduire", String.class ) );
		benevole.setMineurs( rs.getObject( "mineurs", Boolean.class ) );
		benevole.setPermanent( rs.getObject( "permanent", Boolean.class ) );
		return benevole;
	}
	
}
