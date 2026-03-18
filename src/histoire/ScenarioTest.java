package histoire;

import villagegaulois.Etal;
import personnages.Gaulois;


public class ScenarioTest {
	public static void main(String[] args) {
		Etal etal = new Etal();
		try {
			etal.libererEtal();	
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		Gaulois vendeur = new Gaulois("Vendeur", 20);
		etal.occuperEtal(vendeur, "Fleur", 0);
		System.out.println(etal.acheterProduit(12, null));
		
		Gaulois acheteur = new Gaulois("Acheteur", 25);
		try {
			etal.acheterProduit(-1, acheteur);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		Etal etal2 = new Etal();
		try {
			etal2.acheterProduit(10, acheteur);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin du test");
	}
}
