package me.samuelh2005.java_mobile.libosmocom.app;

import io.netty.channel.ChannelHandlerContext;

/**
 * Handler interface for incoming GSUP messages.
 * Implement this to process messages received from the HLR.
 */
public interface GsupHandler {
    
    /**
     * Called when a GSUP message is received from the HLR.
     * 
     * @param ctx the channel handler context
     * @param msg the received GsupMessage
     */
    void messageReceived(ChannelHandlerContext ctx, GsupMessage msg);
    
    /**
     * Called when the channel becomes active (connected to HLR).
     * 
     * @param ctx the channel handler context
     */
    default void channelActive(ChannelHandlerContext ctx) {}
    
    /**
     * Called when the channel becomes inactive (disconnected from HLR).
     * 
     * @param ctx the channel handler context
     */
    default void channelInactive(ChannelHandlerContext ctx) {}
    
    /**
     * Called when an exception is caught in the channel pipeline.
     * 
     * @param ctx the channel handler context
     * @param cause the exception
     */
    default void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {}
}