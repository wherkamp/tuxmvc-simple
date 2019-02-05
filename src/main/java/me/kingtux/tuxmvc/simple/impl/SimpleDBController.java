package me.kingtux.tuxmvc.simple.impl;

import me.kingtux.tmvc.core.model.DatabaseController;
import me.kingtux.tmvc.core.model.Model;
import me.kingtux.tmvc.core.model.ModelService;

public class SimpleDBController implements DatabaseController {

    @Override
    public <T extends Model> ModelService<T> registerModel(T t) {
        return new SimpleModelService<T>();
    }
}
