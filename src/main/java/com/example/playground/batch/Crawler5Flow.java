package com.example.playground.batch;

import java.io.IOException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;

public class Crawler5Flow extends GenericCrawlerFlow {

    @Bean
    public Step step5(ItemReader<String> reader5, ItemProcessor<String, CrawlingDto> processor, ItemWriter<CrawlingDto> writer) {
        return makeStep("step5", reader5, processor, writer);
    }

    @Bean
    public Flow flow5(Step step5) {
        return makeFlow(step5);
    }

    @Bean
    public ItemStreamReader<String> reader5() throws IOException {
        return makeReader("crawler5-data.json");
    }
}
