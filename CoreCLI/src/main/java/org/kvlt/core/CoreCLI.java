package org.kvlt.core;

import org.hibernate.Session;
import org.kvlt.core.config.Config;
import org.kvlt.core.config.NewConfig;
import org.kvlt.core.db.CoreDAO;
import org.kvlt.core.db.HibernateInitiaizer;
import org.kvlt.core.entities.ServerPlayer;

public class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) {
        Config.init();
        CoreDAO.start();
        CoreServer.get().start();
    }

}
