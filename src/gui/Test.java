package gui;

import java.util.HashSet;

public class Test {
	
	public static boolean hasDuplicates(String s1, String s2, String s3, String s4, String s5) {
        HashSet<String> uniqueValues = new HashSet<>();

        // Ajouter chaque valeur à l'ensemble
        uniqueValues.add(s1);
        uniqueValues.add(s2);
        uniqueValues.add(s3);
        uniqueValues.add(s4);
        uniqueValues.add(s5);

        // Si la taille de l'ensemble est inférieure à 5, cela signifie qu'il y a des doublons
        return uniqueValues.size() == 5;
    }

	public static void main(String[] args) {
	        /*// Exemple d'utilisation
	        String s1 = "apple";
	        String s2 = "banana";
	        String s3 = "cherry";
	        String s4 = "date";
	        String s5 = "oil"; // Doublon

	        boolean result = hasDuplicates(s1, s2, s3, s4, s5);
	        System.out.println("Y a-t-il des doublons ? " + result); // Cela imprimera "true"*/
		
		int resultat = 0 ;
		System.out.println("La valeur de résultat : " + resultat);
		
	}

}
