package org.kvlt.core.json;

import java.util.Set;

public class ServerInfo extends JsonInfo {

    private String name;
    private int port;
    private Set<String> players;

    public ServerInfo(String jsonString) {
        super(jsonString);
        name = getString("name");
        port = getNumber("port");
    }

    public ServerInfo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public void setPlayers(Set<String> players) {
        this.players = players;
    }

}
