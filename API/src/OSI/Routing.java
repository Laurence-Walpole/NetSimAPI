package OSI;

import OSI.Physical.Component;
import OSI.Physical.NIC;
import Standards.IPType;
import Standards.IPv4;

import java.util.*;

/**
 * This class is used to allow the discovery of various network interfaces through
 * various connected components.
 * @author Lewis Hogan
 * @version 1.0
 * @since 2018-05-22
 */
public class Routing {
    private Map<IPv4, List<NIC>> routingMap = new HashMap<>();

    /**
     * Finds a list of IPs that must be traversed to connect from network interface to destination
     * @param networkInterface The NIC that is the source of the query
     * @param destinationIP The destination IP address.
     * @param visitedIPs A set of previously visited IP addresses
     * @return List<IP> The IP addresses of the NICs that must be hopped to arrive at the destination (includes destination IP)
     */
    private List<NIC> findRoute(NIC networkInterface, IPv4 destinationIP, Set<IPv4> visitedIPs) {
        // Initial implementation will be an unoptimised breadth-first search for the IP address
        List<NIC> route = new ArrayList<>();
        List<NIC> interfaces = new ArrayList<NIC>();

        System.out.println("Starting route search from IP ADDRESS: " + networkInterface.getIPv4());
        // Check that we are not the destination IP
        if (networkInterface.getIPv4().equals(destinationIP)) {
            System.out.println("Current Device is destination, aborting search...");
            return route;
        }

        if (routingMap.containsKey(destinationIP)) {
            System.out.println("Route already known, using cached result...");
            // TODO: Loop through each IP address and check if the next step in the route still exists in connect components, if it does not then regenerate the routing map for that IP
            return routingMap.get(destinationIP);
        }

        /**
         * Checks every connected NIC to see if we're directly connected to the destination
         * If NIC is destination, end the search now
         * Otherwise add the NIC to a list of interfaces to search through later
         */
        for (Component c : networkInterface.getConnectingComponents()) {
            // TODO: Find ways of evaluating if the other components types are connected to NICs, then obtain associated NIC
            // We only want to check other NICs
            if (!(c instanceof NIC)) {
                System.out.println("Component " + c + " is not a Network Interface, unable to propagate search along this route");
                continue;
            }

            NIC connectedInterface = (NIC) c;
            IPv4 connectedIP = connectedInterface.getIPv4();
            // We've found the destination, add to route and end search
            if (connectedIP.equals(destinationIP)) {
                System.out.println("Found destination IP address: " + destinationIP);
                route.add(connectedInterface);
                break;
            }

            // Skips any IPs we've already visited in other instances of the search
            // This prevents us getting stuck in a infinite loop if several NICs are connected to each other in a circle
            if (visitedIPs.contains(connectedIP)) {
                System.out.println("Skipping recognised interface: " + connectedInterface.getIPv4());
                continue;
            } else {
                // We also don't want to search the same
                if (connectedIP.equals(networkInterface.getIPv4())) {
                    visitedIPs.add(networkInterface.getIPv4());
                    continue;
                }

                visitedIPs.add(connectedInterface.getIPv4());
                System.out.println("Adding IP address to discovered interfaces: " + connectedInterface.getIPv4());
                interfaces.add(connectedInterface);
            }
        }

        // If we didn't find a route on our first search, search connected interfaces
        // TODO: Change this so instead of searching every connect interface, we only ask the gateway address provided to continue the search
        if (route.size() < 1) {
            for (NIC connectedInterface : interfaces) {

                System.out.println("Attempting to search from connected interface: " + connectedInterface.getIPv4() + " for IP address: " + destinationIP.getIPAddress(IPType.NORMAL));
                List<NIC> subroute = findRoute(connectedInterface, destinationIP, visitedIPs);

                // If we find a route, break out early
                // TODO: Search all routes and take the subroute with the smallest size
                // This can be achieved by adding all routes to a TreeMap with the size as index, then choosing the element with the smallest key
                if (subroute.size() > 0) {
                    route.add(connectedInterface);
                    route.addAll(subroute);
                    break;
                }
            }
        }

        // If we found a route to the target, we can add it to the routing map
        if (route.size() > 0) {
            routingMap.put(destinationIP, route);
        } else {
            System.out.println("Unable to find target IP Address");
        }

        return route;
    }

    /**
     * Finds a list of NICs that must be traversed to connect from network interface to destination
     * @param networkInterface The NIC that is the source of the query
     * @param destinationIP The destination IP address.
     * @return List<IP> The IP addresses of the NICs that must be hopped to arrive at the destination (includes destination IP)
     */
    public List<NIC> findRoute(NIC networkInterface, IPv4 destinationIP) {
        return findRoute(networkInterface, destinationIP, new HashSet<>());
    }
}
