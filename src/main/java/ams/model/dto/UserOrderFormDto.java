package ams.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserOrderFormDto {

    public UserDisplayDto userDisplayDto = new UserDisplayDto();

    public List<OrderDisplayDto> orderDisplayDto = new ArrayList<>();
}
