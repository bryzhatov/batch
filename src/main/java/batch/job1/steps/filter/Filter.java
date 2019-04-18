package batch.job1.steps.filter;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Bryzhatov
 * @since 2019-04-18
 */
public class Filter implements ItemProcessor<String, String>, StepExecutionListener {
    private Map<String, Integer> map;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        map = new HashMap<>();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public String process(String s) {
        return null;
    }
}
