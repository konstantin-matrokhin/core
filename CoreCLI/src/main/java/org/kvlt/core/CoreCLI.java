package org.kvlt.core;

import org.kvlt.core.config.Config;
import org.kvlt.core.db.HibernateInitializer;
import org.kvlt.core.email.Email;
import org.kvlt.core.utils.Localization;

import java.io.FileNotFoundException;

public final class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) throws FileNotFoundException {
        Config.init();
        Localization.load();
        HibernateInitializer.init();
        Email.init();
        Core.get().init();
    }

}
