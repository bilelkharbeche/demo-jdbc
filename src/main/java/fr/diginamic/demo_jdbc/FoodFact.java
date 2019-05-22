package fr.diginamic.demo_jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import exception.TechnicalException;
import fr.diginamic.demo_jdbc.dao.ConnectionMgr;
import fr.diginamic.demo_jdbc.modele.Produit;
import fr.diginamic.demo_jdbc.modele.ProduitService;

public class FoodFact {

	public static void main(String[] args) {

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		} catch (SQLException e) {
			throw new TechnicalException("La connexion ne s'est pas établie dans le main", e);
		}

		String monFichierTxt = "C:\\Users\\KHARBECHE Bilel\\Documents\\workspace-sts-3.8.4.RELEASE\\demo-jdbc\\data\\openFoodFacts.csv";
		List<String> liste = null;
		try {
			liste = FileUtils.readLines(new File(monFichierTxt), "UTF-8");
			liste.remove(0);
			for (String li : liste) {
				System.out.println(li);
				Produit prd = new Produit(li);
				ProduitService.insert(prd);
			}
		} catch (IOException e) {
			throw new TechnicalException("Aucun fichier trouvé", e);
		}

		try {
			ConnectionMgr.getInstance().close();
		} catch (SQLException e) {
			throw new TechnicalException(e.getMessage(), e);
		}
	}

}
