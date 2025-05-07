package dao;

import java.sql.*;
import java.util.ArrayList;
import model.*;


/**
 * Classe d'acces aux donnees contenues dans la table promotion
 * 
 * @author KAKOU
 * @version 1.0
 * */
public class PromotionDAO extends ConnectionDAO {
	
	/**
	 * Constructor
	 */
	public PromotionDAO() {
		super();
	}
	
	/**
	 * Permet d'ajouter une promotion dans la table promotion
	 * @param promotion
	 * @return
	 */
	public int add(Promotion promotion) {
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
			ps = con.prepareStatement("INSERT INTO promotion(idpromotion, promotion_id_etape, annediplomation) VALUES(?, ?, ?)");
			ps.setInt(1, promotion.getIdPromotion());
			ps.setInt(2, promotion.getPromIdEtapeTraitement());
			ps.setInt(3, promotion.getAnneeDiplomation());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de dominante existe déjà. Ajout impossible !");
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
	 * Permet de recupérer une promotion par id dans la table promotion.
	 * 
	 * @param idPromotion id de la dominate
	 * @return la dominante trouvé
	 */
	public Promotion get(int idPromotion) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Promotion returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM promotion WHERE idpromotion = ?");
			ps.setInt(1, idPromotion);
			
			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Promotion(rs.getInt("idpromotion"),
						rs.getInt("promotion_id_etape"),
						rs.getInt("annediplomation")
				);
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
	 * Permet de modifier une dominante dans la table dominante.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param dominante la dominante a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Promotion promotion) {
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
			ps = con.prepareStatement("UPDATE promotion set promotion_id_etape = ?, annediplomation = ? WHERE idpromotion = ?");
			ps.setInt(1, promotion.getPromIdEtapeTraitement());
			ps.setInt(2, promotion.getAnneeDiplomation());
			ps.setInt(3, promotion.getIdPromotion());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
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
	 * Permet de supprimer une promotion par id dans la table promotion.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param idPromotion id de la promotion a supprimé
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	public int delete(int idPromotion) {
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
			ps = con.prepareStatement("DELETE FROM promotion WHERE idpromotion = ?");
			ps.setInt(1, idPromotion);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Suppression de la promotion impossible !, car elle est utilisé"
						+ "dans une autre table comme clé étrangère");
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
	 * Permet de recupérer la liste de tous les choix dans la table choix
	 * @return list des dominantes
	 */
	public ArrayList<Promotion> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Promotion> returnValue = new ArrayList<Promotion>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM promotion ORDER BY idpromotion ASC");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Promotion(rs.getInt("idpromotion"),
						rs.getInt("promotion_id_etape"),
						rs.getInt("annediplomation")
				));
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
	 * Methode de test des requetes
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		PromotionDAO dom = new PromotionDAO();
		//int resultat = dom.delete(6);
		//System.out.println(resultat + " promotion supprimee");
		//C##BDD1_14.SYS_C00612849
		ArrayList<Promotion> list = dom.getList();
		for (Promotion de : list) {
			de.afficher();
			System.out.println("-------------------------");
		}

	}

}
