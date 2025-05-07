package model;

/**
* Class EtapeTraitement qui represente les etapes du traitement
* @author SIDY
* @version 1.0
*/
public class EtapeTraitement {
	
	private int idEtape ;
	private String niveauTraitement ;	
	
	/**
	 * Permet de creer une etape sans id
	 * @param niveauTraitement
	 */
	public EtapeTraitement(String niveauTraitement) {
		this.niveauTraitement = niveauTraitement;
	}

	/**
	 * Permet de creer une etape avec id
	 * @param idEtape
	 * @param niveauTraitement
	 */	
	public EtapeTraitement(int idEtape, String niveauTraitement) {
		this.idEtape = idEtape;
		this.niveauTraitement = niveauTraitement ;
	}
	
	/**
	 * Permet de recuperer l'ID de l'etape
	 * @return idEtape
	 */
	public int getIdEtape() {
		return idEtape ;
	}
	
	/**
	 * Permet de recuperer le niveau du traitement
	 * @return niveauTraitement
	 */
	public String getNiveauTraitement() {
		return niveauTraitement;
	}
	
	/**
	 * Permet de modifier l'ID de l'etape
	 * @param idEtape
	 */
	public void setidEtape(int idEtape) {
		this.idEtape = idEtape;
	}
	

	/**
	 * Permet de modifier le niveau du traitement
	 * @param niveauTraitement
	 */
	public void setniveauTraitement(String niveauTraitement) {
		this.niveauTraitement = niveauTraitement ;
	}
	
	/**
	 * Permet d'afficher les informations du traitement
	 */
	public void afficher() {
		System.out.println("ID de l'etape: " + idEtape);
		System.out.println("Niveau du traitement: " + niveauTraitement);
	}

}
