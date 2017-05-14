import java.util.Random;

public class GenerateurDeCartesMot extends GenerateurDeCartes
{
    public String[] tabMots;

    /**
       Constructeur
       @param theme nom du thème
       @param tab tableau de mots à utiliser
    */
    public GenerateurDeCartesMot (String theme, String[] tab)
    {
	setNom(theme);
	this.tabMots = tab;
	
    }

    /**
       Génère une nouvelle carte
       @return une carte
     */
    public Carte genereUneCarte()
    {
	Random rand = new Random();
	Carte c = new CarteMot(false, this.tabMots[rand.nextInt(this.tabMots.length)]);
	return c;
    }

    /**
       Compte le nombre de cartes différentes dans un thème
       @return entier représentant le nombre de cartes différentes
    */
    public int nombreDeCartesDifferentes()
    {
	return this.tabMots.length;
    }
}
