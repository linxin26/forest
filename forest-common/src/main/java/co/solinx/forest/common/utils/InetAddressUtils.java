package co.solinx.forest.common.utils;

import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by LX on 2015/3/28.
 */
public class InetAddressUtils {

    static Logger logger = Logger.getLogger(InetAddressUtils.class);

    /**
     * 取得ip地址，默认取localhost主机
     *
     * @return
     */
    public static String findAddress() {
        String address = null;
        address = findAddressByHostName("localhost");
        return address;
    }

    /**
     * 取得ip地址
     *
     * @param hostName
     * @return
     */
    public static String findAddressByHostName(String hostName) {
        String address = null;
        try {
            address = InetAddress.getByName(hostName).getHostAddress();
        } catch (UnknownHostException e) {
            logger.error(e);
        }
        return address;
    }

    public static void main(String[] args) {

        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            System.out.println(InetAddress.getByName("localhost").getHostAddress());
            System.out.println(InetAddress.getAllByName("localhost")[0]);
            System.out.println(InetAddress.getAllByName("localhost")[1]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
