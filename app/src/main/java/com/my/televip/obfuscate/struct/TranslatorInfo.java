package com.my.televip.obfuscate.struct;

public class TranslatorInfo {

    private final String original;
    private final String resolved;

    public TranslatorInfo(String original, String resolved) {
        this.original = original;
        this.resolved = resolved;
    }

    public String getOriginal() {
        return this.original;
    }

    public String getResolved() {
        return this.resolved;
    }

}
