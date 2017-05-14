import java.util.Random;

public class GenerateurDeCartesImage extends GenerateurDeCartes {

    public String[] tabImages;
	    
    /**
       Constructeur
       @param theme nom du thème
       @param tab tableau d'images à utiliser
    */
    public GenerateurDeCartesImage (String theme, String[] tab)
    {
	setNom(theme);
	this.tabImages = tab;
	
    }
    
    /**
       Génère une nouvelle carte
       @return une carte
    */
    public Carte genereUneCarte()
    {
	Random rand = new Random();
	Carte c = new CarteImage(false, this.tabImages[rand.nextInt(this.tabImages.length)]);
	return c;
    }

    /**
       Compte le nombre de cartes différentes dans un thème
       @return entier représentant le nombre de cartes différentes
    */
    public int nombreDeCartesDifferentes()
    {
	return this.tabImages.length;
    }
}
