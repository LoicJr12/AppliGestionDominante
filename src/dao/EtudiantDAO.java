package dao;

import java.sql.*;
import java.util.ArrayList;
import model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe d'acces aux donnees contenues dans la table etudiant
 * 
 * @author KAKOU
 * @version 1.0
 * */
public class EtudiantDAO extends ConnectionDAO{
	
	/**
	 * Constructor
	 */
	public EtudiantDAO() {
		super();
	}
	
	
	/**
	 * Permet d'ajouter un etudiant dans la table etudiant.
	 * Le mode est auto-commit par defaut : chaque insertion est validee.
	 * 
	 * @param etudiant l'etudiant qu'on veut ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Etudiant etudiant) {
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
			ps = con.prepareStatement("INSERT INTO etudiant(idetudiant, etudiant_id_utilisateur, etudiant_id_promotion, nom, prenom, datenaissance, "
					+ "classement1A, filiere) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, etudiant.getIdEtudiant());
			if(etudiant.getEtuIdUtisateur() != 0)
				ps.setInt(2, etudiant.getEtuIdUtisateur());
			else
				ps.setString(2, null);
			ps.setInt(3, etudiant.getEtuIdPromotion());
			ps.setString(4, etudiant.getNom());
			ps.setString(5, etudiant.getPrenom());
			ps.setString(6, etudiant.getDateNaissance());
			ps.setInt(7, etudiant.getClassement1A());
			ps.setString(8, etudiant.getFiliere());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant d'etudiant existe déjà. Ajout impossible !");
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
	 * Permet de modifier un etudiant dans la table étudiant.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param etudiant l'etudiant a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Etudiant etudiant) {
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
			ps = con.prepareStatement("UPDATE etudiant set etudiant_id_utilisateur = ?, etudiant_id_promotion = ?, nom = ?, prenom = ?, datenaissance = ?, "
					+ "classement1A = ?, filiere = ? WHERE idetudiant = ?");
			ps.setInt(8, etudiant.getIdEtudiant());
			ps.setInt(1, etudiant.getEtuIdUtisateur());
			ps.setInt(2, etudiant.getEtuIdPromotion());
			ps.setString(3, etudiant.getNom());
			ps.setString(4, etudiant.getPrenom());
			ps.setString(5, etudiant.getDateNaissance());
			ps.setInt(6, etudiant.getClassement1A());
			ps.setString(7, etudiant.getFiliere());

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
	 * Permet de supprimer un etudiant par id dans la table etudiant.
	 * Si ce dernier possede des choix, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id de l'etudiant à supprimer
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
			ps = con.prepareStatement("DELETE FROM etudiant WHERE idetudiant = ?");
			ps.setInt(1, id);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Ce etudiant a fait des choix, suppression impossible !"
						         + " Supprimer d'abord ses choix .");
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
	 * @param id l'id de l'etudiant à recupérer
	 * @return retourne l'etudiant qu possede l'id 
	 */
	public Etudiant get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Etudiant returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant WHERE idetudiant = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Etudiant(rs.getInt("idetudiant"),
					rs.getInt("etudiant_id_utilisateur"),
					rs.getInt("etudiant_id_promotion"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("datenaissance"),
					rs.getInt("classement1A"),
					rs.getString("filiere")
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
	 * Permet de recupérer un etudiant par son id utilisateur dans la table etudiant.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id de l'utilisateur à recupérer
	 * @return retourne l'etudiant qu possede ce id utilisateur 
	 */
	public Etudiant getByIdUser(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Etudiant returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant WHERE etudiant_id_utilisateur = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Etudiant(rs.getInt("idetudiant"),
					rs.getInt("etudiant_id_utilisateur"),
					rs.getInt("etudiant_id_promotion"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("datenaissance"),
					rs.getInt("classement1A"),
					rs.getString("filiere")
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
	 * Permet de recupérer la liste de tous les etudiants dans la table etudiant
	 * @return la liste de tous les etudiants par ordre croissant de l'id
	 */
	public ArrayList<Etudiant> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Etudiant> returnValue = new ArrayList<Etudiant>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant ORDER BY idetudiant");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Etudiant(rs.getInt("idetudiant"),
						rs.getInt("etudiant_id_utilisateur"),
						rs.getInt("etudiant_id_promotion"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("datenaissance"),
						rs.getInt("classement1A"),
						rs.getString("filiere")
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
	
	
	public static String formatDate(String dateString) {
        // Définir le format d'entrée
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        // Analyser la chaîne en LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateString, inputFormatter);

        // Définir le format de sortie
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Formater la date et retourner le résultat
        return dateTime.format(outputFormatter);
    }
	
	/**
	 * Methode de test pour mes différentes fonctions
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		EtudiantDAO etu = new EtudiantDAO();
		//Etudiant etudiant = etu.get(12064, 654823);
		//etudiant.afficher();
		//System.out.println("---------------------------");
		//etudiant.setDateNaissance(formatDate(etudiant.getDateNaissance()));
		//etudiant.setPrenom("Esaïe");
		//etudiant.afficher();
		//int returnValue = etu.update(etudiant);
		//System.out.println(returnValue + "mise à jour");
		ArrayList<Etudiant> list = etu.getList();
		for(Etudiant e : list) {
			e.afficher();
			System.out.println("---------------------------");
		}
		

	}

}
