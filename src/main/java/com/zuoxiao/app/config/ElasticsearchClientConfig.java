package com.zuoxiao.app.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/12/1 18:23
 */
@Configuration
public class ElasticsearchClientConfig  extends AbstractElasticsearchConfiguration {

    @Override
    @Bean("restHighLevelClient")
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("81.68.238.10:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
