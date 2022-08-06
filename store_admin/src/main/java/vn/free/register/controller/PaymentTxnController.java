package vn.free.register.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.free.register.entity.PaymentTxn;
import vn.free.register.request.paymnet_txn.PaymentTxnDetailRQ;
import vn.free.register.request.paymnet_txn.PaymentTxnRQ;
import vn.free.register.request.paymnet_txn.PaymentTxnSearch;
import vn.free.register.request.request_txn.RequestTxnRQ;
import vn.free.register.request.request_txn.RequestTxnSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.PaymentTxnService;
import vn.free.register.service.RequestTxnService;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/payment-txn")
public class PaymentTxnController {


    @Autowired
    private PaymentTxnService paymentTxnService;

    private static Gson gson = new Gson();

    @PostMapping("/search")
    public ResponseEntity<Object> searchPaymentTxn(@RequestBody PaymentTxnSearch search) {

        log.info("Input search paymentTxn: {}", gson.toJson(search));
        ResponseDTO response = paymentTxnService.searchPaymentTxn(search);
        log.info("Output search paymentTxn: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update-status")
    public ResponseEntity<Object> updateStatusPaymentTxn(@RequestBody PaymentTxnRQ paymentTxnRQ) {

        log.info("Input update status paymentTxn: {}", gson.toJson(paymentTxnRQ));
        ActionRes response = paymentTxnService.updateStatusPayment(paymentTxnRQ);
        log.info("Output update status paymentTxn: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/get-by-id")
    public ResponseEntity<Object> getByIdPayment(@RequestBody PaymentTxnRQ paymentTxnRQ) {

        log.info("Input get paymentTxn by id: {}", gson.toJson(paymentTxnRQ));
        ResponseDTO response = paymentTxnService.getPaymentTxnById(paymentTxnRQ);
        log.info("Output get paymentTxn by id: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/create")
    public ResponseEntity<Object> createPayment(@RequestBody PaymentTxnRQ paymentTxnRQ) {

        log.info("Input create paymentTxn: {}", gson.toJson(paymentTxnRQ));
        ActionRes response = paymentTxnService.createPayment(paymentTxnRQ);
        log.info("Output create paymentTxn: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updatePayment(@RequestBody PaymentTxnRQ paymentTxnRQ) {

        log.info("Input create paymentTxn: {}", gson.toJson(paymentTxnRQ));
        ActionRes response = paymentTxnService.updatePayment(paymentTxnRQ);
        log.info("Output create paymentTxn: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

}
