package org.kvlt.core;

import org.kvlt.core.config.Config;
import org.kvlt.core.config.NewConfig;

public class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) {
        Config.init();
        //CoreDAO.connect();
        new NewConfig("test.yml").load();
        CoreServer.get().start();
    }

}
