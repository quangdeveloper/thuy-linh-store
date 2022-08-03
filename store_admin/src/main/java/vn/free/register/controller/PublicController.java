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
@RequestMapping("/pub")
public class PublicController {


    @Autowired
    private RequestTxnService requestTxnService;

    private static Gson gson = new Gson();

    @PostMapping("/request-txn")
    public ResponseEntity<Object> createRequestTxn(@RequestBody RequestTxnRQ requestTxnRQ) {

        log.info("Input create requestTxn: {}", gson.toJson(requestTxnRQ));
        ActionRes response = requestTxnService.createRequestTxn(requestTxnRQ);
        log.info("Output create user: {}", gson.toJson(response));
        return ResponseEntity.ok().body(response);
    }

}
