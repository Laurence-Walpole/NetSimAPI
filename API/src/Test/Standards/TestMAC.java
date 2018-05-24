package Standards;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test Class for MAC.
 */
public class TestMAC {

    @Test
    public void testRandomAddress() {
        MAC m1 = new MAC("");

        m1.setRandomMACAddress();

        String address = m1.getMACAddress();
        int addressLength = address.split(":").length;

        System.out.println("Checking random MAC address is 48 or 64 bits");
        // 48 Bit or 64 Bit MAC addresses
        Assert.assertTrue(6 == addressLength || addressLength == 8);
    }
}
