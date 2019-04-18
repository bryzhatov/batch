package batch.config;

import batch.job1.FileReader;
import batch.job1.TemporarySaver;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmitry Bryzhatov
 * @since 2019-04-18
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    Job readFromFileToTemporaryTableJob() {
        return jobBuilderFactory.get("readJob")
                .start(read())
                .build();
    }

    @Bean
    Step read() {
        return stepBuilderFactory.get("readStep")
                .<String, String>chunk(8)
                    .reader(itemReader())
                    .writer(itemWriter())
                .build();
    }

    @Bean
    Step filter() {
        return stepBuilderFactory.get("filterStep")
                .<String, String>chunk(2)
                    .reader(itemReader())
                    .writer(itemWriter())
                .build();
    }

    @Bean
    ItemReader<String> itemReader() {
        return new FileReader();
    }

    @Bean
    ItemWriter<String> itemWriter(){
        return new TemporarySaver();
    }
}
