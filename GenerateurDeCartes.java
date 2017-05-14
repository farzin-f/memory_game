public abstract class GenerateurDeCartes
{
    private String nom;

    /**
       Récupère le nom du thème
       @return String contenant le nom du thème
     */
    public String getNom()
    {
	return this.nom;
    }

    /**
       Assigne un nom de thème à un GenerateurDeCartes
       @param nom nom du thème
     */
    public void setNom(String nom) {
	this.nom = nom;
    }

    /**
       Génère une nouvelle carte
       @return une carte
     */
    public abstract Carte genereUneCarte();

    /**
       Compte le nombre de cartes différentes dans un thème
       @return entier représentant le nombre de cartes différentes
     */
    public abstract int nombreDeCartesDifferentes();

    /**
       Génère un tableau de cartes aléatoires
       @param n nombre de cartes à générer
       @return tableau de cartes
     */
    public Carte[] genereCartes(int n)
    {

	Carte[] tab = new Carte[n];

	for (int i = 0; i < n; i++)
	    {
		//Évite les doublons si possible
		if (i > 0 && i < nombreDeCartesDifferentes()) {
		    //Génère une nouvelle carte si un double existe déjà
		    do {
			tab[i] = genereUneCarte();
		    } while (verifDoublons(tab[i], tab, i));
		   
		} else {
		    tab[i] = genereUneCarte();
		}
	    }

	return tab;
	
    }

    /**
       Génère un tableau de paires de cartes mélangées
       @param n nombre de paires
       @return tableau de 2n cartes mélangées
     */
    public Carte[] generePairesDeCartesMelangees(int n)
    {

	Carte[] tabMelange = new Carte[2*n];
	Carte[] tabDepart = genereCartes(n);

	for (int i = 0; i < n; i++)
	    {
	    tabMelange[i] = tabDepart[i];
	    //Créé une copie de chaque carte dans la 2e moitié du tableau
	    tabMelange[i+n] = (Carte)tabDepart[i].duplique();
	    }

	Carte.melangeCartes(tabMelange);
	return tabMelange;

    }

    /**
       Vérifie si une carte donnée est déjà dans un tableau entre l'index 0 et une position donnée
       @param c carte à chercher
       @param tab tableau où chercher la carte
       @param position index jusqu'auquel chercher
       @return boolean - true si la carte est déjà dans le tableau
     */
    public boolean verifDoublons(Carte c, Carte[] tab, int position) {
	 for (int i = 0; i < position; i++){
	     if (c.rectoIdentique(tab[i])) {
		 return true;
	     }
	 }
	 return false;
    }

    
}
