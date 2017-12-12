package org.kvlt.core;

import org.kvlt.core.config.Config;
import org.kvlt.core.config.NewConfig;
import org.kvlt.core.db.CoreDAO;

public class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) {
        Config.init();
        //CoreDAO.start();
        //new NewConfig("test.yml").load();
        CoreServer.get().start();
    }

}
