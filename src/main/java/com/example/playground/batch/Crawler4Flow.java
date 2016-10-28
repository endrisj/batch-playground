package com.example.playground.batch;

import java.io.IOException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;

public class Crawler4Flow extends GenericCrawlerFlow {

    @Bean
    public Step step4(ItemReader<String> reader4, ItemProcessor<String, CrawlingDto> processor, ItemWriter<CrawlingDto> writer) {
        return makeStep("step4", reader4, processor, writer);
    }

    @Bean
    public Flow flow4(Step step4) {
        return makeFlow(step4);
    }

    @Bean
    public ItemStreamReader<String> reader4() throws IOException {
        return makeReader("crawler4-data.json");
    }
}
