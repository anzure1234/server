package ams.model.dto;

import ams.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderDisplayDto {

    private Long orderId;

    private LocalDate orderDate;

    private Double totalAmount;

    private Status status;

    private Long userId;
}
