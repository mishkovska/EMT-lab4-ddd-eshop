package mk.ukim.finki.emt.productcatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.exceptions.ProductNotFoundException;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.models.ProductId;
import mk.ukim.finki.emt.productcatalog.domain.repository.ProductRepository;
import mk.ukim.finki.emt.productcatalog.services.ProductService;
import mk.ukim.finki.emt.productcatalog.services.form.ProductForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findById(ProductId id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product createProduct(ProductForm productForm) {
        Product product = Product.build(productForm.getProductName(), productForm.getProductDescription(), productForm.getSales(), productForm.getProductPrice());
        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public Product orderItemCreated(ProductId productId, int quantity) {
        Product product = findById(productId);
        product.addSales(quantity);
        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public Product orderItemRemoved(ProductId productId, int quantity) {
        Product product = findById(productId);
        product.removeSales(quantity);
        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
