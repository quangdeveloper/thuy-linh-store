package vn.free.register.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.free.register.request.product.ProductRQ;
import vn.free.register.request.product.ProductSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.ProductService;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    private static Gson gson = new Gson();
    

    @PostMapping("/search")
    public ResponseEntity<Object> searchProduct(@RequestBody ProductSearch search) {

        log.info("Input search product: {}", gson.toJson(search));
        ResponseDTO response = productService.searchProduct(search);
        log.info("Output search product: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/get-by-id")
    public ResponseEntity<Object> getByIdProduct(@RequestBody ProductRQ productRQ) {

        log.info("Input get product by id: {}", gson.toJson(productRQ));
        ResponseDTO response = productService.getProductById(productRQ);
        log.info("Output get product by id: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody ProductRQ productRQ) {

        log.info("Input create product: {}", gson.toJson(productRQ));
        ActionRes response = productService.createProduct(productRQ);
        log.info("Output create product: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductRQ productRQ) {

        log.info("Input update product: {}", gson.toJson(productRQ));
        ActionRes response = productService.updateProduct(productRQ);
        log.info("Output update product: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update-status")
    public ResponseEntity<Object> updateStatusProduct(@RequestBody ProductRQ productRQ) {

        log.info("Input update status product: {}", gson.toJson(productRQ));
        ActionRes response = productService.updateStatusProduct(productRQ);
        log.info("Output update status product: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }


}
