package vn.free.register.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.free.register.entity.RequestTxn;
import vn.free.register.request.product.ProductRQ;
import vn.free.register.request.request_txn.RequestTxnRQ;
import vn.free.register.request.user.UserRQ;
import vn.free.register.request.user.UserSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.ProductService;
import vn.free.register.service.RequestTxnService;
import vn.free.register.service.UserService;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/pub")
public class PublicController {


    @Autowired
    private RequestTxnService requestTxnService;

    @Autowired
    private ProductService productService;

    private static Gson gson = new Gson();

    @PostMapping("/request-txn")
    public ResponseEntity<Object> createRequestTxn(@RequestBody RequestTxnRQ requestTxnRQ) {

        log.info("Input create requestTxn: {}", gson.toJson(requestTxnRQ));
        ActionRes response = requestTxnService.createRequestTxn(requestTxnRQ);
        log.info("Output create requestTxn: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/product/detail")
    public ResponseEntity<Object> getByIdProduct(@RequestBody ProductRQ productRQ) {

        log.info("Input get product by id: {}", gson.toJson(productRQ));
        ResponseDTO response = productService.getProductById(productRQ);
        log.info("Output get product by id: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

}
