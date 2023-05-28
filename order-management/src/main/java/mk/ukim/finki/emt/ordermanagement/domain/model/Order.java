package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.Product;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant orderedOn;

    @Column(name = "order_currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ProductInOrder> productInOrderList;

    private Order() {
        super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant now, Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.orderedOn = now;
        this.currency = currency;
        productInOrderList = new HashSet<>();
    }

    public Money total() {
        return productInOrderList.stream().map(ProductInOrder::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    //make a call to product-catalog module to increase number of sales
    public ProductInOrder addItem(@NonNull Product product, int quantity) {
        Objects.requireNonNull(product, "product must not be null");
        var item = new ProductInOrder(product.getId(), product.getPrice(), quantity);
        productInOrderList.add(item);
        return item;
    }

    public void removeItem(@NonNull ProductInOrderId productInOrderId) {
        Objects.requireNonNull(productInOrderId, "productInOrderId must not be null");
        productInOrderList.removeIf(v -> v.getId().equals(productInOrderId));
    }
}
