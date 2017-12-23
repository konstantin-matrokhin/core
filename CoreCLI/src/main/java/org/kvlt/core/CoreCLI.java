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
        //CoreDAO.start();

        Session s = HibernateInitiaizer.getSessionFactory().openSession();
        s.beginTransaction();
        ServerPlayer p = new ServerPlayer();
        p.setName("MEMES");
        p.setPassword("test23231");
        p.setUuid("uuidempty");
        p.setLastIp("testip");
        p.setLastServer("test-server");
        p.setBanAmount(10);
        s.save(p);

        s.getTransaction().commit();

        CoreServer.get().start();
    }

}
