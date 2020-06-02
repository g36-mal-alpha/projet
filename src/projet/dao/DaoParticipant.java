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
import projet.data.Participant;


public class DaoParticipant {


	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoSexe	daoSexe;


	// Actions

	public int inserer( Participant participant ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			//probleme unique mail 
			cn = dataSource.getConnection();
			sql = "INSERT INTO participant (nom, prenom, numero_tel, date_naissance, adresse, role, certificat_medical, mail, niveau, materiel_utilise,  idsexe) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1,  participant.getNom() );
			stmt.setObject( 2,  participant.getPrenom() );
			stmt.setObject( 3,  participant.getNumero_tel() );
			stmt.setObject( 4,  participant.getDate_naissance() );
			stmt.setObject( 5,  participant.getAdresse() );
			stmt.setObject( 6,  participant.getRole() );
			stmt.setObject( 7,  participant.getCertificat_medical() );
			stmt.setObject( 8,  participant.getMail() );
			stmt.setObject( 9, participant.getNiveau() );
			stmt.setObject( 10, participant.getMateriel_utilise() );
			if ( participant.getSexe() == null ) {
				 stmt.setObject( 11, null );
			} 
			else {
				 stmt.setObject( 11,participant.getSexe().getId() );
			} 
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			participant.setId( rs.getObject( 1, Integer.class) );
		

		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
		
		return participant.getId();
	}


	public void modifier( Participant participant ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE participant SET nom = ?, prenom = ?, numero_tel = ?,  date_naissance = ?, adresse = ?, role = ?, certificat_medical = ?, mail = ?, niveau = ?, materiel_utilise = ?, idsexe = ?  WHERE idparticipant =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, participant.getNom() );
			stmt.setObject( 2, participant.getPrenom() );
			stmt.setObject( 3, participant.getNumero_tel() );
			stmt.setObject( 4, participant.getDate_naissance() );
			stmt.setObject( 5, participant.getAdresse() );
			stmt.setObject( 6, participant.getRole() );
			stmt.setObject( 7, participant.getCertificat_medical() );
			stmt.setObject( 8, participant.getMail() );
			stmt.setObject( 9, participant.getNiveau() );
			stmt.setObject( 10, participant.getMateriel_utilise() );
			if ( participant.getSexe() == null ) {
				 stmt.setObject( 11, null );
			} 
			else {
				 stmt.setObject( 11,participant.getSexe().getId() );
			} 
			stmt.setObject( 12, participant.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idParticipant ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM participant WHERE idparticipant = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idParticipant );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public Participant retrouver( int idParticipant ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM participant WHERE idparticipant = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idParticipant);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireParticipant( rs, false );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Participant> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM participant ORDER BY nom , prenom";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Participant> services = new LinkedList<>();
			while (rs.next()) {
				services.add( construireParticipant( rs, false ) );
			}
			return services;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

public int compterPourSexe( int idSexe ) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM participant WHERE idsexe = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idSexe );
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
	//nom = ?, prenom = ?, sexe = ?, numero_tel = ?,  date_naissance = ?, adresse = ?, role = ?, certificat_medical = ?, mail = ?, niveau = ?, materiel_utilise = ? 
	private Participant construireParticipant( ResultSet rs , boolean flagComplet ) throws SQLException {
		Participant participant = new Participant();
		participant.setId( rs.getObject( "idparticipant", Integer.class ) );
		participant.setNom( rs.getObject( "nom", String.class ) );
		participant.setPrenom( rs.getObject( "prenom", String.class ) );		
		participant.setNumero_tel( rs.getObject( "numero_tel", String.class ) );
		participant.setDate_naissance( rs.getObject( "date_naissance", LocalDate.class ) );
		participant.setAdresse( rs.getObject( "adresse", String.class ) );
		participant.setRole( rs.getObject( "role", String.class ) );		
		participant.setCertificat_medical( rs.getObject( "certificat_medical", String.class ) );
		participant.setMail( rs.getObject( "mail", String.class ) );
		participant.setNiveau( rs.getObject( "niveau", String.class ) );
		participant.setMateriel_utilise( rs.getObject( "materiel_utilise", String.class ) );
		if ( flagComplet ) {
			 Integer idSexe = rs.getObject( "idsexe", Integer.class );
			 if ( idSexe != null ) {
				 participant.setSexe( daoSexe.retrouver(idSexe) );
			 }
		}
		return participant;
	}
}
