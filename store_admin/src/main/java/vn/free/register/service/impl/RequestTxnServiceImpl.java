package vn.free.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.free.register.constant.ResponseCode;
import vn.free.register.constant.StatusCode;
import vn.free.register.entity.RequestTxn;
import vn.free.register.entity.User;
import vn.free.register.repository.RequestTxnRepository;
import vn.free.register.repository.UserRepository;
import vn.free.register.request.request_txn.RequestTxnRQ;
import vn.free.register.request.request_txn.RequestTxnSearch;
import vn.free.register.request.user.UserRQ;
import vn.free.register.request.user.UserSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.RequestTxnService;
import vn.free.register.service.UserService;
import vn.free.register.util.DateUtil;
import vn.free.register.util.SecurityUtil;

import java.util.Collections;
import java.util.Date;

@Slf4j
@Service
public class RequestTxnServiceImpl implements RequestTxnService {

    private static String OBJECT = "lịch hẹn";
    @Autowired
    private RequestTxnRepository reqTxnRepository;


    @Override
    public ResponseDTO getRequestTxnById(RequestTxnRQ requestTxnRQ) {
        try {
            log.debug("Begin get requestTxn by id: {}", requestTxnRQ);

            if (requestTxnRQ.getId() == null || requestTxnRQ.getId() == 0) {
                log.debug("Data request invalid.");
                return ResponseDTO.builder()
                        .code(ResponseCode.INVALID_DATA.getCode())
                        .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                        .build();
            }
            RequestTxn requestTxn = reqTxnRepository.findByID(requestTxnRQ.getId());

            if (requestTxn == null) {
                log.debug("requestTxn not exists. ID: {}", requestTxn.getId());
                return ResponseDTO.builder()
                        .code(ResponseCode.INVALID_DATA.getCode())
                        .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                        .build();
            }

            log.debug("Get requestTxn by id successful. {}", requestTxn);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(requestTxn)
                    .build();
        } catch (Exception ex) {
            log.error("Get requestTxn by id ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
    }

    @Override
    public ResponseDTO searchRequestTxn(RequestTxnSearch search) {
        try {
            log.debug("Begin search requestTxn: {}", search);
            Pageable pageable = PageRequest.of(
                    search.getPageIndex() - 1,
                    search.getPageSize(),
                    Sort.by("id").descending());

            Date fromDate = null;
            Date toDate = null;
            if (StringUtils.isNotEmpty(search.getFromDate())) {
                fromDate = DateUtil.convertStringToDate(search.getFromDate(), "yyyy-MM-dd");
            }
            if (StringUtils.isNotEmpty(search.getToDate())) {
                toDate = DateUtil.convertStringToDate(search.getToDate(), "yyyy-MM-dd");
            }

            Page<RequestTxn> page = reqTxnRepository.search(
                    search.getKeyword(),
                    search.getStatus(),
                    fromDate,
                    toDate,
                    pageable);

            final long total = page.getTotalElements();
            log.debug("Search requestTxn successful. {}", page);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(page.toList())
                    .total(total)
                    .build();
        } catch (Exception ex) {
            log.error("Search requestTxn ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
    }

    @Override
    public ActionRes createRequestTxn(RequestTxnRQ requestTxnRQ) {

        try {
            log.debug("Begin create requestTxn: {}", requestTxnRQ);
            if (StringUtils.isEmpty(requestTxnRQ.getCustomerMobile())
                    || StringUtils.isEmpty(requestTxnRQ.getCustomerName())
                    || StringUtils.isEmpty(requestTxnRQ.getDateRequest())
            ) {
                log.debug("Data request invalid.");
                return ActionRes.builder()
                        .code(ResponseCode.INVALID_DATA.getCode())
                        .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                        .build();
            }

            Date dateReq = DateUtil.convertStringToDate(
                    requestTxnRQ.getDateRequest(),
                    DateUtil.DATE_TIME_FORMAT_V1);

            RequestTxn requestTxn = RequestTxn.builder()
                    .customerName(requestTxnRQ.getCustomerName())
                    .customerMobile(requestTxnRQ.getCustomerMobile())
                    .dateRequest(dateReq)
                    .status(StatusCode.ACTIVE.getCode())
                    .createdBy(SecurityUtil.getCurrentUsernameId())
                    .createdDate(new Date())
                    .build();

            reqTxnRepository.save(requestTxn);
            log.debug("Create requestTxn successful.");
            return ActionRes.builder()
                    .code(ResponseCode.CREATE_SUCCESS.getCode())
                    .message(ResponseCode.CREATE_SUCCESS.getDesc(OBJECT))
                    .build();
        } catch (Exception exception) {

            log.error("Create requestTxn ...fail.", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

    @Override
    public ActionRes updateStatusRequestTxn(RequestTxnRQ requestTxnRQ) {
        try {
            log.debug("Begin update status requestTxn: {}", requestTxnRQ);
            RequestTxn requestTxn = reqTxnRepository.findByID(requestTxnRQ.getId());
            if (requestTxn == null) {
                log.debug("User not Exists. userId: {} ", requestTxnRQ.getId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }

            requestTxn.setStatus(requestTxnRQ.getStatus());
            requestTxn.setUpdatedBy(SecurityUtil.getCurrentUsernameId());
            requestTxn.setUpdatedDate(new Date());
            reqTxnRepository.save(requestTxn);

            log.error("Update status requestTxn successful.");
            return ActionRes.builder()
                    .code(ResponseCode.UPDATE_SUCCESS.getCode())
                    .message(ResponseCode.UPDATE_SUCCESS.getDesc(OBJECT))
                    .build();

        } catch (Exception exception) {
            log.error("Update status request ...fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

}
