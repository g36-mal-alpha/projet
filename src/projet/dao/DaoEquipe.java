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
import projet.data.Equipe;

public class DaoEquipe {


	// Champs

	@Inject
	private DataSource		dataSource;
	
	// Actions 

	public int inserer( Equipe equipe ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO equipe ( nom_equipe, valide, paye, nb_plateau ) VALUES( ?, ?, ?, ?) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, equipe.getNom_equipe() );
			stmt.setObject( 2, equipe.getValide() );
			stmt.setObject( 3, equipe.getPaye()  );
			stmt.setObject( 4, equipe.getNb_plateau()  );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			equipe.setId( rs.getObject( 1, Integer.class) );
			return equipe.getId();

		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Equipe equipe ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE benevole SET nom_equipe = ?, valide = ?, paye = ?, n = ? WHERE idbenevole =  ?";
			stmt = cn.prepareStatement( sql );
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, equipe.getNom_equipe() );
			stmt.setObject( 2, equipe.getValide() );
			stmt.setObject( 3, equipe.getPaye()  );
			stmt.setObject( 4, equipe.getNb_plateau()  );
			stmt.setObject( 5, equipe.getId()  );
			stmt.executeUpdate();
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idEquipe ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM equipe WHERE idequipe = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idEquipe );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Equipe retrouver( int idEquipe ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM equipe WHERE idEquipe = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idEquipe);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireEquipe( rs, true);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Equipe> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM equipe ORDER BY nom_equipe";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Equipe> equipes = new LinkedList<>();
			while (rs.next()) {
				equipes.add( construireEquipe( rs ,false ));
			}
			return equipes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	/*public List<Equipe> listerPoutPoste(int idEquipe) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM avoir INNER JOIN benevole ON avoir.idbenevole = benevole.idbenevole WHERE avoir.idposte = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idEquipe );
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
    public int compterPourEquipe( int idEquipe ) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM equipe WHERE idequipe = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idEquipe );
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

	private Equipe construireEquipe( ResultSet rs, boolean valide  ) throws SQLException {
		Equipe equipe = new Equipe();
		equipe.setId( rs.getObject( "idbenevole", Integer.class ) );
		equipe.setNom_equipe( rs.getObject( "nom_equipe", String.class ) );
		equipe.setValide( rs.getObject( "valide", Boolean.class ) );
		equipe.setPaye( rs.getObject( "paye", Boolean.class ) );
		equipe.setNb_plateau( rs.getObject( "nb_plateau", Integer.class ) );
		return equipe;
	}
	
}
