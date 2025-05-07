package dao;

import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table utilisateur
 * 
 * @author KAKOU 
 * @version 1.0
 * */
public class UtilisateurDAO extends ConnectionDAO {
	
	/**
	 * Constructor
	 */
	public UtilisateurDAO() {
		super();
	}
	
	/**
	 * Permet d'ajouter un utilisateur dans la table utilisateur.
	 * Le mode est auto-commit par defaut : chaque insertion est validee.
	 * 
	 * @param user l'utilisateur qu'on veut ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Utilisateur user) {
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
			ps = con.prepareStatement("INSERT INTO utilisateur(idutilisateur, password, type) VALUES(?, ?, ?)");
			ps.setInt(1, user.getIdUtilisateur());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getType());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant d'utilisateur existe déjà. Ajout impossible !");
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
	 * Permet de modifier un utilisateur dans la table étudiant.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param user l'utilisateur a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Utilisateur user) {
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
			ps = con.prepareStatement("UPDATE utilisateur set password = ?, type = ? WHERE idutilisateur = ?");
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getType());
			ps.setInt(3, user.getIdUtilisateur());

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
	 * Permet de supprimer un utilisateur par id dans la table utilisateur.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id de l'utilisateur à supprimer
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
			ps = con.prepareStatement("DELETE FROM utilisateur WHERE idutilisateur = ?");
			ps.setInt(1, id);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("suppression impossible !");
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
	 * Permet de recupérer un utilisateur par id dans la table utilisateur.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id de l'utilisateur à recupérer
	 * @return retourne l'utilisateur qu possede l'id recherché
	 */
	public Utilisateur get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Utilisateur returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur WHERE idutilisateur = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Utilisateur(rs.getInt("idutilisateur"),
					rs.getString("password"),
					rs.getString("type")
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
	 * Permet de recupérer la liste de tous les utilisateurs dans la table etudiant
	 * @return la liste de tous les utilisateurs par ordre croissant de l'id
	 */
	public ArrayList<Utilisateur> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Utilisateur> returnValue = new ArrayList<Utilisateur>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur ORDER BY idutilisateur");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Utilisateur(rs.getInt("idutilisateur"),
						rs.getString("password"),
						rs.getString("type")
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
	 * Methode de test pour mes différentes fonctions
	 */
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		UtilisateurDAO userDAO = new UtilisateurDAO();
		ArrayList<Utilisateur> list = userDAO.getList();
		
		for(Utilisateur user : list) {
			user.afficher();
			System.out.println("-------------------------");
		}
		

	}

}
