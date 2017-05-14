import java.util.Random;

public class GenerateurDeCartesMultiple extends GenerateurDeCartes {

    public GenerateurDeCartes[] tabThemes;

    /**
       Constructeur
       @param theme nom du thème
       @param tab tableau des thèmes à utiliser
    */
    public GenerateurDeCartesMultiple (String theme, GenerateurDeCartes[] tab) {

	setNom(theme);
	this.tabThemes = tab;
	
    }

    /**
       Génère une nouvelle carte
       @return une carte
     */
    public Carte genereUneCarte() {

	Random rand = new Random();
	Random rand2 = new Random();
	//Choisi un thème aléatoirement
	GenerateurDeCartes themeAleatoire = tabThemes[rand.nextInt(tabThemes.length)];
	Carte c = themeAleatoire.genereUneCarte();
	return c;	
    }

    /**
       Compte le nombre de cartes différentes dans un thème
       @return entier représentant le nombre de cartes différentes
    */
    public int nombreDeCartesDifferentes() {

	int result = 0;
	
	for (int i = 0; i < tabThemes.length; i++) {
	    result += tabThemes[i].nombreDeCartesDifferentes();
	}

	return result;
    }
}
