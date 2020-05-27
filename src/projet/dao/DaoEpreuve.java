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
import projet.data.Epreuve;

public class DaoEpreuve {


	// Champs

	@Inject
	private DataSource		dataSource;
	
	// Actions 

	public int inserer( Epreuve Epreuve ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO epreuve ( nom_Epreuve, date_epreuve, lieu, tarif ) VALUES( ?, ?, ?, ?) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, Epreuve.getNom_epreuve() );
			stmt.setObject( 2, Epreuve.getDate_epreuve() );
			stmt.setObject( 3, Epreuve.getLieu()  );
			stmt.setObject( 4, Epreuve.getTarif()  );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			Epreuve.setId( rs.getObject( 1, Integer.class) );
			return Epreuve.getId();

		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Epreuve Epreuve ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE Epreuve SET nom_Epreuve = ?, date_epreuve = ?,  lieu = ?, tarif = ? WHERE idEpreuve =  ?";
			stmt = cn.prepareStatement( sql );
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, Epreuve.getNom_epreuve() );
			stmt.setObject( 2, Epreuve.getDate_epreuve() );
			stmt.setObject( 3, Epreuve.getLieu()  );
			stmt.setObject( 4, Epreuve.getTarif()  );
			stmt.setObject( 5, Epreuve.getId()  );
			stmt.executeUpdate();
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idEpreuve ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM Epreuve WHERE idEpreuve = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idEpreuve );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Epreuve retrouver( int idEpreuve ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM Epreuve WHERE idEpreuve = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idEpreuve);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireEpreuve( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Epreuve> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM Epreuve ORDER BY nom_Epreuve";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Epreuve> Epreuves = new LinkedList<>();
			while (rs.next()) {
				Epreuves.add( construireEpreuve( rs));
			}
			return Epreuves;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	/*public List<Epreuve> listerPoutPoste(int idEpreuve) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM avoir INNER JOIN benevole ON avoir.idbenevole = benevole.idbenevole WHERE avoir.idposte = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idEpreuve );
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
	*/
    public int compterPourEpreuve( int idEpreuve ) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM Epreuve WHERE idEpreuve = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idEpreuve );
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

	private Epreuve construireEpreuve( ResultSet rs ) throws SQLException {
		Epreuve Epreuve = new Epreuve();
		Epreuve.setId( rs.getObject( "idEpreuve", Integer.class ) );
		Epreuve.setNom_epreuve( rs.getObject( "nom_epreuve", String.class ) );
		Epreuve.setDate_epreuve( rs.getObject( "date_epreuve", LocalDate.class ) );
		Epreuve.setLieu( rs.getObject( "lieu", String.class ) );
		Epreuve.setTarif( rs.getObject( "tarif", String.class ) );
		return Epreuve;
	}
	
}
