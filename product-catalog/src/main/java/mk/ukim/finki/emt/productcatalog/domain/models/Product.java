package mk.ukim.finki.emt.productcatalog.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
public class Product extends AbstractEntity<ProductId> {

    private String productName;
    private String productDescription;
    private int sales = 0;
    private Money productPrice;

    private Product() {
        super(ProductId.randomId(ProductId.class));
    }

    public static Product build(String productName, String productDescription, int sales, Money productPrice) {
        Product product = new Product();
        product.productName = productName;
        product.productDescription = productDescription;
        product.sales = sales;
        product.productPrice = productPrice;
        return product;
    }

    public void addSales(int quantity) {
        this.sales += quantity;
    }

    public void removeSales(int quantity) {
        this.sales -= quantity;
    }
}
