package win.sinno.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

/**
 * network
 * <p>
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-08-99 13:32
 */
public final class NetworkUtil {

    private NetworkUtil() {
    }

    /**
     * 获取主机名称
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getHostName() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostName();
    }


    public static String getIpAdd() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostAddress();
    }

    public static Collection<InetAddress> getAllHostAddress() {
        try {
            //网络接口
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Collection<InetAddress> addresses = new ArrayList<InetAddress>();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    addresses.add(inetAddress);
                }
            }

            return addresses;
        } catch (SocketException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取所有ipv4地址，包括127.0.0.1
     *
     * @return
     */
    public static Collection<InetAddress> getAllIpv4Address() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Collection<InetAddress> addresses = new ArrayList<InetAddress>();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress.getHostAddress().contains(".")) {
                        addresses.add(inetAddress);
                    }
                }
            }

            return addresses;
        } catch (SocketException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取ipv4,判断127.0.0.1
     *
     * @return
     */
    public static Collection<InetAddress> getIpv4AddressWithoutLoopback() {

        try {

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Collection<InetAddress> addresses = new ArrayList<InetAddress>();

            while (networkInterfaces.hasMoreElements()) {

                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {

                    InetAddress inetAddress = inetAddresses.nextElement();

                    if (inetAddress.getHostAddress().contains(".") && !inetAddress.isLoopbackAddress()) {
                        addresses.add(inetAddress);
                    }
                }
            }

            return addresses;
        } catch (SocketException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取私有ipv4地址
     *
     * @return
     */
    public static Collection<InetAddress> getPrivateIpv4Address() {
        try {
            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();

            Collection<InetAddress> addresses = new ArrayList<InetAddress>();

            while (networkInterfaceEnumeration.hasMoreElements()) {

                NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();

                Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();

                while (inetAddressEnumeration.hasMoreElements()) {

                    InetAddress inetAddress = inetAddressEnumeration.nextElement();

                    if (inetAddress.getHostAddress().contains(".") && !inetAddress.isLoopbackAddress()
                            && isPrivateIpv4Addr(inetAddress.getHostAddress())) {
                        addresses.add(inetAddress);
                    }

                }
            }

            return addresses;

        } catch (SocketException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取公网ipv4地址
     *
     * @return
     */
    public static Collection<InetAddress> getPublicIpv4Address() {
        try {
            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();

            Collection<InetAddress> addresses = new ArrayList<InetAddress>();

            while (networkInterfaceEnumeration.hasMoreElements()) {

                NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();

                Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();

                while (inetAddressEnumeration.hasMoreElements()) {

                    InetAddress inetAddress = inetAddressEnumeration.nextElement();

                    if (inetAddress.getHostAddress().contains(".") && !inetAddress.isLoopbackAddress()
                            && !isPrivateIpv4Addr(inetAddress.getHostAddress())) {
                        addresses.add(inetAddress);
                    }

                }
            }

            return addresses;

        } catch (SocketException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static Collection<InetAddress> getAllIpv6Address() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Collection<InetAddress> addresses = new ArrayList<InetAddress>();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress.getHostAddress().contains(":")) {
                        addresses.add(inetAddress);
                    }
                }
            }

            return addresses;
        } catch (SocketException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 根据网卡名称获取mac地址
     *
     * @param name
     * @return
     */
    public static String getHardwareAddressByName(String name) throws UnknownHostException, SocketException {

        NetworkInterface networkInterface = NetworkInterface.getByName(name);

        byte[] macBytes = networkInterface.getHardwareAddress();

        return parseMac(macBytes);
    }


    /**
     * 根据inet address获取mac地址
     *
     * @param inetAddress
     * @return
     * @throws SocketException
     */
    public static String getHardwareAddressByInetAddress(InetAddress inetAddress) throws SocketException {

        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);

        // mac 地址 bytes
        byte[] macBytes = networkInterface.getHardwareAddress();

        return parseMac(macBytes);
    }

    /**
     * 解析mac地址，将byte[]转换为字符串.
     *
     * @param macBytes
     * @return
     */
    private static String parseMac(byte[] macBytes) {
        StringBuilder builder = new StringBuilder();

        if (macBytes != null && macBytes.length > 1) {
            //mac byte
            builder.append(parseByte(macBytes[0])).append(":")
                    .append(parseByte(macBytes[1])).append(":")
                    .append(parseByte(macBytes[2])).append(":")
                    .append(parseByte(macBytes[3])).append(":")
                    .append(parseByte(macBytes[4])).append(":")
                    .append(parseByte(macBytes[5]));
        }

        return builder.toString();
    }

    /**
     * 解析byte
     *
     * @param b
     * @return
     */
    private static String parseByte(byte b) {
        int intValue = 0;
        if (b > 0) {
            intValue = b;
        } else {
            intValue = 256 + b;
        }
        return Integer.toHexString(intValue);
    }

    /**
     * 检测是否为私有网段
     * <p>
     * 私有网段
     * A类ip：10.0.0.0
     * B类ip：172.16.0.0-172.32.0.0
     * C类ip：192.168.0.0
     *
     * @param ipv4Addr
     * @return
     */
    public static boolean isPrivateIpv4Addr(String ipv4Addr) {

        //地址分段数据
        String[] addrSegmentArray = ipv4Addr.split("\\.");

        int len = addrSegmentArray.length;

        if (len != 4) {
            throw new IllegalArgumentException("[" + ipv4Addr + "] is not ipv4 addr.");
        }

        if ("10".equals(addrSegmentArray[0])) {
            return true;
        }

        if ("192".equals(addrSegmentArray[0]) && "168".equals(addrSegmentArray[1])) {
            return true;
        }

        if ("172".equals(addrSegmentArray[0])) {
            int ss = Integer.valueOf(addrSegmentArray[1]);
            if (ss >= 16 && ss <= 32) {
                return true;
            }
        }

        return false;
    }

}
