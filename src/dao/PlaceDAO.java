package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table place
 * 
 * @author KAKOU
 * @version 1.0
 * */

public class PlaceDAO extends ConnectionDAO {
	
	/**
	 * Constructor
	 */
	public PlaceDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Permet d'ajouter une place dans la table place.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * @param place la place a ajouté dans la table
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Place place) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("INSERT INTO place(place_id_dominante, place_id_promotion, nombreplacemax, nombreplacereservealternant) VALUES(?, ?, ?, ?)");
			ps.setInt(1, place.getPlaceIdDominante());
			ps.setInt(2, place.getPlaceIdPromotion());
			ps.setInt(3, place.getNombrePlaceMax());
			ps.setInt(4, place.getNbrePlaceReserveAlternant());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant d'étape existe déjà. Ajout impossible !");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	/**
	 * Permet de supprimer une place par id dans la table place.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id du supplier à supprimer
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	public int delete(int idDominante, int idPromotion) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, le ? represente la valeur de l'ID
			// a communiquer dans la suppression.
			// le getter permet de recuperer la valeur de l'ID du fournisseur
			ps = con.prepareStatement("DELETE FROM place WHERE place_id_dominante = ? AND place_id_promotion = ?");
			ps.setInt(1, idDominante);
			ps.setInt(2, idPromotion);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Cette place est utilisé dans une autre table, suppression impossible !");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}

	
	/**
	 * Permet de modifier une place dans la table place.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param place la place a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Place place) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans la modification.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("UPDATE place set nombreplacemax = ?, nombreplacereservealternant = ? WHERE place_id_dominante = ? AND place_id_promotion = ?");
			ps.setInt(1, place.getNombrePlaceMax());
			ps.setInt(2, place.getNbrePlaceReserveAlternant());
			ps.setInt(3, place.getPlaceIdDominante());
			ps.setInt(4, place.getPlaceIdPromotion());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00936"))
				System.out.println("Erreur de syntaxe dans la requete. Modification  impossible !");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	/**
	 * Permet de recuperer une place a partir de l'id de la promotion et l'id de la dominante
	 * 
	 * @param idDominante l'id de la dominante de la place a recuperer
	 * @param idPromotion l'id de la promotion de la place a récupérer
	 * @return le place trouve;
	 * 			null si aucune place ne correspond a ces identifiants
	 */
	public Place get(int idDominante, int idPromotion) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Place returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM place WHERE place_id_dominante = ? AND place_id_promotion = ? ");
			ps.setInt(1, idDominante);
			ps.setInt(2, idPromotion);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Place(rs.getInt("place_id_dominante"),
									       rs.getInt("place_id_promotion"),
									       rs.getInt("nombreplacemax"),
									       rs.getInt("nombreplacereservealternant"));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	/**
	 * Permet de recuperer toutes les places stockées dans la table place
	 * 
	 * @return une ArrayList de fournisseur
	 */
	public ArrayList<Place> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Place> returnValue = new ArrayList<Place>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM place ORDER BY place_id_dominante");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Place(rs.getInt("place_id_dominante"),
					       				rs.getInt("place_id_promotion"),
					       				rs.getInt("nombreplacemax"),
					       				rs.getInt("nombreplacereservealternant")));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}

	/**
	 * ATTENTION : Cette méthode n'a pas vocation à être executée lors d'une utilisation normale du programme !
	 * Elle existe uniquement pour TESTER les méthodes écrites au-dessus !
	 * 
	 * @param args non utilisés
	 * @throws SQLException si une erreur se produit lors de la communication avec la BDD
	 */
	public static void main(String[] args) throws SQLException {
		PlaceDAO placeDAO = new PlaceDAO();
		//Place place = new Place(2,3,10,5);
		//int ligne = placeDAO.update(place); 
		//System.out.println(ligne + "ligne mise a jour");
		//place.afficher();
		ArrayList<Place> returnValue = placeDAO.getList();
		for (Place p : returnValue) {
			System.out.println("--------------------------------");
			p.afficher();
		}
	}

}
