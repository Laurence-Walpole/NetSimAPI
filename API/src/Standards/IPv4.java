package Standards;

import java.util.Map;

/**
 * This class is used to describe and generate an IPv4 Address.
 *
 * @author Laurence Walpole
 * @version 1.0
 * @since 2018-05-21
 */
public class IPv4 {

    private Map<IPType, IP> ipAddresses;

    public IPv4(String subnet, String mask){
        //TODO:
        //Calculate IP

        ipAddresses.put(IPType.NORMAL, new IP("127.0.0.1"));
    }

    public IP getIPAddress(IPType type){
        return ipAddresses.get(type);
    }

    public void addIPAddress(IPType type, String subnet, String mask){
        //TODO:
        //Calculate IP

        ipAddresses.put(type, new IP("127.0.0.1"));
    }

    public boolean equals(Object other) {
        return (other instanceof IPv4) ? (((IPv4) other).getIPAddress(IPType.NORMAL).getAddress().equals(getIPAddress(IPType.NORMAL).getAddress())) : false;
    }

    public int hashCode() {
        return getIPAddress(IPType.NORMAL).getAddress().hashCode();
    }
}