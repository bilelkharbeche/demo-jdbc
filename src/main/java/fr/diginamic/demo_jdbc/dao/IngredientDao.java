package fr.diginamic.demo_jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.TechnicalException;

public class IngredientDao {

	public void addIngredient(String ingredient, Integer idPrd) {
		Connection conn = ConnectionMgr.getInstance();
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement("INSERT INTO ingredient (nom, id_produit) VALUES (?,?)");
			statement.setString(1, ingredient);
			statement.setInt(2, idPrd);
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

	public Integer findIdIngredient(String ingredient) {
		Connection conn = ConnectionMgr.getInstance();
		PreparedStatement statement = null;
		ResultSet cursor = null;
		Integer id = 0;

		try {
			statement = conn.prepareStatement("SELECT id FROM ingredient WHERE nom =?");
			statement.setString(1, ingredient);
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

	public boolean ingredientExists(String ingredient) {
		return findIdIngredient(ingredient) != 0;
	}
}
