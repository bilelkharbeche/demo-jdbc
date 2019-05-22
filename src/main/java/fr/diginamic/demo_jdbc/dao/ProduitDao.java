package fr.diginamic.demo_jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.TechnicalException;
import fr.diginamic.demo_jdbc.modele.Produit;

public class ProduitDao {

	public void addProduit(Produit produit) {

		Connection conn = ConnectionMgr.getInstance();
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement(
					"INSERT INTO produit (nom, graisse, sucre, fibre, proteine, sel, id_marque, id_cat) VALUES (?,?,?,?,?,?,?,?)");
			statement.setString(1, produit.getNom());
			statement.setDouble(2, produit.getGraisse());
			statement.setDouble(3, produit.getSucre());
			statement.setDouble(4, produit.getFibre());
			statement.setDouble(5, produit.getProteine());
			statement.setDouble(6, produit.getSel());
			statement.setDouble(7, produit.getIdMarque());
			statement.setDouble(8, produit.getIdCategorie());
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

	public Integer findIdProduit(String produit) {
		Connection conn = ConnectionMgr.getInstance();
		PreparedStatement statement = null;
		ResultSet cursor = null;
		Integer id = 0;

		try {
			statement = conn.prepareStatement("SELECT id FROM produit WHERE nom =?");
			statement.setString(1, produit);
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

	public boolean produitExists(String produit) {
		return findIdProduit(produit) != 0;
	}

}
