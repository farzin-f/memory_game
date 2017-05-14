import javax.swing.*;
import java.awt.*;
import java.net.*;

public class CarteImage extends Carte {

    private String imageURL;
    
    /**
       Constructeur
       @param recto détermine si le recto de la carte est montré
       @param imageURL url de l'image à afficher au recto de la carte
    */
    protected CarteImage (boolean recto, String imageURL) {
	super(recto);
	this.imageURL = imageURL;
	
    }

    /**
       Constructeur - créée une copie d'une carte existante
       @param c carte à copier
    */
    protected CarteImage (CarteImage c) {
	super(c);
	this.imageURL = c.imageURL;
    }

    /**
       Dessine le recto de la carte (image)
       @param g contexte graphique de la carte
    */
    public void paintRecto (Graphics2D g) {
	int hauteur = this.getHeight();
	int largeur = this.getWidth();
	int minDimension = Math.min(hauteur, largeur);
	int imageSize = minDimension/2;
	Image image = createImageIconFromURLString(imageURL).getImage();

	g.drawImage(image, largeur/2-imageSize/2, hauteur/2-imageSize/2, imageSize, imageSize, this);
    }

    /**
       Vérifie si deux cartes sont identiques
       @param c carte à comparer
     */
    public boolean rectoIdentique (Carte c) {
	try{
	    return this.imageURL == ((CarteImage)c).imageURL;
	}
	//Si les cartes ne sont pas du même type, retourner false
	catch (ClassCastException e) {
	    return false;
	}
    }

    /**
       Créée une copie d'un objet en utilisant le constructeur de copie
       @return objet identique
    */
    public Object duplique () {
	CarteImage c = new CarteImage(this);
	return c;
    }

    /**
       Redéfinition de toString
       @return String contenant l'état de la carte et l'adresse de son image
     */
    public String toString () {
        return ("montree:" + estMontree() + "image: " + imageURL);
    }

    /**
       Génère une image à partir d'un url
       @param img_urlstring adresse de l'image
       @return objet ImageIcon contenant l'image à afficher
     */
    public static ImageIcon createImageIconFromURLString(String img_urlstring)
    {
        URL img_url = null;
        try { img_url = new URL(img_urlstring); }
        catch(MalformedURLException e)
        {
            try {img_url = new URL("https://"+"webtoolfeed.files.wordpress.com/2012/01/create-your-url1.jpg");}
            catch(MalformedURLException e2) {}
        }
        ImageIcon img_icon = new ImageIcon(img_url);
        return img_icon;
    }
}
