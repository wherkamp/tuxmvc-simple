package me.kingtux.tuxmvc.simple;

import io.javalin.Javalin;
import io.javalin.staticfiles.Location;
import me.kingtux.tmvc.core.Website;
import me.kingtux.tmvc.core.view.TemplateGrabber;
import me.kingtux.tuxmvc.simple.impl.SimpleWebsite;

import java.io.File;

public class SimpleWebsiteBuilder {
    private Javalin javalin;
    //private String url, username, password;
    private TemplateGrabber templateGrabber;

    private SimpleWebsiteBuilder() {

    }

    public static SimpleWebsiteBuilder createSimpleBuilder(Javalin jav) {
        SimpleWebsiteBuilder s = new SimpleWebsiteBuilder();
        s.javalin = jav.start();
        return s;
    }

    public static SimpleWebsiteBuilder createSimpleBuilder(int port, File publicFolder) {
        return createSimpleBuilder(Javalin.create().port(port).enableStaticFiles(publicFolder.getPath(), Location.EXTERNAL));
    }

    public static SimpleWebsiteBuilder createSimpleBuilder(int port) {
        return createSimpleBuilder(port, new File("public"));
    }

    public static SimpleWebsiteBuilder createSimpleBuilder() {
        return createSimpleBuilder(7000);
    }

    public SimpleWebsiteBuilder templateGrabber(TemplateGrabber templateGrabber) {
        this.templateGrabber = templateGrabber;
        return this;
    }
    /*public SimpleWebsiteBuilder sql(SQLDialects dialects, String username, String password, String host, String database, String port) {
        this.dialects = dialects;
        this.url = String.format(this.dialects.getUrl(), host, port, database);
        this.username = username;
        this.password = password;
        return this;
    }*/

    public Website build() {
        return new SimpleWebsite(templateGrabber, javalin);
    }
}
