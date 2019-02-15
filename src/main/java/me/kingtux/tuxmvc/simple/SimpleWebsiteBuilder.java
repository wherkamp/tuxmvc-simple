package me.kingtux.tuxmvc.simple;

import io.javalin.Javalin;
import io.javalin.staticfiles.Location;
import me.kingtux.tmvc.core.Website;
import me.kingtux.tmvc.core.view.TemplateGrabber;
import me.kingtux.tmvc.core.view.templategrabbers.ExternalTemplateGrabber;
import me.kingtux.tuxmvc.simple.impl.SimpleWebsite;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.io.File;

public class SimpleWebsiteBuilder {
    //Template Grabber
    private TemplateGrabber templateGrabber;
    //Javalin Stuff
    private SslContextFactory sslContextFactory;
    private int port = 7575, sslPort;
    private File publicDirectory = new File("public");
    private String host;

    //End of Javalin stuff
    private SimpleWebsiteBuilder() {
    }

    /**
     * The port
     * @param port the port
     * @return the SimpleWebsiteBuilder
     */
    public SimpleWebsiteBuilder port(int port) {
        this.port = port;
        return this;
    }

    /**
     * The host
     * @param host the host (google.com)
     * @return the SimpleWebsiteBuilder
     */
    public SimpleWebsiteBuilder host(String host) {
        this.host = host;
        return this;
    }

    /**
     * https://docs.kingtux.me/tuxmvc/index.html?me/kingtux/tmvc/core/view/templategrabbers/package-summary.html
     * @param t the Template
     * @return the SimpleWebsiteBuilder
     */
    public SimpleWebsiteBuilder templateGrabber(TemplateGrabber t) {
        this.templateGrabber = t;
        return this;
    }

    /**
     * Use the default templategrabber
     * @return the SimpleWebsiteBuilder
     */
    public SimpleWebsiteBuilder defaultTemplateGrabber() {
        this.templateGrabber = new ExternalTemplateGrabber(new File("templates"));
        return this;
    }

    public static SimpleWebsiteBuilder create() {
        return new SimpleWebsiteBuilder();
    }

    public static SslContextFactory getSslContextFactory(String file, String password) {
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(file);
        sslContextFactory.setKeyStorePassword(password);
        return sslContextFactory;
    }

    /**
     * Sets the ssl rules
     * @param sslPort the port
     * @param sslContextFactory the sslContextFactory
     * @return the SimpleWebsiteBuilder
     */
    public SimpleWebsiteBuilder ssl(int sslPort, SslContextFactory sslContextFactory) {
        this.sslPort = sslPort;
        this.sslContextFactory = sslContextFactory;
        return this;
    }

    /**
     * Sets the ssl rules,
     * @param sslPort the port to use
     * @param certPath the cert path
     * @param password the password
     * @return the SimpleWebsiteBuilder
     */
    public SimpleWebsiteBuilder ssl(int sslPort, String certPath, String password) {
        this.sslPort = sslPort;
        this.sslContextFactory = getSslContextFactory(certPath, password);
        return this;
    }

    /**
     * Builds the website!
     * @return The website!
     */
    public Website build() {
        Javalin javalin = Javalin.create().enableStaticFiles(publicDirectory.getPath(), Location.EXTERNAL);
        if (!publicDirectory.exists()) publicDirectory.mkdir();
        if (sslContextFactory != null) {
            javalin.server(() -> {
                Server server = new Server();
                ServerConnector sslConnector = new ServerConnector(server, sslContextFactory);
                sslConnector.setPort(port);
                ServerConnector connector = new ServerConnector(server);
                connector.setPort(sslPort);
                server.setConnectors(new Connector[]{sslConnector, connector});
                return server;
            });
        } else {
            javalin.port(port);
        }
        javalin.start();
        return new SimpleWebsite(templateGrabber, javalin, host,sslContextFactory != null);
    }
}
