
package fx24.loginwithdbsuper;


public class StudentTable {

    
    int id;
    String name,emal,address;

    public StudentTable(int id, String name, String emal, String address) {
        this.id = id;
        this.name = name;
        this.emal = emal;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmal() {
        return emal;
    }

    public void setEmal(String emal) {
        this.emal = emal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
