package com.example.clientdemo.config;



import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import java.net.InetSocketAddress;

@Slf4j
@Configuration
public class RSocketConfiguration {

    @Bean
    public RSocketRequester rSocketRequester(RSocketStrategies strategies) {
        InetSocketAddress address = new InetSocketAddress("localhost", 7000);

        return RSocketRequester.builder()
                .dataMimeType(MimeType.valueOf(MimeTypeUtils.ALL_VALUE))
                .rsocketStrategies(strategies)
                .connect(TcpClientTransport.create(address))
                .retry()
                .block();
    }
}
