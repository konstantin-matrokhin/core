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

        Session s = HibernateInitiaizer.getSessionFactory().openSession();
        s.beginTransaction();
        ServerPlayer p = new ServerPlayer();
        p.setName("meme");
        p.setId(1000);
        p.setPassword("test");
        s.saveOrUpdate(p);
        s.getTransaction().commit();
        s.close();

        //new NewConfig("test.yml").load();
        CoreServer.get().start();
    }

}
