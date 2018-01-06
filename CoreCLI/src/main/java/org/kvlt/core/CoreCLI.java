package org.kvlt.core;

import org.kvlt.core.config.Config;
import org.kvlt.core.db.HibernateInitializer;
import org.kvlt.core.email.Email;

public final class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) {
        Config.init();
        HibernateInitializer.init();
        Email.init();
        Core.get().init();
    }

}
