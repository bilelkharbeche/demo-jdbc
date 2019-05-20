package fr.diginamic.demo_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import exception.TechnicalException;

public class TestConnexionJdbc {

	public static void main(String[] args) {

		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");

		String driverName = monFichierConf.getString("database.driver");
		String url = monFichierConf.getString("database.url");
		String userName = monFichierConf.getString("database.user");
		String password = monFichierConf.getString("database.password");

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			throw new TechnicalException("Le driver JDBC " + driverName + " n'a pas été trouvé ", e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, "root", "lolbilel");
			System.out.println(conn);
		} catch (SQLException e) {
			throw new TechnicalException("La connexion à la base de données n'a pas réussie", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new TechnicalException("La fermeture de la connexion à la base de données a échoué", e);
			}
		}

	}

}
