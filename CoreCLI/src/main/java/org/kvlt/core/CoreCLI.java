package org.kvlt.core;

import org.kvlt.core.config.Config;

public class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) {
        Config.init();
        //CoreDAO.connect();
        CoreServer.get().start();
    }

}
