package vn.free.register.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "language")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "listening", nullable = false)
    private Integer listening;

    @Column(name = "speaking", nullable = false)
    private Integer speaking;

    @Column(name = "reading", nullable = false)
    private Integer reading;

    @Column(name = "writing", nullable = false)
    private Integer writing;

    @Column(name = "register_id", nullable = false)
    private Long registerId;

}
