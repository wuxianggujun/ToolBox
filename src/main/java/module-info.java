module com.wuxianggujun.toolbox {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.netty.all;
    requires io.netty.buffer;
    requires io.netty.codec;
//    requires io.netty.codec.dns;
//    requires io.netty.codec.haproxy;
//    requires io.netty.codec.http;
//    requires io.netty.codec.http2;
//    requires io.netty.codec.memcache;
//    requires io.netty.codec.mqtt;
//    requires io.netty.codec.redis;
//    requires io.netty.codec.smtp;
//    requires io.netty.codec.socks;
//    requires io.netty.codec.stomp;
//    requires io.netty.codec.xml;
    requires io.netty.common;
    requires io.netty.handler;
    requires io.netty.handler.proxy;
    requires io.netty.resolver;
    requires io.netty.resolver.dns;
    requires io.netty.transport;
    //requires io.netty.transport.epoll; (native省略-Java 中的保留关键字）
    //requires io.netty.transport.kqueue; (native省略-Java 中的保留关键字）
    requires io.netty.transport.unix.common;// (native省略-Java 中的保留关键字）
    requires io.netty.transport.rxtx;
    requires io.netty.transport.sctp;
    requires io.netty.transport.udt;


    requires org.slf4j;

    opens com.wuxianggujun.toolbox to javafx.fxml;
    exports com.wuxianggujun.toolbox;
    exports com.wuxianggujun.toolbox.net;
    exports com.wuxianggujun.toolbox.cache;
    opens com.wuxianggujun.toolbox.cache to javafx.fxml;
}