package kz.trankwilizator.todo.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 150)
    private String body;

    @ColumnDefault("false")
    private Boolean completed;

    public Task() {

    }
}
