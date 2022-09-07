package coinquiorganizer.model;

public class CronologiaSplitEntity {
    private String nomeCasa,dataInizio,dataFine,situazioneSplit;

    public CronologiaSplitEntity(String nomeCasa,String dataInizio,String dataFine,String situazioneSplit){
        this.nomeCasa = nomeCasa;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.situazioneSplit = situazioneSplit;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public String getSituazioneSplit() {
        return situazioneSplit;
    }
}
