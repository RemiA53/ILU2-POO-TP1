package histoire;

import villagegaulois.Etal;
import villagegaulois.Village;
import personnages.Gaulois;

public class ScenarioTest {
	public static void main(String[] args) {
		System.out.println("Test libererEtal");
		Etal etal = new Etal();
		etal.libererEtal();	
		System.out.println("Fin test libererEtal");
		
		System.out.println("Test AcheterProduit EtalVide");
		Gaulois acheteur = new Gaulois("Acheteur", 25);	
		try {
			etal.acheterProduit(0, acheteur);
		}catch (IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("Fin test AcheterProduit EtalVide");
		
		System.out.println("Test AcheterProduit AcheteurNull");
		Gaulois vendeur = new Gaulois("Vendeur", 25);
		etal.occuperEtal(vendeur, "fleurs", 0);
		System.out.println(etal.acheterProduit(12, null));
		System.out.println("Fin test AcheterProduit AcheteurNull");
		
		System.out.println("Test AcheterProduit Quantite Illicite");
		try {
			etal.acheterProduit(0, acheteur);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("Fin test AcheterProduit Quantite Illicite");
				
		
		System.out.println("Test VillageSansChef");
		Village village = new Village("le village des irréductibles", 10, 5);
		village.ajouterHabitant(acheteur);
		try {
			System.out.println(village.afficherVillageois());
		} catch (VillageSansChefException e) {
			e.printStackTrace();
		}
		System.out.println("Fin test VillageSansChef\n");
		System.out.println("Fin du test");
	}
}
