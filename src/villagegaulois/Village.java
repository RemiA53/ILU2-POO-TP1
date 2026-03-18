package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	private static class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtal) {
			etals = new Etal[nbEtal];
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			Etal etal = new Etal();
			etal.occuperEtal(vendeur,produit,nbProduit);
			etals[indiceEtal] = etal;
		}
		
		public int trouverEtalLibre() {
		    for (int i=0;i<etals.length;i++) {    
			if (etals[i] == null || !etals[i].isEtalOccupe()) {
		            return i;
		        }
		    }
		    return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			int tailleEtalsProduit = 0;
			for (int i=0;i<etals.length;i++) {
				if (etals[i]!=null && etals[i].contientProduit(produit)) {
					tailleEtalsProduit++;
				}
			}
			Etal[] etalsProduit = new Etal[tailleEtalsProduit];
			
			int index = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i]!=null && etals[i].contientProduit(produit)) {
					etalsProduit[index] = etals[i];
					index++;
				}
			}
			return etalsProduit;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int i=0;
			while (i<etals.length && etals[i].isEtalOccupe()) {
				chaine.append(etals[i].afficherEtal());
				i++;
			}
			int nbEtalsLibres = etals.length - i;
			if (nbEtalsLibres!=0) {
				chaine.append("Il reste "+nbEtalsLibres+" �tals non utilis�s dans le march�.\n");
			}
			return chaine.toString();
		}
	}
	
	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre "+nbProduit+" "+produit+".\n");
		int numEtal = marche.trouverEtalLibre();
		if(numEtal!=-1) {
			marche.utiliserEtal(numEtal, vendeur, produit, nbProduit);
			numEtal++;
			chaine.append("Le vendeur "+vendeur.getNom()+" vend des fleurs à l'étal n°"+numEtal+".\n");
		}
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		if (etalsProduit.length==0) {
			chaine.append("Il n'y a pas de vendeur qui propose des "+produit+" au marché.\n");
		} else if (etalsProduit.length==1) {
			chaine.append("Seul le vendeur "+etalsProduit[0].getVendeur().getNom()+" propose des "+produit+" au marché.\n");
		} else {
			chaine.append("Les vendeurs qui proposent des "+produit+" sont :\n");
			for (int i=0;i<etalsProduit.length;i++) {
				chaine.append("- "+etalsProduit[i].getVendeur().getNom()+"\n");
			}
		}
		return chaine.toString();
	}
}