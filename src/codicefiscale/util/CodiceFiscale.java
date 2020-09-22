package codicefiscale.util;

import codicefiscale.dao.ComuneDAO;

/**
 * Classe che genera il codice fiscale
 * @author Micael Vanini
 */
public class CodiceFiscale {
    private String nome;
    private String cognome;
    private String data;
    private String comune;
    private char codiceControllo;
    
    public CodiceFiscale() {
        this.nome = "";
        this.cognome = "";
        this.data = "";
        this.comune = "";
    }
    
    public String nome(String nome){
        int nomeCounter = 0;
        for(int i = 0; i < nome.length(); i++){
            if(nomeCounter == 3) break;
            if(this.isConsonanate(nome.charAt(i))){
                this.nome = this.nome + nome.charAt(i);
                nomeCounter++;
            }
        }
        
        if(nomeCounter < 3) {
            for(int i = 0; i < nome.length(); i++){
                if(nomeCounter == 3) break;
                if(this.isVocale(nome.charAt(i))){
                    this.nome = this.nome + nome.charAt(i);
                    nomeCounter++;
                }
            } 
        }
        
        if(nomeCounter < 3) {
            for(int i = nomeCounter; i < 3; i++){
                this.nome = this.nome + "X";
            }
        }
        
        return this.nome;
    }
    
    public String cognome(String cognome) {
        int cognomeCounter = 0;
        for(int i = 0; i < cognome.length(); i++){
            if(cognomeCounter == 3) break;
            if(this.isConsonanate(cognome.charAt(i))){
                this.cognome = this.cognome + cognome.charAt(i);
                cognomeCounter++;
            }
        }
        
        if(cognomeCounter < 3) {
            for(int i = 0; i < cognome.length(); i++){
                if(cognomeCounter == 3) break;
                if(this.isVocale(cognome.charAt(i))){
                    this.cognome = this.cognome + cognome.charAt(i);
                    cognomeCounter++;
                }
            } 
        }
        
        if(cognomeCounter < 3) {
            for(int i = cognomeCounter; i < 3; i++){
                this.cognome = this.cognome + "X";
            }
        }
        
        return this.cognome;
    }
    
    public String data(String giorno, String mese, String anni, String sesso) {
        this.data = this.data + anni.substring(2) + this.codiceMese(mese);
        
        if("M".equals(sesso)){
            if(giorno.length()<2) this.data = this.data + "0" + giorno;
            else this.data = this.data + giorno;
        } else this.data = this.data + (Integer.parseInt(giorno)+40);
        
        return this.data;
    }
    
    public String comune(String comune) {
        ComuneDAO comuneDao = new ComuneDAO();
        return comuneDao.readFromSigla(comune);
    }
    
    public String codiceControllo(String codiceFiscale) {
        int risultato = 0;
        for(int i = 0; i < codiceFiscale.length(); i++){
            if(i%2 != 0){
                risultato = risultato + this.pari(codiceFiscale.charAt(i));
            } else {
                risultato = risultato + this.dispari(codiceFiscale.charAt(i));
            }
        }
        this.codiceControllo = this.conversioneRisultato(risultato%26);
        System.out.println(risultato);
        return codiceFiscale + this.codiceControllo;
    }
    
    private int pari(char carattere) {
        char[] pari = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int[] pariInt = {0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        for(int i = 0; i < pari.length; i++) {
            if(carattere == pari[i]) return pariInt[i];
        }
        return 0;
    }
    
    private int dispari(char carattere) {
        char[] dispari = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int[] dispariInt = {1,0,5,7,9,13,15,17,19,21,1,0,5,7,9,13,15,17,19,21,2,4,18,20,11,3,6,8,12,14,16,10,22,25,24,23};
        for(int i = 0; i < dispari.length; i++) {
            if(carattere == dispari[i]) return dispariInt[i];
        }
        return 0;
    }
    
    private char conversioneRisultato(int resto) {
        int[] numeri = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        char[] caratteri = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for(int i = 0; i < numeri.length; i++) {
            if(resto == numeri[i]) return caratteri[i];
        }
        return 0;
    }
    
    private boolean isVocale(char lettera){
        char[] vocali = {'A','E','I','O','U'};
        for(int i = 0; i < vocali.length; i++) {
            if(lettera == vocali[i]) return true;
        }
        return false;
    }
    
    private boolean isConsonanate(char lettera) {
        char[] consonanti = {'B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z'};
        for(int i = 0; i < consonanti.length; i++) {
            if(lettera == consonanti[i]) return true;
        }
        return false;
    }
    
    private String codiceMese(String mese) {
        String letteraMese[][] = {
            {"GENNAIO","FEBBRAIO","MARZO","APRILE","MAGGIO","GIUGNO","LUGLIO","AGOSTO","SETTEMBRE","OTTOBRE","NOVEMBRE","DICEMBRE"},
            {"A","B","C","D","E","H","L","M","P","R","S","T"}
        };
        String lettera = "";
        
        for(int i = 0; i < letteraMese[0].length; i++){
            if(letteraMese[0][i].equals(mese)) return letteraMese[1][i];
        }
        
        return lettera;
    }
}
