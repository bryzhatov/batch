package batch.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * @author Dmitry Bryzhatov
 * @since 2019-04-18
 */
@Data
@RequiredArgsConstructor
@Table
@Entity
public class Lang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;
    private String name;
    @Column(name = "batch_id")
    private long batchId;
}
