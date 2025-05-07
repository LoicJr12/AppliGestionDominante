package model;

/**
 * Class Utilisateur qui represente un utilisateur
 * @author KAKOU
 * @version 1.0
 */
public class Utilisateur {
	
	private int idUtilisateur ;
	private String password ;
	private String type ;

	/**
	 * Permet de creer un utilisateur
	 * @param idUtilisateur
	 * @param password
	 * @param type
	 */
	public Utilisateur(int idUtilisateur, String password, String type) {
		this.idUtilisateur = idUtilisateur ;
		this.password = password ;
		this.type = type ;
	}
	
	/**
	 * Permet de recuperer l'Id de l'utilisateur
	 * @return idUtilisateur
	 */
	public int getIdUtilisateur() {
		return idUtilisateur ;
	}
	
	/**
	 * Permet de recuperer le mot de passe de l'utilisateur
	 * @return password
	 */
	public String getPassword() {
		return password ;
	}
	
	/**
	 * Permet de recuperer le type de l'utilisateur
	 * @return type
	 */
	public String getType() {
		return type ;
	}
	
	/**
	 * Permet de modifier l'Id de l'utilisateur
	 * @param idUtilisateur
	 */
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	/**
	 * Permet de modifier le mot de passe de l'utilisateur
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password ;
	}
	
	/**
	 * Permet de modifier le type de l'utilisateur
	 * @param type
	 */
	public void setType(String type) {
		this.type = type ;
	}
	
	/**
	 * Permet d'afficher les informations de l'utilisateur
	 */
	public void afficher() {
		System.out.println("Id Utilisateur: " + idUtilisateur);
		System.out.println("Mot de passe : " + password);
		System.out.println("Type : " + type);
	}
}
