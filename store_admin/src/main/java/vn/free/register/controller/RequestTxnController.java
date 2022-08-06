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
import vn.free.register.request.request_txn.RequestTxnRQ;
import vn.free.register.request.request_txn.RequestTxnSearch;
import vn.free.register.request.user.UserRQ;
import vn.free.register.request.user.UserSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.RequestTxnService;
import vn.free.register.service.UserService;

/**
 * @author quangnv
 */
@Slf4j
@RestController
@RequestMapping("/request-txn")
public class RequestTxnController {


    @Autowired
    private RequestTxnService requestTxnService;

    private static Gson gson = new Gson();

    @PostMapping("/search")
    public ResponseEntity<Object> searchRequestTxn(@RequestBody RequestTxnSearch search) {

        log.info("Input search requestTxn: {}", gson.toJson(search));
        ResponseDTO response = requestTxnService.searchRequestTxn(search);
        log.info("Output search requestTxn: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update-status")
    public ResponseEntity<Object> updateStatusRequestTxn(@RequestBody RequestTxnRQ requestTxnRQ) {

        log.info("Input update status requestTxn: {}", gson.toJson(requestTxnRQ));
        ActionRes response = requestTxnService.updateStatusRequestTxn(requestTxnRQ);
        log.info("Output update status requestTxn: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/get-by-id")
    public ResponseEntity<Object> getByID(@RequestBody RequestTxnRQ requestTxnRQ) {

        log.info("Input get requestTxn by id: {}", gson.toJson(requestTxnRQ));
        ResponseDTO response = requestTxnService.getRequestTxnById(requestTxnRQ);
        log.info("Output get requestTxn by id: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

}
