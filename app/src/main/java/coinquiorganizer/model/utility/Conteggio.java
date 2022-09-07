package coinquiorganizer.model.utility;

public class Conteggio {
    private String coinquilino;
    private Integer somma;

    public Conteggio(String coinquilino,Integer somma){
        this.coinquilino=coinquilino;
        this.somma = somma;
    }

    public String getCoinquilino() {
        return coinquilino;
    }

    public Integer getSomma() {
        return somma;
    }
}
