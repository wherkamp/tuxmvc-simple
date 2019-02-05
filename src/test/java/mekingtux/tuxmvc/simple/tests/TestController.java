package mekingtux.tuxmvc.simple.tests;

import me.kingtux.tmvc.core.annotations.Path;
import me.kingtux.tmvc.core.annotations.RequestParam;
import me.kingtux.tmvc.core.view.View;


public class TestController {
    @Path(path = "/test")
    public void test(@RequestParam(key = "t", type = RequestParam.Type.GET, defaultValue = "test") String s, View view) {
        System.out.println("Found!");
        view.setTemplate("index").set("test", s);
    }

    @Path(path = "/url/:t")
    public void urlTest(@RequestParam(key = "t", type = RequestParam.Type.URL, defaultValue = "test") String s, View view) {
        System.out.println("Found!");
        view.setTemplate("index").set("test", s);
    }

    @Path(path = "/")
    public void base(View view) {
        System.out.println("Found!");
        view.setTemplate("index").set("test", "Index");
    }
}
