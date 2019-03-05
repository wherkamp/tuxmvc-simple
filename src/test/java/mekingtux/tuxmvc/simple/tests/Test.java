package mekingtux.tuxmvc.simple.tests;

import me.kingtux.tmvc.core.Website;
import me.kingtux.tmvc.core.view.templategrabbers.ExternalTemplateGrabber;
import me.kingtux.tuxmvc.simple.SimpleWebsiteBuilder;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        Website website;
        File file =new File("templates");
        System.out.println(file.exists());
        SimpleWebsiteBuilder simpleWebsiteBuilder = SimpleWebsiteBuilder.create().templateGrabber(new ExternalTemplateGrabber(file)).port(2022);
        website = simpleWebsiteBuilder.build();
        website.registerController(new TestController());
    }
}
