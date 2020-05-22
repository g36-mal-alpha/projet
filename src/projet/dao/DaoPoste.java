package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			sql = "INSERT INTO poste ( libelle, lieu, heure_debut, heure_fin, numero_poste, idcategorie ) VALUES( ?, ?, ?, ?, ?, ? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, poste.getLibelle() );
			stmt.setObject( 2, poste.getLieu() );
			stmt.setObject( 3, poste.getHeure_debut() );
			stmt.setObject( 4, poste.getHeure_fin() );
			stmt.setObject( 5, poste.getNumero_poste() );
			if ( poste.getCategorie() == null ) {
				 stmt.setObject( 6, null );
			} 
			else {
				 stmt.setObject( 6,poste.getCategorie().getId() );
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
			sql = "UPDATE poste SET libelle = ?, lieu = ?, heure_debut = ?, heure_fin = ?, numero_poste = ?, idcategorie = ?  WHERE idposte =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, poste.getLibelle() );
			stmt.setObject( 2, poste.getLieu() );
			stmt.setObject( 3, poste.getHeure_debut() );
			stmt.setObject( 4, poste.getHeure_fin() );
			stmt.setObject( 5, poste.getNumero_poste() );
			if ( poste.getCategorie() == null ) {
				 stmt.setObject( 6, null );
			} 
			else {
				 stmt.setObject( 6,poste.getCategorie().getId() );
			} 
			stmt.setObject( 7, poste.getId() );
			stmt.executeUpdate();
			supprimerAvoir(8);
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
				return construirePoste( rs, false );
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


	// Méthodes auxiliaires

	private Poste construirePoste( ResultSet rs , boolean flagComplet ) throws SQLException {
		Poste poste = new Poste();
		poste.setId( rs.getObject( "idposte", Integer.class ) );
		poste.setLibelle( rs.getObject( "libelle", String.class ) );
		poste.setLieu( rs.getObject( "lieu", String.class ) );
		poste.setHeure_debut( rs.getObject( "heure_debut", LocalTime.class ) );
		poste.setHeure_fin( rs.getObject( "heure_fin", LocalTime.class ) );
		poste.setNumero_poste( rs.getObject( "numero_poste", Integer.class ) );
		if ( flagComplet ) {
			 Integer idCategorie = rs.getObject( "idcategorie", Integer.class );
			 if ( idCategorie != null ) {
				 poste.setCategorie( daoCategorie.retrouver(idCategorie) );
			 }
			 poste.getBenevoles().setAll( daoBenevole.listerPoutPoste( poste.getId() ) );
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
			sql = "INSERT INTO avoir (idposte , idbenevole ) VALUES( ?, ?) ";
			stmt = cn.prepareStatement( sql);
			
			stmt.setObject( 1, poste.getId() );
			for ( Benevole benevole : poste.getBenevoles()) {
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

}
	
