package Model.dtos;

public class Owner {

    private String username;
    private String person_id;
    private String name;
    private String address;
    private String neighborhood;



    public Owner(String username, String person_id, String name, String address, String neighborhood) {
        this.username = username;
        this.person_id = person_id;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;

    }



    public String getEmail() {
        return name;
    }

    public void setEmail(String email) {
        this.name = email;
    }

    public String getFullName() {
        return address;
    }

    public void setFullName(String fullName) {
        this.address = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
