package mk.ukim.finki.emt.ordermanagement.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.Product;
import mk.ukim.finki.emt.ordermanagement.service.OrderService;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.ProductInOrderForm;
import mk.ukim.finki.emt.ordermanagement.xport.client.ProductClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final OrderService orderService;
    private ProductClient productClient;

    @PostConstruct
    public void initData() {
        if (orderService.findAll().isEmpty()) {
            List<Product> productList = productClient.findAll();
            Product p1 = productList.get(0);
            Product p2 = productList.get(1);

            ProductInOrderForm productInOrder1 = new ProductInOrderForm();
            productInOrder1.setProduct(p1);
            productInOrder1.setQuantity(8);

            ProductInOrderForm productInOrder2 = new ProductInOrderForm();
            productInOrder2.setProduct(p2);
            productInOrder2.setQuantity(3);

            OrderForm orderForm = new OrderForm();
            orderForm.setCurrency(Currency.MKD);
            orderForm.setItems(Arrays.asList(productInOrder1, productInOrder2));

            OrderId newOrderId = orderService.placeOrder(orderForm);
            Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);
        }
    }
}
