import javax.swing.*;

public class JeuMemory
{
    public static void main(String[] args)
    {
	//Tableau des thèmes disponibles
	GenerateurDeCartes[] themes = new GenerateurDeCartes[6];
	themes[0] = new GenerateurDeCartesCouleur();
	themes[1] = new GenerateurDeCartesMot("Lettres de A à Z", tabLettres());
	themes[2] = new GenerateurDeCartesMot("Mots Java", javaMotsTab);
	themes[3] = new GenerateurDeCartesImage("Images de Super Mario Bros.", tabImagesMario);
	themes[4] = new GenerateurDeCartesImage("Images de Marvel Comics", tabImagesMarvel);
	themes[5] = new GenerateurDeCartesMultiple("Mélange des thèmes 0 à " + (themes.length-2), themesMelanges(themes));
	
	
	try {
	    //Initialisation des valeurs
	    int nRangees = Integer.parseInt(args[0]);
	    int nColonnes = Integer.parseInt(args[1]);
	    int delaiAffichageInitial = Integer.parseInt(args[2]);
	    int delaiAffichageMauvaisePaire = Integer.parseInt(args[3]);
	    int numeroDeTheme = Integer.parseInt(args[4]);
	    int nombreDeCartes = nRangees * nColonnes;
		Carte[] tab = new Carte[nombreDeCartes];

	    //Vérifie si le nombre de cartes est pair
	    if(nombreDeCartes%2 != 0)
		{
		    JOptionPane.showMessageDialog(null, "Le nombre de cartes doit etre pair.\nNombre de cartes: nRangees x nColonnes = " + nombreDeCartes);
		    System.exit(0);
		}
	    
		//Initialiser le tableau de cartes (aviser si mauvais numéro de thème)
	    try {
			tab = themes[numeroDeTheme].generePairesDeCartesMelangees(nombreDeCartes/2);
			}
		catch (ArrayIndexOutOfBoundsException e2) {
			JOptionPane.showMessageDialog(null,"Numéro de thème invalide!\nLisez les instructions.");
			utilisation(themes);
			System.exit(0);
		}
	    
	    JFrame frame = new JFrame("Memory par Catherine Laprise et Farzin Faridfar");
	    
	    PanneauDeCartes panneau = new PanneauDeCartes(nRangees, nColonnes, tab, delaiAffichageInitial, delaiAffichageMauvaisePaire);
	    frame.add(panneau);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(500,500);
	    frame.setVisible(true);
	
	}

	catch (ArrayIndexOutOfBoundsException e) {
	    //S'il manque des paramètres dans la ligne de commande
	    JOptionPane.showMessageDialog(null,"Paramètres manquants!\nLisez les instructions.");
	    utilisation(themes);
	    System.exit(0);
	}

	catch (NumberFormatException e) {
	    //Si un des paramètres est invalide
	    JOptionPane.showMessageDialog(null,"Mauvais format de paramètres!\nLisez les instructions.");
	    utilisation(themes);
	    System.exit(0);
	}
    }

    /**
       Affiche les instructions d'utilisation et la liste des thèmes
       @param tabThemes tableau des thèmes
     */
    public static void utilisation(GenerateurDeCartes[] tabThemes){

	System.out.println("Utilisation: java JeuMemory nRangees nColonnes delaiAffichageInitial(ms) delaiAffichageMauvaisePaire(ms) numeroDeTheme");
	System.out.println("Ex: java JeuMemory 5 6 5000 1000 3");
	
	System.out.println("Voici la liste des themes disponibles:");
	for (int i = 0; i < tabThemes.length; i++) {
	    System.out.println(i + ": " + tabThemes[i].getNom());
		}
    }

    /**
       Créée un tableau avec les lettres de A à Z
       @return tableau de String contenant les lettres de A à Z
     */
    public static String[] tabLettres () {
	String[] tab = new String[26];
	for (int i = 0, c = 65; i < 26; i++, c++) {
	    tab[i] = ""+(char)c;
	}
	return tab;
    }

    //Tableau de mots relatifs à Java
    public static String[] javaMotsTab = {
	"Java", "Objet", "Classe", "Methode", "Static", "Cast", "Heritage", "Sous-classe", "Interface", "Polymorphisme"
    };

    //Tableau d'images de Super Mario Bros.
    public static String[] tabImagesMario = {
	"http://www.videogamesprites.net/SuperMarioBros1/Items/Super%20Mushroom.gif",
	"http://www.videogamesprites.net/SuperMarioBros1/Items/1-Up%20Mushroom.gif",
	"http://www.videogamesprites.net/SuperMarioBros1/Items/Bricks.gif",
	"http://www.videogamesprites.net/SuperMarioBros1/Characters/Mario/Mario.gif",
	"http://www.videogamesprites.net/SuperMarioBros1/Characters/Luigi/Luigi.gif",
	"http://www.videogamesprites.net/SuperMarioBros1/Scenery/Coin%20Heaven%20Cloud.gif",
	"http://www.videogamesprites.net/SuperMarioBros1/Enemies/Overworld/Green%20Koopa%20Troopa%20-%20Shell1.gif",
	"http://www.videogamesprites.net/SuperMarioBros1/Enemies/Overworld/Red%20Koopa%20Troopa%20-%20Shell1.gif"
	
    };

    //Tableau d'images de Marvel
    public static String[] tabImagesMarvel = {
	"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Ironman-Red-icon.png",
	"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Magneto-icon.png",
	"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Spiderwoman-icon.png",
	"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Captain-America-icon.png",
	"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Spiderman-Baby-icon.png",
	"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Logan-icon.png",
	"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Mask-icon.png",
	"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Thor-icon.png",
	"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Vision-icon.png",
	"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-War-Machine-icon.png"
    };

    /**
       Génère un tableau contenant tous les autres thèmes
       @param tab tableau des thèmes
       @return tableau de GenerateurDeCartes contenant tous les autres GenerateurDeCartes
     */
	public static GenerateurDeCartes[] themesMelanges (GenerateurDeCartes[] tab) {
	    GenerateurDeCartes[] tabResult = new GenerateurDeCartes[tab.length-1];
	    for (int i = 0; i < tabResult.length; i++) {
		tabResult[i] = tab[i];
	    }

	    return tabResult;
	}
    
}
