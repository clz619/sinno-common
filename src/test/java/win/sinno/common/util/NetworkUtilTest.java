package win.sinno.common.util;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collection;

/**
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/3 下午5:34
 */
public class NetworkUtilTest {

    @Test
    public void testIsPrivateIpv4Addr() {
        String[] ips = {"10.168.1.1", "192.168.1.1", "192.167.1.1", "172.15.0.1", "172.16.0.1", "172.17.0.1", "172.32.0.1", "172.33.0.1"};

        for (String ip : ips) {
            System.out.println(ip + " is private addr :" + NetworkUtil.isPrivateIpv4Addr(ip));
        }
    }

    @Test
    public void testGetPublicIpv4Address() {

        Collection<InetAddress> inetAddresses = NetworkUtil.getPublicIpv4Address();

        System.out.println("public ip addr size:" + inetAddresses.size());

        if (CollectionUtils.isNotEmpty(inetAddresses)) {

            for (InetAddress inetAddress : inetAddresses) {
                System.out.println(inetAddress.getHostName());
            }
        }
    }

    @Test
    public void testGetPrivateIpv4Address() {
        //私有地址
        Collection<InetAddress> inetAddresses = NetworkUtil.getPrivateIpv4Address();

        System.out.println("private ip addr size:" + inetAddresses.size());

        if (CollectionUtils.isNotEmpty(inetAddresses)) {

            for (InetAddress inetAddress : inetAddresses) {
                System.out.println(inetAddress.getHostName());
            }
        }
    }

    @Test
    public void testHardwareAddressByInetAddress() {

        Collection<InetAddress> inetAddresses = NetworkUtil.getPrivateIpv4Address();

        System.out.println("private ip addr size:" + inetAddresses.size());

        if (CollectionUtils.isNotEmpty(inetAddresses)) {

            for (InetAddress inetAddress : inetAddresses) {
                try {
                    System.out.println(inetAddress.getHostAddress() + " mac:" + NetworkUtil.getHardwareAddressByInetAddress(inetAddress));
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testGetIp() throws UnknownHostException {
        System.out.println(NetworkUtil.getIpAdd());
    }
}
