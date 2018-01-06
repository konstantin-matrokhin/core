package org.kvlt.core;

import org.kvlt.core.config.Config;
import org.kvlt.core.db.HibernateInitializer;

public final class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) throws ClassNotFoundException {
        Config.init();
        HibernateInitializer.init();

        Class.forName("org.kvlt.core.email.Email");
        Core.get().init();
    }

}
