package com.example.playground.batch;

import java.io.IOException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

abstract class GenericCrawlerFlow {

    @Autowired
    private StepBuilderFactory steps;
    
    protected Step makeStep(String stepName, ItemReader<String> reader, ItemProcessor<String, CrawlingDto> processor, ItemWriter<CrawlingDto> writer) {
        return steps.get(stepName)
            .<String, CrawlingDto>chunk(1)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }
    
    protected Flow makeFlow(Step step) {
        return new FlowBuilder<Flow>("flow2").start(step).build();
    }
    
    protected ItemStreamReader<String> makeReader(String filePath) throws IOException {
        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        reader.setLineMapper(new PassThroughLineMapper());
        reader.setResource(new ClassPathResource(filePath));
        return reader;
    }
}
