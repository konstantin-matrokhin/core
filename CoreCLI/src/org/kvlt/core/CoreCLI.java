package org.kvlt.core;

import org.kvlt.core.config.Config;
import org.kvlt.core.db.DB;
import org.kvlt.core.db.MySQLConnection;
import org.kvlt.core.utils.Log;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;

public class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) throws Exception {
        Config.init();
        DB.getMySQLConnection().connect();

        CoreServer.get().start();
    }

}
