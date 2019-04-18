package batch.job1.steps.filter;

import batch.entity.Lang;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * @author Dmitry Bryzhatov
 * @since 2019-04-18
 */
public class ReadTemporary implements ItemReader<String>, StepExecutionListener {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List<Lang> langs;
    private int size;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        RowMapper<Lang> rowMapper = new BeanPropertyRowMapper<>(Lang.class);
        langs = jdbcTemplate.query("SELECT * FROM lang", rowMapper);
        size = langs.size();
    }

    @Override
    public String read() {
        if (size > 0) {
            return langs.get(--size).getName();
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
