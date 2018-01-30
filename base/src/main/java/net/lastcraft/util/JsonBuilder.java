package net.lastcraft.util;

public class JsonBuilder {

    private StringBuilder json = new StringBuilder();

    public JsonBuilder addText(String text) {
        text = proccess(text);
        if (json.length() != 0) {
            json.append(",");
        }
        json.append("{\"text\":\"").append(text).append("\"}");
        return this;
    }

    public JsonBuilder addTextHover(String text, String hoverText) {
        text = proccess(text);
        if (json.length() != 0) {
            json.append(",");
        }
        if (json.length() != 0) {
            json.append(",");
        }

        json
                .append("{\"text\":\"")
                .append(text)
                .append("\", \"hoverEvent\":{\"action\":\"show_text\", \"value\": \"")
                .append(hoverText).append("\"}}");
        return this;
    }

    public JsonBuilder addRunCommand(String text, String command, String hoverText) {
        text = proccess(text);
        if (json.length() != 0) {
            json.append(",");
        }

        json
                .append("{\"text\":\"")
                .append(text)
                .append("\", \"hoverEvent\":{\"action\":\"show_text\", \"value\": \"")
                .append(hoverText).append("\"}, \"clickEvent\":{\"action\":\"run_command\", \"value\":\"")
                .append(command).append("\"}}");

        return this;
    }

    public JsonBuilder addOpenUrl(String text, String url, String hoverText) {
        text = proccess(text);
        if (json.length() != 0) {
            json.append(",");
        }

        json
                .append("{\"text\":\"").append(text).append("\", " +
                "\"hoverEvent\":{\"action\":\"show_text\", \"value\": \"").append(hoverText).append("\"}, " +
                "\"clickEvent\":{\"action\":\"open_url\", \"value\":\"").append(url).append("\"}" +
                "}");

        return this;
    }

    public JsonBuilder addSuggestCommand(String text, String command, String hoverText) {
        text = proccess(text);
        if (json.length() != 0) {
            json.append(",");
        }

        json
                .append("{\"text\":\"")
                .append(text)
                .append("\", \"hoverEvent\":{\"action\":\"show_text\", \"value\": \"")
                .append(hoverText).append("\"}, \"clickEvent\":{\"action\":\"suggest_command\", \"value\":\"")
                .append(command).append("\"}}");

        return this;
    }

    private static String proccess(String cur) {
        StringBuilder n = new StringBuilder();
        StringBuilder now = new StringBuilder();
        for (int i = 0; i < cur.length();) {
            if (cur.charAt(i) == '§' && i + 1 < cur.length()) {
                now = new StringBuilder();
                while (i + 1 < cur.length() && cur.charAt(i) == '§') {
                    now.append("").append(cur.charAt(i)).append(cur.charAt(i + 1));
                    i += 2;
                }
            }
            if (i >= cur.length()) {
                break;
            }
            if (cur.charAt(i) != ' ') {
                n.append(now).append(cur.charAt(i));
            }
            else {
                n.append("").append(cur.charAt(i));
            }
            ++i;
        }
        return n.toString();
    }

    @Override
    public String toString() {
        return "[" + json + "]";
    }
}
