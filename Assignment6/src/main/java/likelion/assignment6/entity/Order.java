package likelion.assignment6.entity;

import jakarta.persistence.*;
import likelion.assignment6.dto.OrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name="orders")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String orderName;
    private Long orderPrice;

    @Column(name="order_date")
    @CreatedDate
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
        orderProduct.setOrders(this);
    }

    public static Order newOrder(Member member, String orderName, Long orderPrice, List<OrderProduct> orderProducts) {
        Order order = new Order();
        order.setMember(member);
        order.setOrderName(orderName);
        order.setOrderPrice(orderPrice);
        for (OrderProduct orderProduct : orderProducts){
            order.addOrderProduct(orderProduct);
        }
        return order;
    }

    public static OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto(order.getOrderName(), order.getOrderPrice(), order.getOrderDate());
        return orderDto;
    }



}