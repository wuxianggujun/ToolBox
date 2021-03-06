module com.wuxianggujun.toolbox {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires io.netty.all;
    requires io.netty.buffer;
    requires io.netty.codec;
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
    requires com.jfoenix;
    opens com.wuxianggujun.toolbox to javafx.fxml;
    exports com.wuxianggujun.toolbox.cache;
    exports com.wuxianggujun.toolbox.core;
    opens com.wuxianggujun.toolbox.cache to javafx.fxml;
}