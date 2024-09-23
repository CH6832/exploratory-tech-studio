package com.example.contractmanagement.costandtimetracking;


@Entity
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String description;

    @ManyToOne
    private Contract contract;

    // Getters and setters...
}
