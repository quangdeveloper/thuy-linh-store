package vn.free.register.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.free.register.entity.Customer;
import vn.free.register.request.customer.CustomerRQ;
import vn.free.register.request.customer.CustomerSearch;
import vn.free.register.request.user.UserRQ;
import vn.free.register.request.user.UserSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.CustomerService;
import vn.free.register.service.UserService;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    private static Gson gson = new Gson();

    @PostMapping("/get-all")
    public ResponseEntity<Object> getAllCustomer() {

        log.info("Input get all customer");
        ResponseDTO response = customerService.getAllCustomer();
        log.info("Output get all customer: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchCustomer(@RequestBody CustomerSearch search) {

        log.info("Input search customer: {}", gson.toJson(search));
        ResponseDTO response = customerService.searchCustomer(search);
        log.info("Output search customer: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/get-by-id")
    public ResponseEntity<Object> getCustomerById(@RequestBody CustomerRQ customerRQ) {

        log.info("Input create customer: {}", gson.toJson(customerRQ));
        ResponseDTO response = customerService.getCustomerByID(customerRQ);
        log.info("Output create customer: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/create")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerRQ customerRQ) {

        log.info("Input create customer: {}", gson.toJson(customerRQ));
        ActionRes response = customerService.createCustomer(customerRQ);
        log.info("Output create customer: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateCustomer(@RequestBody CustomerRQ customerRQ) {

        log.info("Input update customer: {}", gson.toJson(customerRQ));
        ActionRes response = customerService.updateCustomer(customerRQ);
        log.info("Output update customer: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update-status")
    public ResponseEntity<Object> updateStatusCustomer(@RequestBody CustomerRQ customerRQ) {

        log.info("Input update status customer: {}", gson.toJson(customerRQ));
        ActionRes response = customerService.updateStatusCustomer(customerRQ);
        log.info("Output update status customer: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }


}
