package batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dmitry Bryzhatov
 * @since 2019-04-18
 */
@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
    }
}

//    RowMapper<Lang> rowMapper = new BeanPropertyRowMapper<>(Lang.class);
//    List<Lang> langList = jdbcTemplate.query("SELECT * FROM language", rowMapper);
//    System.out.println(langList);

//    int id = jdbcTemplate.update("INSERT INTO language (name, batch_id) VALUES (?, ?)", "java", 0);