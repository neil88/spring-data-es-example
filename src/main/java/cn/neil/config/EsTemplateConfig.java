package cn.neil.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.util.CollectionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaozhikun
 */
@Configuration
@Slf4j
public class EsTemplateConfig {

    @Value("${elasticsearch.clustername:elasticsearch_xiaozhikun}")
    private String esClusterName;

    @Value("${spring.elasticsearch.port:9300}")
    private Integer esPort;

    @Value("#{'${elasticsearch.hosts:127.0.0.1}'.split(',')}")
    private List<String> hosts = new ArrayList<>();


    private Settings settings() {
        Settings settings = Settings.builder()
                .put("cluster.name", esClusterName)
                .put("client.transport.sniff", true).build();
        return settings;
    }

    @Bean
    protected Client buildClient() {
        TransportClient preBuiltTransportClient = new PreBuiltTransportClient(settings());


        if (!CollectionUtils.isEmpty(hosts)) {
            hosts.stream().forEach(h -> {
                try {
                    preBuiltTransportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(h), esPort));
                } catch (UnknownHostException e) {
                    log.error("Error addTransportAddress,with host:" + h, e);
                }
            });
        }
        return preBuiltTransportClient;
    }


    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        return new ElasticsearchTemplate(buildClient());
    }

}
