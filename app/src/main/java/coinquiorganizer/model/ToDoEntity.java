package coinquiorganizer.model;

import java.util.Comparator;

public class ToDoEntity {
    private String nomeCasa,nomeAttivita,descrizioneAttivita,dataOrario;
    private boolean importante,segnato;

    public ToDoEntity(String nomeCasa,String nomeAttivita,String descrizioneAttivita,boolean importante,boolean segnato,String dataOrario){
        this.nomeCasa = nomeCasa;
        this.nomeAttivita = nomeAttivita;
        this.descrizioneAttivita = descrizioneAttivita;
        this.importante = importante;
        this.segnato = segnato;
        this.dataOrario=dataOrario;
    }


    public String getNomeCasa() {
        return nomeCasa;
    }

    public String getNomeAttivita() {
        return nomeAttivita;
    }

    public String getDescrizioneAttivita() {
        return descrizioneAttivita;
    }

    public boolean isImportante() {
        return importante;
    }

    public boolean isSegnato() {
        return segnato;
    }

    public void setSegnato(boolean segnato) {
        this.segnato = segnato;
    }

    public String getDataOrario() {
        return dataOrario;
    }

    public static Comparator<ToDoEntity> comparator = new Comparator<ToDoEntity>(){
        @Override
        public int compare(ToDoEntity o1, ToDoEntity o) {
            if(o1.isImportante()){
                if(!o.isImportante())
                    return -1;
            }
            if(!o1.isImportante()){
                if(o.isImportante())
                    return 1;
            }
            return 0;
        }
    };
}
