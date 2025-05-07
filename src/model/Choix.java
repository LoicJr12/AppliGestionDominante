package model;

/**
* Class Choix qui represente le choix d'un utilisateur
* @author SIDY
* @version 1.0
*/
public class Choix {
	private int choix_idEtu ;
	private int choix_idDom ;
	private int numeroOrdre ;
	private String validation ;
	
	/**
	 * Permet de creer un choix
	 * @param numeroOrdre
	 * @param validation
	 */	
	public Choix(int choix_idEtu, int choix_idDom, int numeroOrdre, String validation) {
		this.choix_idEtu = choix_idEtu ;
		this.choix_idDom = choix_idDom ;
		this.numeroOrdre= numeroOrdre;
		this.validation = validation ;
	}
	
	/**
	 * Permet de récupérer l'id de l'etudiant a qui appartient le choix
	 * @return choix_idEtu l'id de l'etudiant 
	 */
	public int getChoixIdEtudiant() {
		return choix_idEtu ;
	}
	
	/**
	 * Permet de récupérer l'id de la dominante choisie
	 * @return choix_idDom l'id de la dominante 
	 */
	public int getChoixIdDominante() {
		return choix_idDom ;
	}
	
	/**
	 * Permet de recuperer le numero d'ordre de l'utilisateur
	 * @return numeroOrdre
	 */
	public int getNumeroOrdre() {
		return numeroOrdre ;
	}
	
	/**
	 * Permet de recuperer la validation de l'utilisateur
	 * @return validation
	 */
	public String getValidation() {
		return validation;
	}
	
	/**
	 * Permet de modifier l'id de l'étudiant du choix
	 * @param idEtudiant un id etudiant
	 */
	public void setChoixIdEtu(int idEtudiant) {
		choix_idEtu = idEtudiant ;
	}
	
	/**
	 * Permet de modifier l'id de la dominate du choix
	 * @param idDominante un id dominate
	 */
	public void setChoixIdDom(int idDominante) {
		choix_idDom = idDominante ;
	}
	
	/**
	 * Permet de modifier le numero d'ordre de l'utilisateur
	 * @param numeroOrdre
	 */
	public void setNumeroOrdre(int numeroOrdre) {
		this.numeroOrdre = numeroOrdre ;
	}
	
	/**
	 * Permet de modifier la validation de l'utilisateur
	 * @param validation
	 */
	public void setValidation(String validation) {
		this.validation = validation ;
	}
	
	/**
	 * Permet d'afficher les informations de l'utilisateur
	 */
	public void afficher() {
		System.out.println("Numero d'ordre: " + numeroOrdre);
		System.out.println("Id Etudiant : " + choix_idEtu);
		System.out.println("Id Dominante : " + choix_idDom);
		System.out.println("Validation: " + validation);
	}
	
	
	
}


