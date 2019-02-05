package me.kingtux.tuxmvc.simple.impl;

import me.kingtux.tmvc.core.Website;
import me.kingtux.tmvc.core.request.Request;
import me.kingtux.tmvc.core.view.TemplateGrabber;
import me.kingtux.tmvc.core.view.View;
import me.kingtux.tmvc.core.view.ViewManager;
import me.kingtux.tmvc.core.view.templategrabbers.ExternalTemplateGrabber;
import org.jtwig.JtwigTemplate;

import java.io.File;

public class SimpleViewManager implements ViewManager {
    private TemplateGrabber templateGrabber;

    public SimpleViewManager(TemplateGrabber templateGrabber) {
        this.templateGrabber = templateGrabber;
        if(this.templateGrabber ==null){
            this.templateGrabber = new ExternalTemplateGrabber(new File("templates"));
        }
    }

    @Override
    public void setTemplateGrabber(TemplateGrabber templateGrabber) {
        this.templateGrabber = templateGrabber;
    }

    @Override
    public TemplateGrabber getTemplateGrabber() {
        return templateGrabber;
    }

    @Override
    public String parseView(View view) {
        JtwigTemplate jtwigTemplate = JtwigTemplate.inlineTemplate(templateGrabber.getFile(view.getTemplate()));
        return jtwigTemplate.render(((SimpleView) view).getjTwigModel());
    }

    @Override
    public View buildView(Website website, Request request) {
        return new SimpleView();
    }
}
