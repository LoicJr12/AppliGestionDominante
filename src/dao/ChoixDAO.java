 package dao;

import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table choix
 * 
 * @author KAKOU
 * @version 1.0
 * */
public class ChoixDAO extends ConnectionDAO {
	
	/**
	 * Constructor
	 */
	public ChoixDAO() {
		super();
	}
	
	/**
	 * Permet d'ajouter un choix dans la table choix.
	 * Le mode est auto-commit par defaut : chaque insertion est validee.
	 * 
	 * @param choix le choix a ajouter 
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Choix choix) {
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
			ps = con.prepareStatement("INSERT INTO choix(choix_id_etudiant, choix_id_dominante, numeroDordre, validation) VALUES(?, ?, ?, ?)");
			ps.setInt(1, choix.getChoixIdEtudiant());
			ps.setInt(2, choix.getChoixIdDominante());
			ps.setInt(3, choix.getNumeroOrdre());
			ps.setString(4, choix.getValidation());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de fournisseur existe déjà. Ajout impossible !");
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
	 * Permet de recupérer un etudiant par id dans la table etudiant.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param idEtu id de l'etudiant
	 * @param idDom id de la dominate
	 * @return retourne le choix
	 */
	public Choix get(int idEtu, int idDom) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Choix returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM choix WHERE choix_id_etudiant = ? AND choix_id_dominante = ?");
			ps.setInt(1, idEtu);
			ps.setInt(2, idDom);
			
			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Choix(rs.getInt("choix_id_etudiant"),
					rs.getInt("choix_id_dominante"),
					rs.getInt("numeroDordre"),
					rs.getString("validation")
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
	 * Permet de recupérer la liste des choix d'un etudiant dans la table choix
	 * a partir de l'id etudiant
	 * 
	 * @param idEtu l'id de l'étudiant
	 * @return la liste des choix d'un étudiant
	 */
	/*public ArrayList<Choix> getListChoixEtudiant(int idEtudiant) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Choix> returnValue = new ArrayList<Choix>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM choix INNER JOIN dominante ON choix.choix_id_dominante = dominante.iddominante"
					+ " WHERE choix_id_etudiant = ? ORDER BY numerodordre"); //ASC facultatif
			ps.setInt(1, idEtudiant);

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Choix(rs.getInt("choix_id_etudiant"),
						rs.getInt("numeroDordre"),
						rs.getString("validation"),
						new Dominante(rs.getInt("choix_id_dominante"), 
								rs.getString("nom"),
								rs.getString("sigle")
						)
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
	}*/
	
	/**
	 * Permet de recupérer la liste des choix des etudiants dans la table choix
	 * a partir de l'id d'une dominate
	 * 
	 * @param idDom l'id de la dominante
	 * @return la liste de choix des etudiants pour une dominante
	 */
	public ArrayList<Choix> getListChoixDominante(int idDom) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Choix> returnValue = new ArrayList<Choix>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM choix ORDER BY choix_id_dominante ASC"); //ASC facultatif

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Choix(rs.getInt("choix_id_etudiant"),
						rs.getInt("choix_id_dominante"),
						rs.getInt("numeroDordre"),
						rs.getString("validation")
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
	 * Permet de recupérer la liste de tous les choix dans la table choix
	 * @return list des choix
	 */
	public ArrayList<Choix> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Choix> returnValue = new ArrayList<Choix>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM choix ORDER BY choix_id_etudiant ASC, numeroDordre ASC ");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Choix(rs.getInt("choix_id_etudiant"),
						rs.getInt("choix_id_dominante"),
						rs.getInt("numeroDordre"),
						rs.getString("validation")
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
	 * Permet de récupérer la liste des choix d'un étudiant dans la table choix a partir de son id
	 * @param idEtudiant
	 * @return ArrayList<Choix>
	 */
	public ArrayList<Choix> getChoixByIdEtudiant(int idEtudiant){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Choix> returnValue = new ArrayList<Choix>();
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM choix WHERE choix_id_etudiant = ? ORDER BY numeroDordre ASC ");
			ps.setInt(1, idEtudiant);

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
						returnValue.add(new Choix(rs.getInt("choix_id_etudiant"),
								rs.getInt("choix_id_dominante"),
								rs.getInt("numeroDordre"),
								rs.getString("validation")
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
	 * Permet de modifier un choix dans la table choix.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param choix le choix a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Choix choix) {
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
			ps = con.prepareStatement("UPDATE choix set numeroDordre = ?, validation = ? WHERE choix_id_etudiant = ? AND choix_id_dominante = ?");
			ps.setInt(1, choix.getNumeroOrdre());
			ps.setString(2, choix.getValidation());
			ps.setInt(3, choix.getChoixIdEtudiant());
			ps.setInt(4, choix.getChoixIdDominante());

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
	 * Permet de supprimer un choix par id dans la table choix.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param idEtu id etudiant qui constitue la clé primaire du choix
	 * @param idDom id dominante qui constitue la clé primaire du choix
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	public int delete(int idEtu, int idDom) {
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
			ps = con.prepareStatement("DELETE FROM choix WHERE choix_id_etudiant = ? AND choix_id_dominante = ?");
			ps.setInt(1, idEtu);
			ps.setInt(2, idDom);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Suppression du choix impossible !");
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
	 * Methode de test des requetes sql
	 * @param args
	 * @throws SQLException si une erreur se produit lors de la communication avec la BDD
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		ChoixDAO choixDAO = new ChoixDAO();
		//Choix choix = new Choix(12056, 1, 1, "en attente");
		//Choix choix2 = new Choix(12056, 10, 2, "en attente");
		//int resultat = choixDAO.add(choix);
		ArrayList<Choix> list = choixDAO.getList() ;
		for(Choix c : list) {
			c.afficher();
			System.out.println("-----------------------");
		}
		//System.out.println(resultat);
		

	}

}
