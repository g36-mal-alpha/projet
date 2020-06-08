package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Benevole;
import projet.data.Poste;
import projet.dao.DaoBenevole;

public class DaoPoste {


	// Champs
	
	@Inject
	private DataSource		dataSource;
	
	@Inject
	private DaoBenevole     daoBenevole;
	
	@Inject
	private DaoCategorie    daoCategorie;


	// Actions 

	public int inserer( Poste poste ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO poste ( libelle, lieu, nombre, jour, heure_debut, heure_fin, numero_poste, idcategorie ) VALUES( ?, ?, ?, ?, ?, ?, ?, ? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, poste.getLibelle() );
			stmt.setObject( 2, poste.getLieu() );
			stmt.setObject( 3, poste.getNombre() );
			stmt.setObject( 4, poste.getJour() );
			stmt.setObject( 5, poste.getHeure_debut() );
			stmt.setObject( 6, poste.getHeure_fin() );
			stmt.setObject( 7, poste.getNumero_poste() );
			if ( poste.getCategorie() == null ) {
				 stmt.setObject( 8, null );
			} 
			else {
				 stmt.setObject( 8,poste.getCategorie().getId() );
			} 
			
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			poste.setId( rs.getObject( 1, Integer.class) );
			insererAvoir(poste);
			return poste.getId();

		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Poste poste ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE poste SET libelle = ?, lieu = ?, nombre = ?, jour = ? , heure_debut = ?, heure_fin = ?, numero_poste = ?, idcategorie = ?  WHERE idposte =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, poste.getLibelle() );
			stmt.setObject( 2, poste.getLieu() );
			stmt.setObject( 3, poste.getNombre() );
			stmt.setObject( 4, poste.getJour() );
			stmt.setObject( 5, poste.getHeure_debut() );
			stmt.setObject( 6, poste.getHeure_fin() );
			stmt.setObject( 7, poste.getNumero_poste() );
			if ( poste.getCategorie() == null ) {
				 stmt.setObject( 8, null );
			} 
			else {
				 stmt.setObject( 8,poste.getCategorie().getId() );
			} 
			 
			stmt.setObject( 9, poste.getId() );
			stmt.executeUpdate();
			supprimerAvoir(poste.getId());
			insererAvoir(poste);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idPoste ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			supprimerAvoir(idPoste);
			cn = dataSource.getConnection();
			sql = "DELETE FROM poste WHERE idposte = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idPoste );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Poste retrouver( int idPoste ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM poste WHERE idposte = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idPoste);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construirePoste( rs, true );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Poste> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM poste ORDER BY numero_poste";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Poste> postes = new LinkedList<>();
			while (rs.next()) {
				postes.add( construirePoste( rs, false ) );
			}
			return postes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Poste> listerPourBenevole(int idBenevole) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM avoir INNER JOIN poste ON avoir.idPoste = poste.idPoste WHERE avoir.idbenevole = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idBenevole);
			rs = stmt.executeQuery();

			List<Poste> postes = new LinkedList<>();
			while (rs.next()) {
				postes.add( construirePoste( rs, false ) );
			}
			return postes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	// Méthodes auxiliaires

	private Poste construirePoste( ResultSet rs , boolean categorie ) throws SQLException {
		Poste poste = new Poste();
		poste.setId( rs.getObject( "idposte", Integer.class ) );
		poste.setLibelle( rs.getObject( "libelle", String.class ) );
		poste.setNombre( rs.getObject( "nombre", Integer.class ) );
		poste.setLieu( rs.getObject( "lieu", String.class ) );
		poste.setJour( rs.getObject( "jour", LocalDate.class ) );
		poste.setHeure_debut( rs.getObject( "heure_debut", String.class ) );
		poste.setHeure_fin( rs.getObject( "heure_fin", String.class ) );
		poste.setNumero_poste( rs.getObject( "numero_poste", Integer.class ) );
		if ( categorie ) {
			 Integer idCategorie = rs.getObject( "idcategorie", Integer.class );
			 if ( idCategorie != null ) {
				 poste.setCategorie( daoCategorie.retrouver(idCategorie) );
				 poste.getBenevoles().setAll( daoBenevole.listerPoutPoste( poste.getId() ) );
			 }
		} 
		return poste;
	}
	
	private void supprimerAvoir( int idPoste ) {
		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM avoir WHERE idposte = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idPoste );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	private void insererAvoir( Poste poste ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
	
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO avoir (idposte , idbenevole ) VALUES ( ?, ?) ";
			stmt = cn.prepareStatement( sql);
			
			for ( Benevole benevole : poste.getBenevoles()) {
				stmt.setObject( 1, poste.getId() );
				stmt.setObject( 2, benevole.getId() );
				stmt.executeUpdate();
			} 
			
		    } 
		catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn );
		}
	}

    public int compterPourPoste( int idPoste ) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM avoir WHERE idposte = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idPoste );
            rs = stmt.executeQuery();

            rs.next();
            return rs.getInt( 1 );

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
    }
    
    public int totalPourPoste( int idPoste ) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT nombre FROM poste WHERE idposte = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idPoste );
            rs = stmt.executeQuery();

            rs.next();
            return rs.getInt( 1 );

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
    }
}
	
