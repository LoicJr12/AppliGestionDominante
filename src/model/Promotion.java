package model;

/**
 * Class Promotion qui represente une promotion d'etudiant
 * @author KAKOU
 * @version 1.0
 */
public class Promotion {
	
	/**
	 * Constructeur permettant de creer une promotion sans id
	 * @param prom_idEtape
	 * @param anneeDiplomation
	 */
	public Promotion(int prom_idEtape, int anneeDiplomation) {
		this.prom_idEtape = prom_idEtape;
		this.anneeDiplomation = anneeDiplomation;
	}

	private int idPromotion ;
	private int prom_idEtape ;
	private int anneeDiplomation;
	
	/**
	 * Constructeur permettant de creer une promotion d'etudiant
	 * @param idPromotion
	 * @param prom_idEtape
	 * @param anneeDiplomation
	 */
	public Promotion (int idPromotion, int prom_idEtape, int anneeDiplomation) {
		this.idPromotion = idPromotion ;
		this.prom_idEtape = prom_idEtape ;
		this.anneeDiplomation = anneeDiplomation ;
	}
	
	/**
	 * Permet de recuperer l'Id de la Promotion
	 * @return idPromotion
	 */
	public int getIdPromotion() {
		return idPromotion ;
	}
	
	/**
	 * Permet de recuperer l'id de l'etape du tratement appartenant a la promotion
	 * @return prom_idEtape
	 */
	public int getPromIdEtapeTraitement() {
		return prom_idEtape ;
	}
	
	/**
	 * Permet de recuperer l'annee de diplomation de la promotion
	 * @return anneeDiplomation
	 */
	public int getAnneeDiplomation() {
		return anneeDiplomation ;
	}
	
	/**
	 * Permet de mofier l'id de la promotion
	 * @param idPromotion
	 */
	public void setIdPromotion(int idPromotion) {
		this.idPromotion = idPromotion ;
	}
	
	/**
	 * Permet de modifier l'id de l'etape de traitement de l'utilisateur
	 * @param prom_idEtape
	 */
	public void setPromIdEtapeTraitement(int prom_idEtape) {
		this.prom_idEtape = prom_idEtape ;
	}
	
	/**
	 * Permet de modifier l'année de diplomation d'une promotion
	 * @param anneeDiplomation
	 */
	public void setAnneeDiplomation(int anneeDiplomation) {
		this.anneeDiplomation = anneeDiplomation ;
	}
	
	/**
	 * Permet d'afficher les informations de la promotion
	 */
	public void afficher() {
		System.out.println("Id Promotion : " + idPromotion);
		System.out.println("Id Etape de traitement de la promotion : " + prom_idEtape);
		System.out.println("Annee de diplomation : " + anneeDiplomation);
	}
}
