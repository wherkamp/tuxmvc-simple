package mekingtux.tuxmvc.simple.tests;

import me.kingtux.tmvc.core.Website;
import me.kingtux.tmvc.core.view.templategrabbers.ExternalTemplateGrabber;
import me.kingtux.tuxmvc.simple.SimpleWebsiteBuilder;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File f = new File("tmpls");
        if (!f.exists()) {
            f.mkdir();
        }
        Website website;
        SimpleWebsiteBuilder simpleWebsiteBuilder = SimpleWebsiteBuilder.createSimpleBuilder(8080).templateGrabber(new ExternalTemplateGrabber(f));
        website = simpleWebsiteBuilder.build();
        website.registerController(new TestController());
    }
}
