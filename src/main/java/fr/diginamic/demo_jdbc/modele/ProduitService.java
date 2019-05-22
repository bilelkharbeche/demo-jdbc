package fr.diginamic.demo_jdbc.modele;

import fr.diginamic.demo_jdbc.dao.CategorieDao;
import fr.diginamic.demo_jdbc.dao.IngredientDao;
import fr.diginamic.demo_jdbc.dao.MarqueDao;
import fr.diginamic.demo_jdbc.dao.ProduitDao;

public class ProduitService {

	public static void insert(Produit prod) {

		MarqueDao marqueDao = new MarqueDao();
		CategorieDao catDao = new CategorieDao();
		IngredientDao ingDao = new IngredientDao();
		ProduitDao prodDao = new ProduitDao();

		if (!marqueDao.marqueExists(prod.getNomMarque())) {
			marqueDao.addMarque(prod.getNomMarque());
		}

		if (!catDao.categorieExists(prod.getNomCategorie())) {
			catDao.addCategorie(prod.getNomCategorie());
		}

		if (!prodDao.produitExists(prod.getNom())) {

			Integer idCat = catDao.findIdCategorie(prod.getNomCategorie());
			Integer idMarque = marqueDao.findIdMarque(prod.getNomMarque());

			prod.setIdCategorie(idCat);
			prod.setIdMarque(idMarque);

			prodDao.addProduit(prod);

			Integer idProduit = prodDao.findIdProduit(prod.getNom());

			for (String ingredient : prod.getIngredient()) {
				ingDao.addIngredient(ingredient, idProduit);
			}
		}
	}

}
