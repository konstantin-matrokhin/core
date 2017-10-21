package org.kvlt.core.commands;

public abstract class Command {

    private String name;
    private String[] args;

    /**
     * @return выполнена ли команда успешно
     * возвращает false, если команды не существует или она невалидна
     */
    protected abstract boolean execute();

    public Command(String name) {
        this.name = name;
    }

    public void passInput(String name, String[] args) {

        if (getName().equalsIgnoreCase(name)) {
            this.name = name;
            this.args = args;
            execute();
        }

    }

    public String getName() {
        return name;
    }

    public String getArg(int index) {
        if (index < 0 || index > args.length || args.length == 0) return "";
        return args[index];
    }

}
