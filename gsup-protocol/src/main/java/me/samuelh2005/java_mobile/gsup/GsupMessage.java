package me.samuelh2005.java_mobile.gsup;

import me.samuelh2005.java_mobile.gsup.ieis.IEI;

public record GsupMessage(int messageType, IEI[] ieis) {}