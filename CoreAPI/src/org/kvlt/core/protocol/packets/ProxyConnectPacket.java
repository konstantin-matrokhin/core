package org.kvlt.core.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.protocol.Packet;
import org.kvlt.core.protocol.PacketUtil;

public class ProxyConnectPacket extends Packet<Channel> {

    private String proxy;

    //Обязательно нужен будет пустой конструктор, если есть второй(!) другой. Потому что вызывается .newInstance()
    public ProxyConnectPacket() {}

    //Ну вот и второй пакет. Чтобы его отправить будет достаточно
    //channel.writeAndFlush(new ProxyConnectPacket("sw-lobby1"));
    public ProxyConnectPacket(String proxy) {
        this.proxy = proxy;
    }

    //Когда данные получены и можно работать с пакетом
    @Override
    public void execute(Channel channel) {
        String response = String.format("Прокси-сервер подключен (%s)", proxy);
        System.out.println(response);
        new Proxy(proxy, channel);
    }

    @Override
    public void send() {

    }

    //Когда пакет пришел, разбираем байты
    @Override
    public void readBytes(ByteBuf byteBuf) {
        this.proxy = PacketUtil.readString(byteBuf);
    }

    //Это при отправке пакета
    @Override
    public void writeBytes(ByteBuf byteBuf) {
        PacketUtil.writeString(proxy, byteBuf);
    }

}
