package me.samuelh2005.java_mobile.gsup;

public record GsupMessage(int messageType, Object[] ieis, int[] codes) {}