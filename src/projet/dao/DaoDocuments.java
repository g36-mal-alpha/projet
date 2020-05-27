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
import projet.data.Documents;

public class DaoDocuments {


	// Champs

	@Inject
	private DataSource		dataSource;
	
	// Actions 

	public int inserer( Documents documents ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO documents_licencies ( nom_doc, date_doc, lien_doc ) VALUES( ?, ?, ?) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, documents.getNom_doc() );
			stmt.setObject( 2, documents.getDate_doc() );
			stmt.setObject( 3, documents.getLien_doc()  );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			documents.setId( rs.getObject( 1, Integer.class) );
			return documents.getId();

		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Documents documents ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE documents_licencies SET nom_doc = ?, date_doc = ?,  lien_doc = ? WHERE iddocument =  ?";
			stmt = cn.prepareStatement( sql );
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, documents.getNom_doc() );
			stmt.setObject( 2, documents.getDate_doc() );
			stmt.setObject( 3, documents.getLien_doc()  );
			stmt.setObject( 4, documents.getId()  );
			stmt.executeUpdate();
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int iddocument ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM documents_licencies WHERE iddocument = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, iddocument );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Documents retrouver( int iddocument ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM documents_licencies WHERE iddocument = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, iddocument);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireDocuments( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Documents> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM documents_licencies ORDER BY nom_doc";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Documents> document = new LinkedList<>();
			while (rs.next()) {
				document.add( construireDocuments( rs));
			}
			return document;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	/*public List<Documents> listerPoutPoste(int iddocuments) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM avoir INNER JOIN benevole ON avoir.idbenevole = benevole.idbenevole WHERE avoir.idposte = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, iddocuments );
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
	
    public int compterPourDocuments( int iddocument ) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM documents_licencies WHERE iddocument = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, iddocument );
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

	private Documents construireDocuments( ResultSet rs ) throws SQLException {
		Documents documents = new Documents();
		documents.setId( rs.getObject( "iddocument", Integer.class ) );
		documents.setNom_doc( rs.getObject( "nom_doc", String.class ) );
		documents.setDate_doc( rs.getObject( "date_doc", LocalDate.class ) );
		documents.setLien_doc( rs.getObject( "lien_doc", String.class ) );
		return documents;
	}
	
}
