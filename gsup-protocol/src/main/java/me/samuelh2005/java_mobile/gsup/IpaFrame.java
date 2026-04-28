package me.samuelh2005.java_mobile.gsup;

import io.netty.buffer.ByteBuf;

public record IpaFrame(int streamId, int proto, ByteBuf payload) {}