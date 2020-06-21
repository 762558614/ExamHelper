package com.net;

import javax.net.ssl.SSLEngine;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

public class SecureExamServerInitializer extends ExamServerInitializer {

	private final SslContext context;
	
	public SecureExamServerInitializer(ChannelGroup group, SslContext context) {
		super(group);
		this.context = context;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		super.initChannel(ch);
		SSLEngine engine = context.newEngine(ch.alloc());
		engine.setUseClientMode(false);
		ch.pipeline().addFirst(new SslHandler(engine));
	}
	
	
	
}
