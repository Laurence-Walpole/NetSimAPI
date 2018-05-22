package OSI;

import OSI.Physical.Component;
import OSI.Physical.NIC;
import Standards.MAC;

import java.util.*;

/**
 * This class is used to allow the discovery of various network interfaces through
 * various connected components.
 * @author Lewis Hogan
 * @version 1.0
 * @since 2018-05-22
 */
public class Routing {
    private Map<MAC, List<NIC>> routingMap = new HashMap<MAC, List<NIC>>();

    /**
     * Finds a list of MACs that must be traversed to connect from network interface to destination
     * @param networkInterface The NIC that is the source of the query
     * @param destinationMAC The destination MAC address.
     * @param visitedMACs A set of previously visited MAC addresses
     * @return List<MAC> The MAC addresses of the NICs that must be hopped to arrive at the destination (includes destination MAC)
     */
    private List<NIC> findRoute(NIC networkInterface, MAC destinationMAC, Set<MAC> visitedMACs) {
        // Initial implementation will be an unoptimised breadth-first search for the MAC address
        List<NIC> route = new ArrayList<>();
        List<NIC> interfaces = new ArrayList<NIC>();

        System.out.println("Starting route search from MAC ADDRESS: " + networkInterface.getMacAddress());
        // Check that we are not the destination mac
        if (networkInterface.getMacAddress().equals(destinationMAC)) {
            System.out.println("Current Device is destination, aborting search...");
            return route;
        }

        if (routingMap.containsKey(destinationMAC)) {
            System.out.println("Route already known, using cached result...");
            // TODO: Loop through each mac address and check if the next step in the route still exists in connect components, if it does not then regenerate the routing map for that mac
            return routingMap.get(destinationMAC);
        }

        /**
         * Checks every connected NIC to see if we're directly connected to the destination
         * If NIC is destination, end the search now
         * Otherwise add the NIC to a list of interfaces to search through later
         */
        for (Component c : networkInterface.getConnectingComponents()) {
            // We only want to check other NICs
            if (!(c instanceof NIC)) {
                System.out.println("Component " + c + " is not a Network Interface, unable to propagate search along this route");
                continue;
            }

            NIC connectedInterface = (NIC) c;
            MAC connectedMAC = connectedInterface.getMacAddress();
            // We've found the destination, add to route and end search
            if (connectedMAC.equals(destinationMAC)) {
                System.out.println("Found destination MAC address: " + destinationMAC);
                route.add(connectedInterface);
                break;
            }

            // Skips any macs we've already visited in other instances of the search
            // This prevents us getting stuck in a infinite loop if several NICs are connected to each other in a circle
            if (visitedMACs.contains(connectedMAC)) {
                System.out.println("Skipping recognised interface: " + connectedInterface.getMacAddress());
                continue;
            } else {
                // We also don't want to search the same
                if (connectedMAC.equals(networkInterface.getMacAddress())) {
                    visitedMACs.add(networkInterface.getMacAddress());
                    continue;
                }

                visitedMACs.add(connectedInterface.getMacAddress());
                System.out.println("Adding MAC address to discovered interfaces: " + connectedInterface.getMacAddress());
                interfaces.add(connectedInterface);
            }
        }

        // If we didn't find a route on our first search, search connected interfaces
        if (route.size() < 1) {
            for (NIC connectedInterface : interfaces) {

                System.out.println("Attempting to search from connected interface: " + connectedInterface.getMacAddress() + " for MAC address: " + destinationMAC.getMACAddress());
                List<NIC> subroute = findRoute(connectedInterface, destinationMAC, visitedMACs);

                // If we find a route, break out early
                // TODO: Search all routes and take the subroute with the smallest size
                if (subroute.size() > 0) {
                    route.add(connectedInterface);
                    route.addAll(subroute);
                    break;
                }
            }
        }

        // If we found a route to the target, we can add it to the routing map
        if (route.size() > 0) {
            routingMap.put(destinationMAC, route);
        } else {
            System.out.println("Unable to find target MAC Address");
        }

        return route;
    }

    /**
     * Finds a list of NICs that must be traversed to connect from network interface to destination
     * @param networkInterface The NIC that is the source of the query
     * @param destinationMAC The destination MAC address.
     * @return List<MAC> The MAC addresses of the NICs that must be hopped to arrive at the destination (includes destination MAC)
     */
    public List<NIC> findRoute(NIC networkInterface, MAC destinationMAC) {
        return findRoute(networkInterface, destinationMAC, new HashSet<>());
    }
}
