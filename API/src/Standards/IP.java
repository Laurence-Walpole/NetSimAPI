package Standards;
/**
 * This class is used to describe a basic IP address.
 *
 * @author Laurence Walpole
 * @version 1.0
 * @since 2018-05-21
 */
public class IP {

    private String address;

    public IP(String address){
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
