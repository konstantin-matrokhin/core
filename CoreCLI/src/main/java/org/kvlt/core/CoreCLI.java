package org.kvlt.core;

import org.kvlt.core.config.Config;
import org.kvlt.core.db.HibernateInitializer;
import org.kvlt.core.email.Email;
import org.kvlt.core.utils.Lang;
import org.kvlt.core.utils.Localization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) throws FileNotFoundException {
        Config.init();
        Localization.load(Lang.RUSSIAN, new FileInputStream(new File("ru.yml")));
        Localization.load(Lang.ENGLISH, new FileInputStream(new File("en.yml")));
        HibernateInitializer.init();
        Email.init();
        Core.get().init();
    }

}
