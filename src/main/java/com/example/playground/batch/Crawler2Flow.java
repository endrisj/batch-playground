package com.example.playground.batch;

import java.io.IOException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;

public class Crawler2Flow extends GenericCrawlerFlow {

    @Bean
    public Step step2(ItemReader<String> reader2, ItemProcessor<String, CrawlingDto> processor, ItemWriter<CrawlingDto> writer) {
        return makeStep("step2", reader2, processor, writer);
    }

    @Bean
    public Flow flow2(Step step2) {
        return makeFlow(step2);
    }

    @Bean
    public ItemStreamReader<String> reader2() throws IOException {
        return makeReader("crawler2-data.json");
    }
}
