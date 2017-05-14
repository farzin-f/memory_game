import java.util.Random;
import java.awt.Color;

public class GenerateurDeCartesCouleur extends GenerateurDeCartes
{

    /**
       Constructeur
     */
    public GenerateurDeCartesCouleur(){
	setNom("Cartes de couleur");
	
    }

    //Tableau des couleurs possibles
    private Color[] tabCouleurs = {
	Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.PINK, Color.ORANGE
    };

    /**
       Génère une nouvelle carte
       @return une carte
     */
    public Carte genereUneCarte()
    {
	Random rand = new Random();
	Carte c = new CarteCouleur(false, this.tabCouleurs
				   [rand.nextInt(this.nombreDeCartesDifferentes())]);
	
	return c;
    }

    /**
       Compte le nombre de cartes différentes dans un thème
       @return entier représentant le nombre de cartes différentes
    */
    public int nombreDeCartesDifferentes()
    {
	return this.tabCouleurs.length;
    }
}
