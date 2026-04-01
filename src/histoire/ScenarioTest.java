package histoire;

import villagegaulois.Etal;
import villagegaulois.Village;
import personnages.Gaulois;

public class ScenarioTest {
	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();	
		System.out.println("Fin test libererEtal");
		
		Gaulois acheteur = new Gaulois("Acheteur", 25);		
		Gaulois vendeur = new Gaulois("Vendeur", 25);
		etal.occuperEtal(vendeur, "fleurs", 0);
		System.out.println(etal.acheterProduit(12, null));
		System.out.println("Fin test AcheterProduit AcheteurNull");
		
		try {
			etal.acheterProduit(0, acheteur);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("Fin test AcheterProduit Quantite Illicite");
		
		Etal etal2 = new Etal();
		try {
			etal2.acheterProduit(10, acheteur);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		Village village = new Village("le village des irréductibles", 10, 5);
		village.ajouterHabitant(acheteur);
		try {
			System.out.println(village.afficherVillageois());
		} catch (VillageSansChefException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
	}
}
