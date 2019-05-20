package fr.diginamic.demo_jdbc;

public class Article {

	Integer id;
	String designation;
	String fournisseur;
	Integer prix;

	public Article(Integer id, String designation, String fournisseur, Integer prix) {
		super();
		this.id = id;
		this.designation = designation;
		this.fournisseur = fournisseur;
		this.prix = prix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article [id=" + id + ", designation=" + designation + ", fournisseur=" + fournisseur + ", prix=" + prix
				+ "]";
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Setter
	 * 
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * Getter
	 * 
	 * @return the fournisseur
	 */
	public String getFournisseur() {
		return fournisseur;
	}

	/**
	 * Setter
	 * 
	 * @param fournisseur
	 *            the fournisseur to set
	 */
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	/**
	 * Getter
	 * 
	 * @return the prix
	 */
	public int getPrix() {
		return prix;
	}

	/**
	 * Setter
	 * 
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(int prix) {
		this.prix = prix;
	}

}
