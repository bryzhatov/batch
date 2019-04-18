package batch.job1.steps.readsaver;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author Dmitry Bryzhatov
 * @since 2019-04-18
 */
public class TemporarySaverStep implements ItemWriter<String>, StepExecutionListener {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Long batchId;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        batchId = stepExecution.getId();
    }

    @Override
    public void write(List<? extends String> list) {
        for (String nameLang : list) {
            jdbcTemplate.update("INSERT INTO lang (name, batch_id) VALUES (?, ?)", nameLang, batchId);
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
