package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class ProductInOrderId extends DomainObjectId {

    private ProductInOrderId() {
        super(ProductInOrderId.randomId(ProductInOrderId.class).getId());
    }
    public ProductInOrderId(@NonNull String uuid) {
        super(uuid);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
