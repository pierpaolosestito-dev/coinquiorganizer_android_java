package coinquiorganizer.model;


public class SplitcashEntity {
    private String nomeCasa,coinquilinoDonatore,motivazione,dataVersamento;
    private String orario;
    private Integer somma;

    public SplitcashEntity(String nomeCasa,String coinquilinoDonatore,Integer somma, String motivazione,String dataVersamento,String orario){
        this.nomeCasa = nomeCasa;
        this.coinquilinoDonatore = coinquilinoDonatore;
        this.somma = somma;
        this.motivazione = motivazione;
        this.dataVersamento = dataVersamento;
        this.orario=orario;
    }

    public void setCoinquilinoDonatore(String coinquilinoDonatore) {
        this.coinquilinoDonatore = coinquilinoDonatore;
    }

    public String getOrario() {
        return orario;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public String getCoinquilinoDonatore() {
        return coinquilinoDonatore;
    }

    public Integer getSomma() {
        return somma;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public String getDataVersamento() {
        return dataVersamento;
    }
}
