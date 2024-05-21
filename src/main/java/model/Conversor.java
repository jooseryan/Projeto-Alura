package model;

public class Conversor {

    public static String conectarUrl(String urlBase, String codBase, String codAlvo){
        return urlBase + codBase + "/" + codAlvo;
    }

    public static double converterValor(TaxaConversao taxa, double valor){
        double valorTaxa = Double.parseDouble(taxa.conversion_rate());
        return valorTaxa * valor;
    }

}
