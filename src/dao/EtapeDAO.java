package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table etapeTraitement
 * 
 * @author KAKOU
 * @version 1.0
 * */
public class EtapeDAO extends ConnectionDAO {

	/**
	 * Constructor
	 */
	public EtapeDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public int add(EtapeTraitement etape) {
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
			ps = con.prepareStatement("INSERT INTO etapetraitement(idetape, niveautraitement) VALUES(?, ?)");
			ps.setInt(1, etape.getIdEtape());
			ps.setString(2, etape.getNiveauTraitement());

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
	 * Permet de modifier une place dans la table place.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param place la place a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(EtapeTraitement etape) {
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
			ps = con.prepareStatement("UPDATE etapetraitement set niveautraitement = ? WHERE idetape = ? ");
			ps.setString(1, etape.getNiveauTraitement());
			ps.setInt(2, etape.getIdEtape());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00936"))
				System.out.println("Erreur de syntaxe dans la requete. Modification  impossible !");
			else if (e.getMessage().contains("ORA-02290"))
				System.out.println("violation de contraintes. cette valeur ne fait pas partir des valeurs que peuvent prendre le "
						+ "champ modifié. Modification  impossible !");
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
	 * Permet de supprimer un fournisseur par id dans la table supplier.
	 * Si ce dernier possede des articles, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id du supplier à supprimer
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	public int delete(int id) {
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
			ps = con.prepareStatement("DELETE FROM etapetraitement WHERE idetape = ?");
			ps.setInt(1, id);

			System.out.println("suppression de l'id " + id + "en cours ........");
			// Execution de la requete
			returnValue = ps.executeUpdate();
			System.out.println("suppression de l'id " + id + "terminé ........");
			

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Cette étape est liée à d'autres tables, suppression impossible !"
						         + " Supprimer d'abord les éléments auxquels elle est liée ou utiiser la méthode de suppression de ses éléments.");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
				ignore.getMessage();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
				ignore.getMessage();
			}
		}
		return returnValue;
	}
	
	public int count() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnValue = 0 ;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("SELECT COUNT(idetape) FROM etapetraitement");
			
			//délais de 20 sécondes
			ps.setQueryTimeout(15);

			// Execution de la requete
			rs = ps.executeQuery();
			
			if (rs.next()) {
				returnValue = rs.getInt("COUNT(idetape)");
			}

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
	 * Permet de recuperer une étape a partir de son id
	 * 
	 * @param id l'id de létape a recuperer
	 * @return l'étape trouvée;
	 * 	null si aucune étape ne correspond a cet id
	 */
	public EtapeTraitement get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		EtapeTraitement returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etapetraitement WHERE idetape = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new EtapeTraitement(rs.getInt("idetape"),
									       rs.getString("niveautraitement"));
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
	 * Permet de recuperer tous les fournisseurs stockes dans la table fournisseur
	 * 
	 * @return une ArrayList de fournisseur
	 */
	public ArrayList<EtapeTraitement> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<EtapeTraitement> returnValue = new ArrayList<EtapeTraitement>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etapetraitement ORDER BY idetape");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new EtapeTraitement(rs.getInt("idetape"),
						                     rs.getString("niveautraitement")));
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
		EtapeDAO etapeDAO = new EtapeDAO();
		//int id = etapeDAO.count() + 1;
		//System.out.println("id : " + id);
		//EtapeTraitement etape = new EtapeTraitement(7, "validé classiques");
		//etape.setniveauTraitement("fermé aux alternants");
		//int ligne = etapeDAO.add(etape);
		//System.out.println(ligne + " ligne ajoutée");
		//EtapeTraitement etapeNew = etapeDAO.get(9);
		//etapeNew.afficher();
		ArrayList<EtapeTraitement> returnValue = etapeDAO.getList();
		
		for (EtapeTraitement e : returnValue) {
			e.afficher();
			System.out.println("--------------------------------");
		}
		
		
		
		//System.out.println(ligne+" supprimé");
	}
}
