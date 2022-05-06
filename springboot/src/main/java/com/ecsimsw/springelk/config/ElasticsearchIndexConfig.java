package com.ecsimsw.springelk.config;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class ElasticsearchIndexConfig {

    private final RestHighLevelClient client;

    public ElasticsearchIndexConfig(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void createCode() throws IOException {
        final String indexName = "code";

        if (client.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT)) {
            return;
        }

        final CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0)
        );

        final XContentBuilder mappingBuilder = XContentFactory.jsonBuilder();
        mappingBuilder.startObject();
        {
            mappingBuilder.startObject("properties");
            {
                mappingBuilder.startObject("content");
                mappingBuilder.field("type", "text");
                mappingBuilder.endObject();

                mappingBuilder.startObject("language");
                mappingBuilder.field("type", "keyword");
                mappingBuilder.endObject();

                mappingBuilder.startObject("star");
                mappingBuilder.field("type", "integer");
                mappingBuilder.endObject();

                mappingBuilder.startObject("url");
                mappingBuilder.field("type", "keyword");
                mappingBuilder.endObject();
            }
            mappingBuilder.endObject();
        }
        mappingBuilder.endObject();

        request.mapping("_doc", mappingBuilder);
        client.indices().create(request, RequestOptions.DEFAULT);
    }
}
