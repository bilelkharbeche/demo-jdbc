package fr.diginamic.demo_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.TechnicalException;

public class TestConnexionJdbc {

	private static final Logger LOG = LoggerFactory.getLogger(TestConnexionJdbc.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");

		String driverName = monFichierConf.getString("database.driver");
		String url = monFichierConf.getString("database.url");
		String userName = monFichierConf.getString("database.user");
		String password = monFichierConf.getString("database.password");

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			LOG.error("La connexion à la base de données n'a pas pu s'établir", e);
			throw new TechnicalException("Le driver JDBC " + driverName + " n'a pas été trouvé ", e);
		}

		Connection conn = null;
		ResultSet cursor = null;
		Statement monStatement = null;

		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);

			monStatement = conn.createStatement();

			monStatement.executeUpdate(
					"INSERT INTO ARTICLE(ID, DESIGNATION, FOURNISSEUR, PRIX) VALUES (1,'Table','Ikea',10)");
			monStatement.executeUpdate(
					"INSERT INTO ARTICLE(ID, DESIGNATION, FOURNISSEUR, PRIX) VALUE(2,'Frigo','Ikea',0.5)");
			monStatement.executeUpdate(
					"INSERT INTO ARTICLE(ID, DESIGNATION, FOURNISSEUR, PRIX) VALUE(3,'Canape','Ikea',25)");
			monStatement
					.executeUpdate("INSERT INTO ARTICLE(ID, DESIGNATION, FOURNISSEUR, PRIX) VALUE(4,'Four','Ikea',15)");

			monStatement.executeUpdate("UPDATE ARTICLE SET PRIX=PRIX*1.25 WHERE PRIX > 10");

			ArrayList<Article> articles = new ArrayList();

			cursor = monStatement.executeQuery("SELECT * FROM ARTICLE");
			while (cursor.next()) {
				Integer id = cursor.getInt("ID");
				String designation = cursor.getString("DESIGNATION");
				String fournisseur = cursor.getString("FOURNISSEUR");
				Integer prix = cursor.getInt("PRIX");

				Article article = new Article(id, designation, fournisseur, prix);
				articles.add(article);
			}

			for (int i = 0; i < articles.size(); i++) {
				System.out.println(articles.get(i));
			}

			// cursor.close();

			cursor = monStatement.executeQuery("SELECT AVG(PRIX) AS Moyenne FROM ARTICLE");

			if (cursor.next()) {
				double moyenne = cursor.getDouble("Moyenne");
				System.out.println(moyenne);
			}

			monStatement.executeUpdate("DELETE FROM ARTICLE");

			conn.commit();

		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				LOG.error("Le rollback a échoué", e1);
			}
			LOG.error("La connexion à la base de données n'a pas pu s'établir", e);
			throw new TechnicalException("La connexion à la base de données n'a pas réussie", e);
		} finally {
			try {
				if (cursor != null) {
					cursor.close();
				}
				if (monStatement != null) {
					monStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				LOG.error("La connexion à la base de données n'a pas pu s'établir", e);
				throw new TechnicalException("La fermeture de la connexion à la base de données a échoué", e);
			}
		}

	}

}
