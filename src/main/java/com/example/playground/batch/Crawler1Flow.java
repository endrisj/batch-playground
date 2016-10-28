package com.example.playground.batch;

import java.io.IOException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Crawler1Flow extends GenericCrawlerFlow {

    @Bean
    public Step step1(ItemReader<String> reader1, ItemProcessor<String, CrawlingDto> processor, ItemWriter<CrawlingDto> writer) {
        return makeStep("step1", reader1, processor, writer);
    }

    @Bean
    public Flow flow1(Step step1) {
        return makeFlow(step1);
    }

    @Bean
    public ItemStreamReader<String> reader1() throws IOException {
        return makeReader("crawler1-data.json");
    }
}
