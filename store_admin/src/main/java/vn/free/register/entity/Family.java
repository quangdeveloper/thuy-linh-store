package vn.free.register.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "family")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "relation", nullable = false)
    private String relation;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "job", nullable = false)
    private String job;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "register_id", nullable = false)
    private Long registerId;

}
