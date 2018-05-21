package Standards;

import java.util.Arrays;
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

    public MAC(String address){
        this.address = address;
    }

    public String getMACAddress() {
        return address;
    }

    public void setMACAddress(String address) {
        this.address = address;
    }

    public void setRandomMACAddress(){
        this.address = randomMACAddress();
    }

    private String randomMACAddress(){
        Random rand = new Random();
        byte[] mac = new byte[6];
        rand.nextBytes(mac);
        mac[0] = (byte)(mac[0] & (byte)254);  //zeroing last 2 bytes to make it unicast and locally adminstrated
        StringBuilder sb = new StringBuilder(18);
        for(byte b : mac){

            if(sb.length() > 0)
                sb.append(":");

            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
