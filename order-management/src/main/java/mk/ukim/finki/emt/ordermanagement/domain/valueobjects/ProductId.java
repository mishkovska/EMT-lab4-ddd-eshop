package mk.ukim.finki.emt.ordermanagement.domain.valueobjects;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ProductId extends DomainObjectId {

    private ProductId() {
        super(ProductId.randomId(ProductId.class).getId());
    }
    public ProductId(@NonNull String uuid) {
        super(uuid);
    }
}
