package org.kvlt.core.commands;

import org.kvlt.core.packets.Destination;
import org.kvlt.core.packets.proxy.BroadcastPacket;
import org.kvlt.core.utils.Log;
import org.kvlt.core.utils.Printer;

import java.util.Arrays;

public class BroadcastCommand extends Command {

    public BroadcastCommand() {
        super("bc");
        addAliases("broadcast");
    }

    @Override
    protected boolean execute() {
        if (getArgs().length < 1) {
            Printer.$("Введите направление и/или текст");
            return false;
        }

        String destination = getArg(0);
        boolean hasDestination;
        int startFrom;
        int minArgs;

        hasDestination = destination.startsWith("@");

        startFrom = hasDestination ? 1 : 0;
        minArgs = startFrom + 1;

        String[] wordsArray = Arrays.copyOfRange(getArgs(), startFrom, getArgs().length);
        String words = String.join(" ", wordsArray);

        if (getArgs().length < minArgs) {
            Printer.$("Введите текст сообщения!");
            return false;
        }

        String finalDestination = hasDestination ? destination : "@all";

        new BroadcastPacket(words).send(Destination.BUNGEE, finalDestination);
        Log.$(words);

        return true;
    }
}
