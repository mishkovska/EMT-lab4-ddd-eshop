package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.ProductId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_in_order")
@Getter
public class ProductInOrder extends AbstractEntity<ProductInOrderId> {

    private Money itemPrice;

    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name = "product_id", nullable = false))
    private ProductId productId;

    private ProductInOrder() {
        super(DomainObjectId.randomId(ProductInOrderId.class));
    }

    public ProductInOrder(@NonNull ProductId productId, @NonNull Money itemPrice, int quantity) {
        super(ProductInOrderId.randomId(ProductInOrderId.class));
        this.productId = productId;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public Money subtotal() {
        return itemPrice.multiply(quantity);
    }
}
