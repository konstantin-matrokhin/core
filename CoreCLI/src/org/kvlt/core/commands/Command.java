package org.kvlt.core.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {

    private String name;
    private String[] args;
    private List<String> aliases;

    /**
     * @return выполнена ли команда успешно
     * возвращает false, если команды не существует или она невалидна
     */
    protected abstract boolean execute();

    public Command(String name) {
        this.name = name;
        aliases = new ArrayList<>();
    }

    public void passInput(String name, String[] args) {

        if (getName().equalsIgnoreCase(name) || aliases.contains(name.toLowerCase())) {
            this.name = name;
            this.args = args;
            execute();
        }

    }

    protected void addAliases(String... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
    }
//
//    protected void addAliases(String[] a) {
//
//    }

    public String getName() {
        return name;
    }

    protected String getArg(int index) {
        if (index < 0 || index > args.length || args.length == 0) return "";
        return args[index];
    }

    protected String[] getArgs() {
        return args;
    }

}
