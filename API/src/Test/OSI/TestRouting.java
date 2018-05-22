package OSI;

import OSI.Physical.Component;
import OSI.Physical.NIC;
import OSI.Routing;
import Standards.MAC;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test Class for OSI.Routing
 */
public class TestRouting {

    private void printHops(List<NIC> path) {
        for(int i = 0; i < path.size(); i++) {
            System.out.println("\t" + path.get(i));
        }
    }

    @Test
    /**
     * Tests routing for directly connected NICs.
     */
    public void testDirectRoute() {
        List<Component> networkInterface1Connections = new ArrayList<>();
        List<Component> destinationConnections = new ArrayList<>();

        NIC networkInterface1 = new NIC(new MAC(""), 3, 100, false, networkInterface1Connections);
        NIC destinationInterface = new NIC(new MAC(""), 3, 100, false, destinationConnections);

        networkInterface1Connections.add(destinationInterface);
        destinationConnections.add(networkInterface1);

        Routing router = new Routing();

        List<NIC> routeToDestinationMAC = router.findRoute(networkInterface1, destinationInterface.getMacAddress());
        Assert.assertEquals(1, routeToDestinationMAC.size());
        printHops(routeToDestinationMAC);

        List<NIC> routeToInterface1 = router.findRoute(destinationInterface, networkInterface1.getMacAddress());
        Assert.assertEquals(1, routeToInterface1.size());
        printHops(routeToInterface1);
    }

    @Test
    /**
     * Tests routing for NICs with an intermediary NIC (non direct connection)
     */
    public void testMultipleHops() {
        List<Component> networkInterface1Connections = new ArrayList<>();
        List<Component> networkInterface2Connections = new ArrayList<>();
        List<Component> destinationConnections = new ArrayList<>();

        NIC networkInterface1 = new NIC(new MAC(""), 3, 100, false, networkInterface1Connections);
        NIC networkInterface2 = new NIC(new MAC(""), 3, 100, false, networkInterface2Connections);
        NIC destinationInterface = new NIC(new MAC(""), 3, 100, false, destinationConnections);

        networkInterface1Connections.add(networkInterface2);
        networkInterface2Connections.add(networkInterface1);
        networkInterface2Connections.add(destinationInterface);
        destinationConnections.add(networkInterface2);

        Routing router = new Routing();

        List<NIC> routeToDestinationMAC = router.findRoute(networkInterface1, destinationInterface.getMacAddress());
        Assert.assertEquals(2, routeToDestinationMAC.size());
        printHops(routeToDestinationMAC);

        List<NIC> routeToInterface1 = router.findRoute(destinationInterface, networkInterface1.getMacAddress());
        Assert.assertEquals(2, routeToInterface1.size());
        printHops(routeToInterface1);

    }
}
