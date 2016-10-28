package com.example.playground.batch;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
@Import({Crawler1Flow.class, Crawler2Flow.class, Crawler3Flow.class, Crawler4Flow.class, Crawler5Flow.class})
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Bean
    public Job job(Step step1, Step step2, Step step3, Step step4, Step step5) throws Exception {
        return jobs.get("job1")
            .start((new Crawler1Flow()).flow1(step1))
            .split(new SimpleAsyncTaskExecutor()).add((new Crawler2Flow()).flow2(step2))
            .split(new SimpleAsyncTaskExecutor()).add((new Crawler3Flow()).flow3(step3))
            .split(new SimpleAsyncTaskExecutor()).add((new Crawler4Flow()).flow4(step4))
            .split(new SimpleAsyncTaskExecutor()).add((new Crawler5Flow()).flow5(step5))
            .end()
            .build();
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
