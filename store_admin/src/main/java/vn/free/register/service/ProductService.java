package vn.free.register.service;

import vn.free.register.request.product.ProductRQ;
import vn.free.register.request.product.ProductSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;

public interface ProductService {

    ResponseDTO searchProduct(ProductSearch search);

    ResponseDTO getProductById(ProductRQ search);

    ActionRes createProduct(ProductRQ productRQ);

    ActionRes updateProduct(ProductRQ productRQ);

    ActionRes updateStatusProduct(ProductRQ productRQ);
}
