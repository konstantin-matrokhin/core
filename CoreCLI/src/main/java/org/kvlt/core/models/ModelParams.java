package org.kvlt.core.models;

import java.util.HashMap;

public abstract class ModelParams {

    private HashMap<String, String> cols;

    public ModelParams() {
        cols = new HashMap<>();
        fillCols();
    }

    public abstract String selectSQL();

    protected abstract void fillCols();

    protected void map(String col, String prop) {
        cols.put(col, prop);
    }

    public HashMap<String, String> getCols() {
        return cols;
    }

}
