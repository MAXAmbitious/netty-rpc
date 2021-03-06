package com.beidao.netty.dubbo.sever;

import com.beidao.netty.dubbo.decoder.NettyNetMessageRPCDecoder;
import com.beidao.netty.dubbo.encoder.NettyNetMessageRPCEncoder;
import com.beidao.netty.dubbo.facade.api.message.DubboRequestMessage;
import com.beidao.netty.dubbo.facade.api.message.DubboResponseMessage;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author beidao
 *
 */
public class DubboServer {

	private int port;

	public DubboServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline p = ch.pipeline();
							p.addLast(new NettyNetMessageRPCDecoder(DubboRequestMessage.class));
							p.addLast(new NettyNetMessageRPCEncoder(DubboResponseMessage.class));
							ch.pipeline().addLast(new DubboServerHandler());
						}
					});

			// Bind and start to accept incoming connections.
			ChannelFuture f = b.bind(port).sync(); // (7)

			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new DubboServer(8080).run();
	}

}
