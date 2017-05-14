import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.*;

public class PanneauDeCartes extends JPanel
{

    protected int rangee;
    protected int colonne;
    protected final Carte[] cartes;
    protected int nombreDeCartes;
    protected int delaiInitial;
    protected int delaiPaire;
    protected SourisAction clique;

    /**
       Constructeur de PanneauDeCartes
       @param nRangees nombre de rangées
       @param nColonnes nombre de colonnes
       @param cartes tableau de cartes à utiliser
       @param delaiAffichageInitial délai (en ms) pendant lequel les cartes sont montrées au début du jeu
       @param delaiAffichageMauvaisePaire délai (en ms) pendant lequel deux cartes non identiques sont montrées avant d'être retournées
    */
    public PanneauDeCartes(int nRangees, int nColonnes, final Carte[] cartes,
			   int delaiAffichageInitial,
			   int delaiAffichageMauvaisePaire)
    {	
	this.rangee = nRangees;
	this.colonne = nColonnes;
	this.cartes = cartes;
	this.nombreDeCartes = cartes.length;
	this.delaiInitial = delaiAffichageInitial;
	this.delaiPaire = delaiAffichageMauvaisePaire;
	this.clique = new SourisAction();

	//Layout manager
	GridLayout layout = new GridLayout(this.rangee, this.colonne);
	layout.setHgap(10);
	layout.setVgap(10);
	this.setLayout(layout);

	//Insére les cartes dans le layout manager
	for(int i=0; i < cartes.length; ++i)
	    {
		cartes[i].montre();
		this.add(cartes[i]);
	    }
	
	//Cache toutes les cartes après le délai initial
	ActionListener affichageCache = new ActionListener()
	    {
		public void actionPerformed(ActionEvent e)
		{
	
		    for(int i=0; i < cartes.length; ++i)
			{
			    cartes[i].cache();
			    cartes[i].addMouseListener(clique);
			}
		}		        
	    };

	Timer timer = new Timer(this.delaiInitial, affichageCache);
	timer.start();
	timer.setRepeats(false);

    }
	
    /**
       Enlève le MouseListener de toutes les cartes du panneau
    */
    public void enleverListener() {
	for(int i=0; i < cartes.length; ++i)
	    {
		cartes[i].removeMouseListener(clique);
	    }
    }
	
    /**
       Ajoute le MouseListener à toutes les cartes du panneau
    */
    public void ajouterListener() {
	for(int i=0; i < cartes.length; ++i)
	    {
		cartes[i].addMouseListener(clique);
	    }
    }
    
    //Classe imbriquée - réaction aux clics de souris
    public class SourisAction extends MouseAdapter
    {
	private int compteur = 0;
	private int coups = 0;
	private Carte premiereCarte;

	/**
	   Redéfinition des méthodes de MouseAdapter
	   @param e MouseEvent
	*/
	public void mouseReleased (MouseEvent e)
	{
	    mouseActionPerformed(e);
	}
	

	/**
	   Redéfinition des méthodes de MouseAdapter
	   @param e MouseEvent
	*/
	public void mouseClicked (MouseEvent e)
	{
	    mouseActionPerformed(e);
	}


	/**
	   Réaction aux clics de souris
	   @param e MouseEvent
	*/
	public void mouseActionPerformed(MouseEvent e)
	{
	    final Carte carteCliquee = (Carte)e.getSource();
	    
	    if(carteCliquee.estCachee())
		{
		    carteCliquee.retourne();
		    //Si la carte est la première à être retournée, la garder en mémoire
		    if (compteur == 0)
			{
			    premiereCarte = carteCliquee;
			    ++compteur;
			}
		    //Si une carte est déjà retournée, comparer les deux cartes
		    else if(compteur == 1)
			{
			    if (carteCliquee.rectoIdentique(premiereCarte))
				{
				    nombreDeCartes -= 2;
				    coups++;
				    if(nombreDeCartes == 0)
					{
					    //Fin la partie si plus de cartes cachées
					    System.out.println("Fin de la partie");
					    JOptionPane.showMessageDialog(null, "Bravo! Vous avez réussi en " + coups + " coups." );
					}
				    compteur = 0;
				}
			    else
				{
				    //Si la paire n'est pas identique, empêcher les clics
				    PanneauDeCartes.this.enleverListener();
					
				    //Retourner les cartes après le délaiPaire et remettre les clics
				    ActionListener affichagePaire = new ActionListener()
					{
					    public void actionPerformed(ActionEvent e)
					    {
						premiereCarte.retourne();
						carteCliquee.retourne();
						coups++;
						compteur = 0;
						PanneauDeCartes.this.ajouterListener();
					    }
					};

				    Timer timer = new Timer(delaiPaire, affichagePaire);
				    timer.start();
				    timer.setRepeats(false);
				    				    
				}
			}
		
		}
	}
    }
}
    
