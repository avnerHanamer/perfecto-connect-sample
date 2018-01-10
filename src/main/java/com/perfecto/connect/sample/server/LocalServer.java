package com.perfecto.connect.sample.server;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Random;

public class LocalServer {

    private final Server server;
    private String address;
    private int port;

    public LocalServer() {
        try {
            address = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            address = "localhost";
        }
        port = 6060 + new Random().nextInt(9);
        server = new Server(getPort());
    }

    private int getPort() {
        return port;
    }

    private String getAddress() {
        return address;
    }

    public void start(String msg) throws Exception {
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addServlet(new ServletHolder(new MainRequest(msg)), "/");
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    public String getHost(){
        return "http://" + getAddress() + ":" + getPort();
    }

}
