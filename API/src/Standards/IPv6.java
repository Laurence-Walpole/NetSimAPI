package Standards;

import java.util.Map;

/**
 * This class is used to describe and generate an IPv6 Address.
 *
 * @author Laurence Walpole
 * @version 1.0
 * @since 2018-05-21
 */
public class IPv6 {
    private Map<IPType, IP> ipAddresses;

    public IPv6(String subnet, String mask){
        //TODO:
        //Calculate IP

        ipAddresses.put(IPType.NORMAL, new IP("FE80::1"));
    }

    public IP getIPAddress(IPType type){
        return ipAddresses.get(type);
    }

    public void addIPAddress(IPType type, String subnet, String mask){
        //TODO:
        //Calculate IP

        ipAddresses.put(type, new IP("FE80::1"));
    }
}
