package net.enot.darta.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LoggerFormatter extends Formatter {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    public String format(LogRecord record) {
        return "[" + dateFormat.format(new Date(record.getMillis())) + "/" + record.getLevel().getLocalizedName() + "]: " + formatMessage(record) + LoggerColor.RESET + System.getProperty("line.separator");
    }

}
