package org.kvlt.core;

public class CoreCLI {

    private CoreCLI() {}

    public static void main(String[] args) {
        Config.init();
        CoreServer.get().start();
    }

}
