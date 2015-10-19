package springapp.repository.simple;

import java.util.List;

import springapp.domain.Product;
import springapp.repository.ProductDao;

public class InMemoryProductDao implements ProductDao {
    private List<Product> productList;

    public InMemoryProductDao(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void saveProduct(Product prod) {
    	
    }
}