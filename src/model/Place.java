package model;

/**
* Class Place qui represente le nombre de places par dominante
* @author SIDY
* @version 1.0
*/
public class Place {
	
	private int place_idDominante;
	private int place_idPromotion ;
	private int nombrePlaceMax ;
	private int nbrePlaceReserveAlternant ;	
	
	/**
	 * Permet de creer une place
	 * @param nombrePlaceMax
	 * @param nbrePlaceReserveAlternant
	 */	
	
	public Place(int idDominante, int idPromotion,int nombrePlaceMax, int nbrePlaceReserveAlternant) {
		this.place_idDominante = idDominante ;
		this.place_idPromotion = idPromotion ;
		this.nombrePlaceMax = nombrePlaceMax;
		this.nbrePlaceReserveAlternant = nbrePlaceReserveAlternant ;
	}
	
	/**
	 * permet de recupérer l'id d'une dominante pour une place
	 * @return l'id de la dominante qui est référencé pour cette place
	 */
	public int getPlaceIdDominante() {
		return place_idDominante ;
	}
	
	/**
	 * permet de recupérer l'id d'une promotion pour une place
	 * @return l'id de la promotion qui est référencé pour cette place
	 */
	public int getPlaceIdPromotion() {
		return place_idPromotion ;
	}
	
	/**
	 * Permet de recuperer le nombre de place maximum
	 * @return nombrePlaceMax
	 */
	public int getNombrePlaceMax() {
		return nombrePlaceMax ;
	}
	
	/**
	 * Permet de recuperer le nombre de places reserve aux alternants
	 * @return nbrePlaceReserveAlternant
	 */
	public int getNbrePlaceReserveAlternant() {
		return nbrePlaceReserveAlternant;
	}
	

	public int getNbrePlaceClassique() {
		return nombrePlaceMax - nbrePlaceReserveAlternant;
	}
	
	/**
	 * Permet de modifier le nombre de place maximum
	 * @param nombrePlaceMax
	 */
	public void setnombrePlaceMax(int nombrePlaceMax) {
		this.nombrePlaceMax = nombrePlaceMax ;
	}
	

	/**
	 * Permet de modifier le nombre de places reserve aux alternants
	 * @param nbrePlaceReserveAlternant
	 */
	public void setnbrePlaceReserveAlternant(int nbrePlaceReserveAlternant) {
		this.nbrePlaceReserveAlternant = nbrePlaceReserveAlternant ;
	}
	
	/**
	 * Permet d'afficher le nombre de places
	 */
	public void afficher() {
		System.out.println("Id Dominante : " + place_idDominante);
		System.out.println("Id Promotion : " + place_idPromotion);
		System.out.println("Nombre de places maximum: " + nombrePlaceMax);
		System.out.println("Nombre de places pour alternants: " + nbrePlaceReserveAlternant);
	}
}
