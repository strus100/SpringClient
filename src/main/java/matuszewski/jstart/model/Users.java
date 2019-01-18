package matuszewski.jstart.model;

public class Users {

    private Long libarycardnumber;
    private String firstname;
    private String lastname;

    public Users(Long libarycardnumber, String firstname, String lastname) {
        this.libarycardnumber = libarycardnumber;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Users() {
    }

    public Long getLibarycardnumber() {
        return libarycardnumber;
    }

    public void setLibarycardnumber(Long libarycardnumber) {
        this.libarycardnumber = libarycardnumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
