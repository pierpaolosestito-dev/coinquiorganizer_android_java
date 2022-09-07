package coinquiorganizer.model;

import java.util.Comparator;

public class PuliziaEntity{
    private String nomeCasa,postoCasa,coinquilinoAddetto,giornoPulizia;

    public PuliziaEntity(String nomeCasa,String postoCasa,String coinquilinoAddetto,String giornoPulizia){
        this.nomeCasa=nomeCasa;
        this.postoCasa=postoCasa;
        this.coinquilinoAddetto=coinquilinoAddetto;
        this.giornoPulizia=giornoPulizia;
    }

    @Override
    public String toString() {
        return "PuliziaEntity{" +
                "nomeCasa='" + nomeCasa + '\'' +
                ", postoCasa='" + postoCasa + '\'' +
                ", coinquilinoAddetto='" + coinquilinoAddetto + '\'' +
                ", giornoPulizia='" + giornoPulizia + '\'' +
                '}';
    }

    public void setCoinquilinoAddetto(String coinquilinoAddetto) {
        this.coinquilinoAddetto = coinquilinoAddetto;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public String getPostoCasa() {
        return postoCasa;
    }

    public String getCoinquilinoAddetto() {
        return coinquilinoAddetto;
    }

    public String getGiornoPulizia() {
        return giornoPulizia;
    }

    public static Comparator<PuliziaEntity> comparator = new Comparator<PuliziaEntity>(){
        @Override
        public int compare(PuliziaEntity o1, PuliziaEntity o) {
            if(o1.getGiornoPulizia().equals("Lunedì")){
                if(o.getGiornoPulizia().equals("Lunedì"))
                    return 0;

                return -1;
            }

            if(o1.getGiornoPulizia().equals("Martedì")) {
                if (o.getGiornoPulizia().equals("Martedì"))
                    return 0;
                if (o.getGiornoPulizia().equals("Lunedì"))
                    return 1;

                return -1;
            }

            if(o1.getGiornoPulizia().equals("Mercoledì")){
                if (o.getGiornoPulizia().equals("Mercoledì"))
                    return 0;
                if (o.getGiornoPulizia().equals("Lunedì")||o.getGiornoPulizia().equals("Martedì"))
                    return 1;


                return -1;
            }
            if(o1.getGiornoPulizia().equals("Giovedì")){
                if (o.getGiornoPulizia().equals("Giovedì"))
                    return 0;
                if (o.getGiornoPulizia().equals("Lunedì") || o.getGiornoPulizia().equals("Martedì")||o.getGiornoPulizia().equals("Mercoledì"))
                    return 1;

                return -1;
            }

            if(o1.getGiornoPulizia().equals("Venerdì")){
                if(o.getGiornoPulizia().equals("Venerdì")){
                    return 0;
                }
                if(o.getGiornoPulizia().equals("Sabato") || o.equals("Domenica")){
                    return -1;
                }
                return 1;
            }

            if(o1.getGiornoPulizia().equals("Sabato")){
                if(o.getGiornoPulizia().equals("Sabato")){
                    return 0;
                }
                if(o.getGiornoPulizia().equals("Domenica")){
                    return -1;
                }
                return 1;
            }

            if(o1.getGiornoPulizia().equals("Domenica") && !o.getGiornoPulizia().equals("Domenica"))
                return -1;

            return 0;
        }
    };



}
