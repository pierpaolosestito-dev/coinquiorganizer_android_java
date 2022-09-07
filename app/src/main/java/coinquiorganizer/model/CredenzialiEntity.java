package coinquiorganizer.model;

public class CredenzialiEntity {
    private String nomeCasa,username,password;

    public CredenzialiEntity(String nomeCasa,String username,String password){
        this.nomeCasa=nomeCasa;
        this.username=username;
        this.password=password;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
