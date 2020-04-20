package com.beidao.netty.dubbo.encoder;

import com.beidao.netty.dubbo.serialize.NettyProtoBufRpcSerialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author beidao
 *
 */
public class NettyNetMessageRPCEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public NettyNetMessageRPCEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (genericClass.isInstance(in)) {
            byte[] data = NettyProtoBufRpcSerialize.serialize(in);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
