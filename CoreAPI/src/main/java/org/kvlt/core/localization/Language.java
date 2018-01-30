package org.kvlt.core.localization;

public enum Language {

    RUSSIAN("russian"),
    ENGLISH("english"),
    UKRAINIAN("ukrainian"),
    BELARUSIAN("belarusian");

    private String name;

    Language(String name) {
        this.name = name;
    }
}
