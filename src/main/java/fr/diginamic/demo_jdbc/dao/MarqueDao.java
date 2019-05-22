package fr.diginamic.demo_jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.TechnicalException;
import fr.diginamic.demo_jdbc.modele.Produit;

/**
 * @author KHARBECHE Bilel
 *
 */
public class MarqueDao {

	/** listePizza : ArrayList<Pizza> */
	private ArrayList<Produit> listeMarque = new ArrayList();

	/**
	 * @param marque
	 */
	public void addMarque(String marque) {
		Connection conn = ConnectionMgr.getInstance();
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement("INSERT INTO marque (nom) VALUES (?)");
			statement.setString(1, marque);
			statement.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				throw new TechnicalException("Le rollback a échoué", e1);
			}
			throw new TechnicalException("La ligne n'a pas pu être insérée", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw new TechnicalException("La fermeture de la base de données à échouée", e);

			}
		}
	}

	public Integer findIdMarque(String marque) {
		Connection conn = ConnectionMgr.getInstance();
		PreparedStatement statement = null;
		ResultSet cursor = null;
		Integer id = 0;

		try {
			statement = conn.prepareStatement("SELECT id FROM marque WHERE nom =?");
			statement.setString(1, marque);
			cursor = statement.executeQuery();

			if (cursor.next()) {
				id = cursor.getInt("id");
			}

			return id;

		} catch (SQLException e) {
			throw new TechnicalException("La ligne n'a pas pu être insérée", e);
		} finally {
			try {
				if (cursor != null) {
					cursor.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw new TechnicalException("La fermeture de la base de données à échouée", e);

			}
		}
	}

	public boolean marqueExists(String marque) {
		return findIdMarque(marque) != 0;
	}
}
