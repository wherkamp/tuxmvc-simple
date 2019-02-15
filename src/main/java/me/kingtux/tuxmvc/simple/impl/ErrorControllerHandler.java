package me.kingtux.tuxmvc.simple.impl;

import io.javalin.Context;
import me.kingtux.tmvc.core.Website;
import me.kingtux.tmvc.core.controller.ControllerExeception;
import me.kingtux.tmvc.core.controller.ControllerExecutor;
import me.kingtux.tmvc.core.errorhandler.ErrorController;

public class ErrorControllerHandler {
    private ErrorController sc;
    private Website website;

    public ErrorControllerHandler(ErrorController sc) {
        this.sc = sc;
    }

    public ErrorControllerHandler(ErrorController sc, Website website) {
        this.sc = sc;
        this.website = website;
    }

    public void execute(Context ctx) {
        try {
            ControllerExecutor se = sc.buildExecutor(new SimpleRequest(ctx, null, website), website.getViewManager(), website);
            se.execute();
        } catch (ControllerExeception controllerExeception) {
            controllerExeception.printStackTrace();
        }
    }
}
