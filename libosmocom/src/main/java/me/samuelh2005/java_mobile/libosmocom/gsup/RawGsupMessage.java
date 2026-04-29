package me.samuelh2005.java_mobile.libosmocom.gsup;

public record RawGsupMessage(int messageType, Object[] ieis) {}