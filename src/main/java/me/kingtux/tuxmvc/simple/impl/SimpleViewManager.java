package me.kingtux.tuxmvc.simple.impl;

import me.kingtux.tmvc.core.Website;
import me.kingtux.tmvc.core.WebsiteRules;
import me.kingtux.tmvc.core.request.Request;
import me.kingtux.tmvc.core.view.TemplateGrabber;
import me.kingtux.tmvc.core.view.View;
import me.kingtux.tmvc.core.view.ViewManager;
import me.kingtux.tmvc.core.view.ViewVariableGrabber;
import me.kingtux.tmvc.core.view.templategrabbers.ExternalTemplateGrabber;
import org.jtwig.JtwigTemplate;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SimpleViewManager implements ViewManager {
    private TemplateGrabber templateGrabber;
    private Map<String, Object> defaultVariables = new HashMap<>();
    private Map<String, ViewVariableGrabber> viewVariableGrabbers = new HashMap<>();

    public SimpleViewManager(TemplateGrabber templateGrabber, WebsiteRules website) {
        this.templateGrabber = templateGrabber;
        if(this.templateGrabber ==null){
            this.templateGrabber = new ExternalTemplateGrabber(new File("templates"));
        }
        // sr.host sr.protocol and sr.baseURL
        registerDefaultViewVariable("sr", website);
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
        View view = new SimpleView();
        defaultVariables.forEach(view::set);
        viewVariableGrabbers.forEach((s, viewVariableGrabber) -> view.set(s, viewVariableGrabber.get(request)));
        return view;
    }


    @Override
    public void registerDefaultViewVariable(String s, Object o) {
        if (o instanceof ViewVariableGrabber) {
            registerViewVariableGrabber(s, (ViewVariableGrabber) o);
            return;
        }
        defaultVariables.put(s, o);
    }

    @Override
    public void registerViewVariableGrabber(String s, ViewVariableGrabber viewVariableGrabber) {
        viewVariableGrabbers.put(s, viewVariableGrabber);
    }
}
