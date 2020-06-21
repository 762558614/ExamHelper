package com.bootstrap;

import java.net.InetSocketAddress;
import java.security.cert.CertificateException;

import com.net.ExamServer;
import com.net.SecureExamServerInitializer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class SecureExamServer extends ExamServer {

	private final SslContext context;

	public SecureExamServer(SslContext context) {
		this.context = context;
	}

	@Override
	protected ChannelInitializer<Channel> createInitializer(ChannelGroup channelGroup) {
		return new SecureExamServerInitializer(channelGroup, context);
	}

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Please give port as argument");
			System.exit(1);
		}
		int port = Integer.parseInt(args[0]);
		SelfSignedCertificate cert;
		try {
			cert = new SelfSignedCertificate();
			SslContext context = SslContext.newServerContext(cert.certificate(), cert.privateKey());
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
