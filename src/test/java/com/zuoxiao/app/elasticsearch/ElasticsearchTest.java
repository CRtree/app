package com.zuoxiao.app.elasticsearch;

import com.zuoxiao.app.pojo.TUser;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author zuoxiao
 * @date 2020/12/1 18:25
 */
@Log4j2
@SpringBootTest
public class ElasticsearchTest {

    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Autowired
    ElasticsearchRestTemplate template;

    @Test
    public void test() throws IOException {

        final GetIndexRequest indexRequest = new GetIndexRequest("tuser");
        final boolean exists = restHighLevelClient.indices().exists(indexRequest, RequestOptions.DEFAULT);
        log.info("1.索引是否存在:{}",exists);


        GetRequest indexRequest2 = new GetRequest("tuser","1");
        GetResponse response = restHighLevelClient.get(indexRequest2, RequestOptions.DEFAULT);
        log.info("2.该id的document是否存在:{}",response.toString());
        Map<String,Object> source = response.getSource();
        log.info("3.document的内容:{}",source.toString());
    }

    @Test
    public void test2(){
        IndexOperations ops = template.indexOps(TUser.class);
        boolean exists = ops.exists();
        log.info("1.索引是否存在:{}",exists);

        //和上一样
        TUser tUser = template.get("10009", TUser.class);
        log.info("3.document的内容:{}",tUser.toString());

    }

}
