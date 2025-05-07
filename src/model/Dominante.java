package model;

/**
 * Class Dominante qui represente la dominante d'un utilisateur
 * @author SIDY
 * @version 1.0
 */
public class Dominante {
	
	private int idDominante ;
	private String nom ;
	private String sigle ;
	
	/**
	 * Permet de creer une dominante
	 * @param idDominante
	 * @param nom 
	 * @param sigle
	 */
	public Dominante(int idDominante, String nom, String sigle) {
		this.idDominante = idDominante ;
		this.nom = nom ;
		this.sigle = sigle;
	}
	
	/**
	 * Permet de creer une dominante sans id
	 * @param nom
	 * @param sigle
	 */
	public Dominante(String nom, String sigle) {
		this.nom = nom;
		this.sigle = sigle;
	}

	/**
	 * Permet de recuperer l'Id de la dominante
	 * @return idDominante
	 */
	public int getIdDominante() {
		return idDominante ;
	}
	
	/**
	 * Permet de recuperer le nom de la dominante
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Permet de recuperer le sigle de la dominante
	 * @return sigle
	 */
	public String getSigle() {
		return sigle ;
	}
	
	/**
	 * Permet de modifier l'Id de la dominante
	 * @param idDominante
	 */
	public void setIdDom(int idDominante) {
		this.idDominante = idDominante;
	}
	
	/**
	 * Permet de modifier le nom de la dominante
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Permet de modifier le sigle de la dominante
	 * @param sigle
	 */
	public void setSigle(String sigle) {
		this.sigle = sigle ;
	}
	
	/**
	 * Permet d'afficher les informations de la dominante
	 */
	public void afficher() {
		System.out.println("Id Dominante: " + idDominante);
		System.out.println("Nom: " + nom);
		System.out.println("Sigle : " + sigle);
	}
}
