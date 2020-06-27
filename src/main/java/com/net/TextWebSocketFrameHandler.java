package com.net;

import com.alibaba.fastjson.JSON;
import com.data.QuestionCache;
import com.entity.Question;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	private final ChannelGroup group;

	public TextWebSocketFrameHandler(ChannelGroup group) {
		this.group = group;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		System.out.println("channelActive.");
		group.add(ctx.channel());
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		int id = Integer.valueOf(msg.text());
		Question q = QuestionCache.getInstance().getQueestion("12656", id);
		String json = JSON.toJSONString(q);
		TextWebSocketFrame res = new TextWebSocketFrame(json);
		group.writeAndFlush(res.retain());
	}
}
