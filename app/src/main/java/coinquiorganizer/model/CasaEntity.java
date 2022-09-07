package coinquiorganizer.model;

public class CasaEntity {

    private String nomeCasa,domanda,risposta,groupWAPP;
    private Integer numeroCoinquilini;

    public CasaEntity(String nomeCasa,String domanda,String risposta,String groupWApp,Integer numeroCoinquilini){
        this.nomeCasa=nomeCasa;
        this.domanda=domanda;
        this.risposta=risposta;
        this.groupWAPP=groupWApp;
        this.numeroCoinquilini=numeroCoinquilini;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public Integer getNumeroCoinquilini() {
        return numeroCoinquilini;
    }

    public String getDomanda() {
        return domanda;
    }

    public String getRisposta() {
        return risposta;
    }

    public String getGroupWAPP() {
        return groupWAPP;
    }
}
