package dao;

import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table dominante
 * 
 * @author KAKOU
 * @version 1.0
 * */
public class DominanteDAO extends ConnectionDAO{
	
	/**
	 * Constructor
	 */
	public DominanteDAO() {
		super();
	}
	
	//"INSERT INTO dominante(iddominante, nom, sigle) VALUES(?, ?, ?)"
	
	/**
	 * Permet d'ajouter une dominante dans la table dominante
	 * @param dominante
	 * @return nombre de ligne ajoute dans la table
	 */
	public int add(Dominante dominante) {
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
			ps = con.prepareStatement("INSERT INTO dominante(iddominante, nom, sigle) VALUES(?, ?, ?)");
			ps.setInt(1, dominante.getIdDominante());
			ps.setString(2, dominante.getNom());
			ps.setString(3, dominante.getSigle());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de dominate d'etudiant existe déjà. Ajout impossible !");
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
	 * Permet de modifier une dominante dans la table dominante.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param dominante la dominante a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Dominante dominante) {
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
			ps = con.prepareStatement("UPDATE dominante set nom = ?, sigle = ? WHERE iddominante = ?");
			ps.setString(1, dominante.getNom());
			ps.setString(2, dominante.getSigle());
			ps.setInt(3, dominante.getIdDominante());

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
	 * Permet de recupérer une dominante par id dans la table dominante.
	 * 
	 * @param idDom id de la dominate
	 * @return la dominante trouvé
	 */
	public Dominante get(int idDomimante) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Dominante returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM dominante WHERE iddominante = ?");
			ps.setInt(1, idDomimante);
			
			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Dominante(rs.getInt("iddominante"),
					rs.getString("nom"),
					rs.getString("sigle")
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
	 * Permet de supprimer une dominante par id dans la table dominante.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param idDominante id de la dominante a supprimé
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	public int delete(int idDominante) {
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
			ps = con.prepareStatement("DELETE FROM dominante WHERE iddominante = ?");
			ps.setInt(1, idDominante);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Suppression de la dominante impossible !, car elle est utilisé"
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
	public ArrayList<Dominante> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Dominante> returnValue = new ArrayList<Dominante>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM dominante ORDER BY iddominante ASC");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Dominante(rs.getInt("iddominante"),
						rs.getString("nom"),
						rs.getString("sigle")
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
		DominanteDAO dom = new DominanteDAO();
		//Dominante newDom = new Dominante(15, "Math physique de l'ingénieur", "MPI");
		//int ligne = dom.add(newDom);
		//System.out.println(ligne + " ligne inséré ");
		ArrayList<Dominante> resultat = dom.getList();
		
		for(Dominante d : resultat) {
			d.afficher();
			System.out.println("---------------------");
		}
		

	}

}
