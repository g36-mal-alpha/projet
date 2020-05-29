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
import projet.data.Poste;

public class DaoBenevole {


	// Champs

	@Inject
	private DataSource		dataSource;

	@Inject
	private DaoCategorie	daoCategorie;
	
	// Actions 

	public int inserer( Benevole benevole ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO benevole ( nom, prenom, date_naissance, permis_conduire, mineurs, idcategorie ) VALUES( ?, ?, ?, ?, ?, ? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, benevole.getNom() );
			stmt.setObject( 2, benevole.getPrenom() );
			stmt.setObject( 3, benevole.getDate_naissance() );
			stmt.setObject( 4, benevole.getPermis_conduire() );
			stmt.setObject( 5, benevole.getMineurs() );
			stmt.setObject( 6, benevole.getCategorie().getId() );
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
			sql = "UPDATE benevole SET nom = ?, prenom = ?, date_naissance = ?, permis_conduire = ?, mineurs = ?, idcategorie = ? WHERE idbenevole =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, benevole.getNom() );
			stmt.setObject( 2, benevole.getPrenom() );
			stmt.setObject( 3, benevole.getDate_naissance() );
			stmt.setObject( 4, benevole.getPermis_conduire() );
			stmt.setObject( 5, benevole.getMineurs() );
			stmt.setObject( 6, benevole.getCategorie().getId() );
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
			sql = "SELECT * FROM benevole WHERE idbenevole = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idBenevole);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireBenevole( rs, true);
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
			sql = "SELECT * FROM benevole ORDER BY nom , prenom";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Benevole> benevoles = new LinkedList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole( rs ,false ));
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	public List<Benevole> listerPoutPoste(int idPoste) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM avoir INNER JOIN benevole ON avoir.idbenevole = benevole.idbenevole WHERE avoir.idposte = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idPoste );
			rs = stmt.executeQuery();

			List<Benevole> benevoles = new LinkedList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole( rs, false ) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
    public int compterPourCategorie( int idCategorie ) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM benevole WHERE idcategorie = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idCategorie );
            rs = stmt.executeQuery();

            rs.next();
            return rs.getInt( 1 );

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
    }
	

	// Méthodes auxiliaires

	private Benevole construireBenevole( ResultSet rs, boolean flagComplet  ) throws SQLException {
		Benevole benevole = new Benevole();
		benevole.setId( rs.getObject( "idbenevole", Integer.class ) );
		benevole.setNom( rs.getObject( "nom", String.class ) );
		benevole.setPrenom( rs.getObject( "prenom", String.class ) );
		benevole.setDate_naissance( rs.getObject( "date_naissance", LocalDate.class ) );
		benevole.setPermis_conduire( rs.getObject( "permis_conduire", String.class ) );
		benevole.setMineurs( rs.getObject( "mineurs", Boolean.class ) );
		if ( flagComplet ) {
			benevole.setCategorie( daoCategorie.retrouver( rs.getObject("idcategorie", Integer.class) ) );
		}
		return benevole;
	}


	public Poste benevolesPourDialogAjout() {
		// TODO Auto-generated method stub
		return null;
	}
}
