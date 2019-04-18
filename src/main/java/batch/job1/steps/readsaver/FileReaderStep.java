package batch.job1.steps.readsaver;

import lombok.extern.log4j.Log4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Dmitry Bryzhatov
 * @since 2019-04-18
 */
@Log4j
public class FileReaderStep implements ItemReader<String>, StepExecutionListener {
    private Scanner fileScanner;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        try {
            fileScanner = new Scanner(new File("/Users/dbryzhatov/Desktop/batch/src/main/resources/incoming/lang.txt"));
        } catch (FileNotFoundException e) {
            log.error("Error with access of file.", e);
        }
    }

    @Override
    public String read() {
        return fileScanner != null && fileScanner.hasNext() ? fileScanner.nextLine() : null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        fileScanner.close();
        return ExitStatus.COMPLETED;
    }
}
