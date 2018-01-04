package org.kvlt.core;

import org.kvlt.core.config.Config;
import org.kvlt.core.db.HibernateInitializer;

public class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) throws ClassNotFoundException {
        Config.init();
        HibernateInitializer.start();
        Class.forName("org.kvlt.core.email.Email");
        CoreServer.get().start();
    }

}
