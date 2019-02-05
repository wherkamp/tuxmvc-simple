package me.kingtux.tuxmvc.simple.impl;

import io.javalin.Context;
import me.kingtux.tmvc.core.Website;
import me.kingtux.tmvc.core.controller.ControllerExeception;
import me.kingtux.tmvc.core.controller.ControllerExecutor;
import me.kingtux.tmvc.core.controller.SingleController;

public class ControllerHandler {
    private SingleController sc;
    private Website website;

    public ControllerHandler(SingleController sc) {
        this.sc = sc;
    }

    public ControllerHandler(SingleController sc, Website website) {
        this.sc = sc;
        this.website = website;
    }

    public void execute(Context ctx) {
        try {
            ControllerExecutor se = sc.buildExecutor(new SimpleRequest(ctx, sc.getRequestType()), website.getViewManager(), website);
            se.execute();
        } catch (ControllerExeception controllerExeception) {
            controllerExeception.printStackTrace();
            controllerExeception.getCause().printStackTrace();
        }
    }
}
