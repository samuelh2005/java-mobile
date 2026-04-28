package me.samuelh2005.java_mobile.msc;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import me.samuelh2005.java_mobile.gsup.GsupClient;
import me.samuelh2005.java_mobile.gsup.GsupHandler;
import me.samuelh2005.java_mobile.gsup.GsupMessage;
import me.samuelh2005.java_mobile.gsup.ieis.ImsiIEI;
import me.samuelh2005.java_mobile.gsup.ieis.IEIType;
import me.samuelh2005.java_mobile.gsup.codec.GsupMessageEncoder;

public class App implements GsupHandler {
    
    private GsupClient client;

    public static void main(String[] args) {
        new App().run();
    }
    
    public void run() {
        client = new GsupClient(this);
        ChannelFuture future = client.connect("localhost", 4222);
        future.awaitUninterruptibly();
        
        if (future.isSuccess()) {
            System.out.println("Connected to HLR");
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            sendSendAuthenticationInfo();
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            client.shutdown();
        }
    }
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, GsupMessage msg) {
        int type = msg.messageType();
        
        System.out.println("Received message type: 0x" + Integer.toHexString(type));
        
        if (type == 0x03) {
            Object[] ieis = msg.ieis();
            int[] codes = msg.codes();
            for (int i = 0; i < ieis.length; i++) {
                System.out.println("  IEI: 0x" + Integer.toHexString(codes[i]) + 
                    " value=" + bytesToHex(IEIType.encode(codes[i], ieis[i])));
            }
        }
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Channel active");
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("Channel inactive - disconnected from HLR");
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Exception: " + cause.getMessage());
    }
    
    private void sendSendAuthenticationInfo() {
        String imsi = "001010123456789";
        
        Object[] ieis = new Object[] {
            ImsiIEI.encode(imsi)
        };
        int[] codes = new int[] {
            ImsiIEI.CODE
        };
        
        GsupMessage authRequest = new GsupMessage(0x02, ieis, codes);
        
        client.send(authRequest);
        
        System.out.println("Sent SendAuthenticationInfo request for IMSI: 001010123456789");
    }
    
    public void shutdown() {
        if (client != null) {
            client.shutdown();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
}