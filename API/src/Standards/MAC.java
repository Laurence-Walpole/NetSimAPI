package Standards;

import java.util.Random;
/**
 * This class is used to describe and generate a MAC Address.
 *
 * @author Laurence Walpole
 * @version 1.0
 * @since 2018-05-21
 */
public class MAC {
    private String address;

    /**
     * Defines the piece of cable used to connect two NICs.
     * @param address Defines the MAC Address standard of the interface.
     */
    public MAC(String address){
        this.address = address;
    }
    /**
     * @return String This returns the MAC address.
     */
    public String getMACAddress() {
        return address;
    }
    /**
     * @param address Set the MAC Address.
     */
    public void setMACAddress(String address) {
        this.address = address;
    }
    /**
     * Sets the MAC Address based on a random generation.
     */
    public void setRandomMACAddress(){
        this.address = randomMACAddress();
    }
    /**
     * @return String This returns the newly generated MAC address.
     */
    private String randomMACAddress(){
        Random rand = new Random();
        byte[] mac = new byte[6];
        rand.nextBytes(mac);
        mac[0] = (byte)(mac[0] & (byte)254);
        StringBuilder sb = new StringBuilder(18);
        for(byte b : mac){

            if(sb.length() > 0)
                sb.append(":");

            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
