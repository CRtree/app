package com.zuoxiao.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/12/1 19:30
 */
@AllArgsConstructor
@Data
@Document(indexName = "tuser")
public class TUser {
    @Id
    private int id;
    @Field
    private String account_num;
    @Field
    private String name;
    @Field
    private int age;
    @Field
    private String desc;
    @Field
    private long create_time;
}
