package priv.mm.netty.protocol.discard;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * DiscardServerHandler
 *
 * @author maodh
 * @since 2018/6/24
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

    @Test
    public void unitTest() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new DiscardServerHandler());
        embeddedChannel.writeInbound("Hello World");
        Assert.assertFalse(embeddedChannel.finish());
    }
}
