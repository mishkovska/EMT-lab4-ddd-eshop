package mk.ukim.finki.emt.productcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.models.ProductId;
import mk.ukim.finki.emt.productcatalog.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping("/order-item-created")
    public Product orderItemCreated(@RequestBody ProductId productId, @RequestParam int quantity) {
        return this.productService.orderItemCreated(productId, quantity);
    }
}
