package likelion.assignment6.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CreateOrderDto {
    private AddOrderDto order;
    private List<AddOrderProductDto> orderProducts;
    private PaymentDetailDto paymentDetail;
}
