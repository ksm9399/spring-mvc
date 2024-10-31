package spring.spring_mvc.order;

public interface OrderService {

  Order createOrder(Long memberId, String itemName, int itemPrice);
}
