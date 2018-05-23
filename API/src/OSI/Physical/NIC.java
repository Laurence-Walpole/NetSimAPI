package OSI.Physical;

import Standards.IPv4;
import Standards.IPv6;
import Standards.MAC;
import java.util.List;
/**
 * This class is used to describe a Network Interface Card component in the network.
 * It inherits attributes from the Component class.
 *
 * @author Laurence Walpole
 * @version 1.0
 * @since 2018-05-21
 */
public class NIC extends Component{
    private MAC macAddress;
    private int maxConnections;
    private IPv4 iPv4;
    private IPv6 iPv6;

    /**
     * Defines the piece of cable used to connect two NICs.
     * @param macAddress Defines the MAC Address standard of the interface, can be left blank for random.
     * @param maxConnections Defines the maximum amount of connections this NIC can handle.
     * @param interfaceSpeed Defines the speed of the interface, measured in MegaBytes.
     * @param active Defines if the component is active.
     * @param connectingComponents The other components connected to this component.
     */
    public NIC(MAC macAddress, int maxConnections, int interfaceSpeed, boolean active, List<Component> connectingComponents){
        super(active, interfaceSpeed, connectingComponents);
        if (macAddress.getMACAddress().equals("")){
            macAddress.setRandomMACAddress();
            this.macAddress = macAddress;
        } else {
            this.macAddress = macAddress;
        }
        this.maxConnections = maxConnections;
    }
    /**
     * @return MAC This returns the MAC address of the NIC.
     */
    public MAC getMacAddress() {
        return macAddress;
    }
    /**
     * @return String This returns the maximum connections of the NIC.
     */
    public int getMaxConnections() {
        return maxConnections;
    }

    /**
     * @return IPv4 This returns the IPv4 Address of the NIC.
     */
    public IPv4 getIPv4() { return iPv4;}

    public IPv6 getIPv6() { return iPv6; }

    /**
     * @return String Returns a formatted display of the NICs basic information.
     */
    @Override
    public String toString(){
        return String.format("[MAC]: %s, [Ports]: %d, [Port Speed]: %dMB/s, [Active]: %s, [Connection Count]: %d",
                getMacAddress(), getMaxConnections(), getMaxThroughput(), isActive(), getConnectingComponents().size());
    }
}
