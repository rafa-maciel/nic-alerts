package com.rmaciel.nicalerts.monitoring.infra.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class NetworkCommunicationService {

    public Boolean ping(String ipAddress) throws UnknownHostException {
        InetAddress iaddress = InetAddress.getByName(ipAddress);
        try {
            return iaddress.isReachable(5000);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
