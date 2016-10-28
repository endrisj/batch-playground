package com.example.playground.batch;

import java.io.IOException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;

public class Crawler3Flow extends GenericCrawlerFlow {
    
    @Bean
    public Step step3(ItemReader<String> reader3, ItemProcessor<String, CrawlingDto> processor, ItemWriter<CrawlingDto> writer) {
        return makeStep("step3", reader3, processor, writer);
    }

    @Bean
    public Flow flow3(Step step3) {
        return makeFlow(step3);
    }

    @Bean
    public ItemStreamReader<String> reader3() throws IOException {
        return makeReader("crawler3-data.json");
    }
}
