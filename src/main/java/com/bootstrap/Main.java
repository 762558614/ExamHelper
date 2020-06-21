package com.bootstrap;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetSocketAddress;

import com.net.ExamServer;

import io.netty.channel.ChannelFuture;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class Main {

	public static void main(String[] args) throws IOException {
//        if (args.length != 1) {
//            System.err.println("Please give port as argument");
//            System.exit(1);
//        }
//        int port = Integer.parseInt(args[0]);
//        final ExamServer endpoint = new ExamServer();
//        ChannelFuture future = endpoint.start(
//                new InetSocketAddress(port));
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                endpoint.destroy();
//            }
//        });
//        RuntimeMXBean  mnBean = ManagementFactory.getRuntimeMXBean();
//        int pid = Integer.valueOf(mnBean.getName().split("@")[0]).intValue();
//        File pidFile = new File("pid.txt");    
//        if(!pidFile.exists()){
//        	pidFile.createNewFile();
//        }
//        try(FileWriter fileWritter = new FileWriter(pidFile.getName(), false);){
//            fileWritter.write(pid);	
//        }
//        future.channel().closeFuture().syncUninterruptibly();

		if (args.length != 1) {
			System.err.println("Please give port as argument");
			System.exit(1);
		}
		int port = Integer.parseInt(args[0]);
		SelfSignedCertificate cert;
		try {
			cert = new SelfSignedCertificate();
//			File sslKey = new File("resources/3924292_www.hounianci.cn_public.crt");
//			File sslCert = new File("resources/ssl.pem");
			SslContext context = SslContextBuilder.forServer(cert.certificate(), cert.privateKey()).build();
//			SslContext context = SslContextBuilder.forServer(sslCert, sslKey).build();
			final SecureExamServer endpoint = new SecureExamServer(context);
			ChannelFuture future = endpoint.start(new InetSocketAddress(port));
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					endpoint.destroy();
				}
			});
	        future.channel().closeFuture().syncUninterruptibly();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
