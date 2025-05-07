package model;

/**
 * Class Etudiant qui represente un etudiant
 * @author KAKOU
 * @version 1.0
 */
public class Etudiant {
	
	private int idEtudiant ;
	private int etu_idUser ;
	private int etu_idProm ;
	private String nom ;
	private String prenom ;
	private String dateNaissance ; // sous format DD-MM-YYYY
	private int classement1A ;
	private String filiere ;
	
	/**
	 * Constructeur il permet de creer un etudiant
	 * @param idEtudiant
	 * @param idUser
	 * @param idPromotion
	 * @param nom
	 * @param prenom
	 * @param classement1A
	 * @param filiere
	 */
	public Etudiant(int idEtudiant, int idUser, int idPromotion, String nom, String prenom, String dateNaissance, int classement1A, String filiere) {
		this.idEtudiant = idEtudiant ;
		etu_idUser = idUser ;
		etu_idProm = idPromotion ;
		this.nom = nom ;
		this.prenom = prenom ;
		this.dateNaissance = dateNaissance ;
		this.classement1A = classement1A ;
		this.filiere = filiere ;
	}
	
	/**
	 * Constructeur il permet de creer un etudiant mais sans id utilisateur
	 * @param idEtudiant
	 * @param idPromotion
	 * @param nom
	 * @param prenom
	 * @param classement1A
	 * @param filiere
	 */
	public Etudiant(int idEtudiant, int idPromotion, String nom, String prenom, String dateNaissance, int classement1A, String filiere) {
		this.idEtudiant = idEtudiant ;
		etu_idProm = idPromotion ;
		this.nom = nom ;
		this.prenom = prenom ;
		this.dateNaissance = dateNaissance ;
		this.classement1A = classement1A ;
		this.filiere = filiere ;
	}
	
	/**
	 * Permet de recuperer l'Id de l'etduaint
	 * @return idEtudiant
	 */
	public int getIdEtudiant(){
		return idEtudiant ;
	}
	
	/**
	 * Permet de recuperer l'Id utilisateur de l'etduaint
	 * @return etu_idEtud
	 */
	public int getEtuIdUtisateur(){
		return etu_idUser ;
	}
	
	/**
	 * Permet de recuperer l'Id de la promotion de l'etduaint
	 * @return etu_idProm
	 */
	public int getEtuIdPromotion(){
		return etu_idProm ;
	}
	
	/**
	 * Permet de recuperer le classement de premiere annee de l'etduaint
	 * @return classement1A
	 */
	public int getClassement1A(){
		return classement1A ;
	}
	
	/**
	 * Permet de recuperer le nom de l'etduaint
	 * @return nom
	 */
	public String getNom(){
		return nom ;
	}
	
	/**
	 * Permet de recuperer le prenom de l'etduaint
	 * @return prenom
	 */
	public String getPrenom(){
		return prenom ;
	}
	
	/**
	 * Permet de recuperer la date de naissance de l'etduaint
	 * @return date
	 */
	public String getDateNaissance(){
		return dateNaissance ;
	}
	
	/**
	 * Permet de recuperer la filiere de l'etduaint
	 * @return filiere
	 */
	public String getFiliere(){
		return filiere ;
	}
	
	/**
	 * Permet de modifier l'Id de l'etudiant
	 * @param idEtuiant
	 */
	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	
	/**
	 * Permet de modifier l'Id utilisateur de l'etudiant
	 * @param idUser
	 */
	public void setEtuIdUser(int idUser) {
		etu_idUser = idUser;
	}
	
	/**
	 * Permet de modifier l'Id de la promo de l'etudiant
	 * @param idPromotion
	 */
	public void setEtuIdPromo(int idPromotion) {
		etu_idProm = idPromotion;
	}
	
	/**
	 * Permet de modifier le classement de premiere annee de l'etudiant
	 * @param classement1A
	 */
	public void setClassement1A(int classement1A) {
		this.classement1A = classement1A ;
	}
	
	/**
	 * Permet de modifier la date de naissance de l'etudiant
	 * @param dateNaissance sous format DD-MM-YYYY
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance ;
	}
	
	/**
	 * Permet de modifier le nom de l'etudiant
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom ;
	}
	
	/**
	 * Permet de modifier le prenom de l'etudiant
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Permet de modifier la filiere de l'etudiant
	 * @param filiere
	 */
	public void setFiliere(String filiere) {
		this.filiere = filiere ;
	}
	
	/**
	 * Permet d'afficher les informations de l'etudiant
	 */
	public void afficher() {
		System.out.println("Id Etudiant : " + idEtudiant);
		if(this.etu_idUser != 0) 
			System.out.println("Id Utilisateur de l'etudiant : " + etu_idUser);
		else 
			System.out.println("Id Utilisateur de l'etudiant : Aucun utilisateur attribué");
		System.out.println("Id Promotion de l'utilisateur : " + etu_idProm);
		System.out.println("Nom : " + nom);
		System.out.println("Prenom : " + prenom);
		System.out.println("Date de naissance : " + dateNaissance);
		System.out.println("Classement 1A : " + classement1A);
		System.out.println("Filiere : " + filiere);
	}

}
