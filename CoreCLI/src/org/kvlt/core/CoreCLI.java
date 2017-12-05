package org.kvlt.core;

import org.kvlt.core.config.Config;
import org.kvlt.core.db.DAO;

public class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) {
        Config.init();
        //DAO.connect();
        CoreServer.get().start();
    }

}
