package fr.diginamic.demo_jdbc.modele;

import java.util.Arrays;
import java.util.List;

import fr.diginamic.demo_jdbc.utils.DoubleUtils;

public class Produit {

	/** cat : Categorie */
	private String nomCategorie;
	/** marque : String */
	private String nomMarque;
	/** nom : String */
	private String nom;
	/** ingredient : List<String> */
	private List<String> ingredient;
	/** graisse : Double */
	private Double graisse;
	/** sucre : Double */
	private Double sucre;
	/** fibre : Double */
	private Double fibre;
	/** proteine : Double */
	private Double proteine;
	/** sel : Double */
	private Double sel;
	/** id_categorie : int */
	private int idCategorie;
	/** id_marque : int */
	private int idMarque;

	/**
	 * Constructor
	 * 
	 * @param line
	 */
	public Produit(String line) {
		try {
			String[] attributs = line.split("\\|");
			this.nomCategorie = attributs[0];
			this.nomMarque = attributs[1];
			this.nom = attributs[2];
			this.ingredient = Arrays.asList(attributs[4].split(","));
			this.graisse = DoubleUtils.convert(attributs[6]);
			this.sucre = DoubleUtils.convert(attributs[7]);
			this.fibre = DoubleUtils.convert(attributs[8]);
			this.proteine = DoubleUtils.convert(attributs[9]);
			this.sel = DoubleUtils.convert(attributs[10]);
		} catch (NumberFormatException e) {
			System.out.println(line);
			e.printStackTrace();
		}

	}

	/**
	 * Getter
	 * 
	 * @return the nomCategorie
	 */
	public String getNomCategorie() {
		return nomCategorie;
	}

	/**
	 * Setter
	 * 
	 * @param nomCategorie
	 *            the nomCategorie to set
	 */
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	/**
	 * Getter
	 * 
	 * @return the nomMarque
	 */
	public String getNomMarque() {
		return nomMarque;
	}

	/**
	 * Setter
	 * 
	 * @param nomMarque
	 *            the nomMarque to set
	 */
	public void setNomMarque(String nomMarque) {
		this.nomMarque = nomMarque;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the ingredient
	 */
	public List<String> getIngredient() {
		return ingredient;
	}

	/**
	 * Setter
	 * 
	 * @param ingredient
	 *            the ingredient to set
	 */
	public void setIngredient(List<String> ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * Getter
	 * 
	 * @return the graisse
	 */
	public Double getGraisse() {
		return graisse;
	}

	/**
	 * Setter
	 * 
	 * @param graisse
	 *            the graisse to set
	 */
	public void setGraisse(Double graisse) {
		this.graisse = graisse;
	}

	/**
	 * Getter
	 * 
	 * @return the sucre
	 */
	public Double getSucre() {
		return sucre;
	}

	/**
	 * Setter
	 * 
	 * @param sucre
	 *            the sucre to set
	 */
	public void setSucre(Double sucre) {
		this.sucre = sucre;
	}

	/**
	 * Getter
	 * 
	 * @return the fibre
	 */
	public Double getFibre() {
		return fibre;
	}

	/**
	 * Setter
	 * 
	 * @param fibre
	 *            the fibre to set
	 */
	public void setFibre(Double fibre) {
		this.fibre = fibre;
	}

	/**
	 * Getter
	 * 
	 * @return the proteine
	 */
	public Double getProteine() {
		return proteine;
	}

	/**
	 * Setter
	 * 
	 * @param proteine
	 *            the proteine to set
	 */
	public void setProteine(Double proteine) {
		this.proteine = proteine;
	}

	/**
	 * Getter
	 * 
	 * @return the sel
	 */
	public Double getSel() {
		return sel;
	}

	/**
	 * Setter
	 * 
	 * @param sel
	 *            the sel to set
	 */
	public void setSel(Double sel) {
		this.sel = sel;
	}

	/**
	 * Getter
	 * 
	 * @return the idCategorie
	 */
	public int getIdCategorie() {
		return idCategorie;
	}

	/**
	 * Setter
	 * 
	 * @param idCategorie
	 *            the idCategorie to set
	 */
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	/**
	 * Getter
	 * 
	 * @return the idMarque
	 */
	public int getIdMarque() {
		return idMarque;
	}

	/**
	 * Setter
	 * 
	 * @param idMarque
	 *            the idMarque to set
	 */
	public void setIdMarque(int idMarque) {
		this.idMarque = idMarque;
	}

}