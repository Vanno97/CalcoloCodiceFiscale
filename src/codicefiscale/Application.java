package codicefiscale;

import codicefiscale.util.CodiceFiscale;
import java.util.Scanner;

/**
 * Programma per la generazione del Codice Fiscale
 * @author Micael Vanini
 */
public class Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il nome");
        String nome = scanner.next();
        System.out.println("Inserisci il cognome");
        String cognome = scanner.next();
        System.out.println("Inserisci giorno di nascita");
        String giorno = scanner.next();
        System.out.println("Inserisci mese di nascita");
        String mese = scanner.next();
        System.out.println("Inserisci anno di nascita");
        String anno = scanner.next();
        System.out.println("inserisci il sesso");
        String sesso = scanner.next();
        System.out.println("Inserisci comune di nascita");
        String comune = scanner.next();
        CodiceFiscale cf = new CodiceFiscale();
        String codiceFiscale = cf.codiceControllo(
                        cf.cognome(cognome.toUpperCase()) +
                        cf.nome(nome.toUpperCase()) 
                        +
                        cf.data(
                                giorno,
                                mese.toUpperCase(),
                                anno,
                                sesso.toUpperCase()).toUpperCase()+
                        cf.comune(comune.toUpperCase()));
        System.out.println(codiceFiscale);
    }
    
}
