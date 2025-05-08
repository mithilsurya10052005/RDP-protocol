import java.net.*;
import java.util.*;

public class IpAddr {
    private String ip;
    IpAddr(){
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface netInterface = interfaces.nextElement();

                // Skip loopback and down interfaces
                if (!netInterface.isUp() || netInterface.isLoopback()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        ip=addr.getHostAddress();
                        System.out.println("Active IP Address: " + addr.getHostAddress());
                        return; // Stop after finding the first valid one
                    }
                }
            }

            System.out.println("No active IP address found.");
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    String getIp(){
        return ip;
    }
}
