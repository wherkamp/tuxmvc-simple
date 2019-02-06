package mekingtux.tuxmvc.simple.tests;

import me.kingtux.tmvc.core.Website;
import me.kingtux.tuxmvc.simple.SimpleWebsiteBuilder;

public class Test {
    public static void main(String[] args) {
        Website website;
        SimpleWebsiteBuilder simpleWebsiteBuilder = SimpleWebsiteBuilder.create().defaultTemplateGrabber().port(2022);
        website = simpleWebsiteBuilder.build();
        website.registerController(new TestController());
    }
}
