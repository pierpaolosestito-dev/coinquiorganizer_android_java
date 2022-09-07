package coinquiorganizer.model;

public class CoinquilinoEntity {
    private String nomeCasa;
    private String username;
    private String password;
    private String email;
    private String domandaCasa;
    private String rispostaCasa;
    private String firstAccess;

    public CoinquilinoEntity(String nomeCasa,String username,String password,String email,String domandaCasa,String rispostaCasa,String firstAccess){
        this.nomeCasa = nomeCasa;
        this.username = username;
        this.password = password;
        this.email = email;
        this.domandaCasa = domandaCasa;
        this.rispostaCasa = rispostaCasa;
        this.firstAccess = firstAccess;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDomandaCasa() {
        return domandaCasa;
    }

    public String getRispostaCasa() {
        return rispostaCasa;
    }

    public String getFirstAccess() {
        return firstAccess;
    }

    @Override
    public String toString() {
        return "CoinquilinoEntity{" +
                "nomeCasa='" + nomeCasa + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", domandaCasa='" + domandaCasa + '\'' +
                ", rispostaCasa='" + rispostaCasa + '\'' +
                ", firstAccess=" + firstAccess +
                '}';
    }
}
