package com.example.playground.batch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Step step1(ItemReader<String> reader, ItemProcessor<String, CrawlingDto> processor, ItemWriter<CrawlingDto> writer) {
        return steps.get("step1")
            .<String, CrawlingDto>chunk(1)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }

    @Bean
    public Job job(Step step1) throws Exception {
        return jobs.get("job1")
            //            .incrementer(new RunIdIncrementer())
            .start(step1)
            .build();
    }

    @Bean
    public FlatFileItemReader<String> reader() {
        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("sample-data.json"));
        reader.setLineMapper(new PassThroughLineMapper());
        return reader;
    }

    @Bean
    public ItemProcessor processor() {
        return (ItemProcessor<String, CrawlingDto>) (String i) -> {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(i, CrawlingDto.class);
        };
    }

    @Bean
    public ItemWriter<CrawlingDto> writer() {
        return new ItemWriter<CrawlingDto>() {
            @Override
            public void write(List<? extends CrawlingDto> list) throws Exception {
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.postForLocation("http://localhost:8080/url-to-be-scanned", list.get(0).getFirstCall());
                    restTemplate.postForLocation("http://localhost:8080/famous-people-for-url", list.get(0).getSecondCall());
                    restTemplate.postForLocation("http://localhost:8080/repository-key-for-url", list.get(0).getThirdCall());
                } catch (HttpClientErrorException e) {
                    System.out.println("WHEN SENDING `"+list.get(0)+"` got error: "+e.getMessage());
                }
            }
        };
    }
}
