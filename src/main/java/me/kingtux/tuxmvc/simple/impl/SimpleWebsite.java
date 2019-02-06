package me.kingtux.tuxmvc.simple.impl;


import io.javalin.Javalin;
import io.javalin.core.HandlerType;
import me.kingtux.simpleannotation.MethodFinder;
import me.kingtux.tmvc.core.Website;
import me.kingtux.tmvc.core.annotations.Path;
import me.kingtux.tmvc.core.controller.SingleController;
import me.kingtux.tmvc.core.model.DatabaseController;
import me.kingtux.tmvc.core.request.RequestType;
import me.kingtux.tmvc.core.view.TemplateGrabber;
import me.kingtux.tmvc.core.view.ViewManager;

import java.lang.reflect.Method;


public class SimpleWebsite implements Website {
    private Javalin javalin;
    private ViewManager viewManager;
    private DatabaseController databaseController;
    private boolean https = false;

    public SimpleWebsite(TemplateGrabber templateGrabber, Javalin javalin, boolean https) {
        this.https = https;
        viewManager = new SimpleViewManager(templateGrabber);
        this.javalin = javalin;
    }

    public void registerController(Object controller) {
        for (Method method : MethodFinder.getAllMethodsWithAnnotation(controller.getClass(), Path.class)) {
            SingleController sc = new SingleController(controller, method);
            javalin.addHandler(getHandlerType(sc.getRequestType()), sc.getPath(), new ControllerHandler(sc, this    )::execute);
        }
    }

    private HandlerType getHandlerType(RequestType requestType) {
        switch (requestType) {
            case GET:
                return HandlerType.GET;
            case POST:
                return HandlerType.POST;
            case PUT:
                return HandlerType.PUT;
            case DELETE:
                return HandlerType.DELETE;
        }
        return HandlerType.GET;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public DatabaseController getDatabaseController() {
        return null;
    }

    @Override
    public boolean isHttps() {
        return https;
    }

}
