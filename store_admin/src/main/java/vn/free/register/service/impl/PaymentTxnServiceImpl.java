package vn.free.register.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.free.register.constant.ResponseCode;
import vn.free.register.constant.StatusCode;
import vn.free.register.entity.Customer;
import vn.free.register.entity.PaymentTxn;
import vn.free.register.entity.PaymentTxnDetail;
import vn.free.register.repository.CustomerRepository;
import vn.free.register.repository.PaymentTxnDetailRepository;
import vn.free.register.repository.PaymentTxnRepository;
import vn.free.register.request.paymnet_txn.PaymentTxnDetailRQ;
import vn.free.register.request.paymnet_txn.PaymentTxnRQ;
import vn.free.register.request.paymnet_txn.PaymentTxnSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.response.customer.CustomerRP;
import vn.free.register.response.paymnet_txn.PaymentTxnDetailRP;
import vn.free.register.response.paymnet_txn.PaymentTxnRP;
import vn.free.register.service.PaymentTxnService;
import vn.free.register.util.DateUtil;
import vn.free.register.util.SecurityUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PaymentTxnServiceImpl implements PaymentTxnService {

    private static String OBJECT = "giao dịch";
    private static String OBJECT_PAY_DETAIL = "chi tiết giao dịch";
    private static String OBJECT_CUSTOMER = "khách hàng";

    @Autowired
    private PaymentTxnRepository paymentTxnRepository;

    @Autowired
    private PaymentTxnDetailRepository paymentTxnDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public ResponseDTO getPaymentTxnById(PaymentTxnRQ search) {
        try {
            log.debug("Begin get product by id: {}", search);
            PaymentTxn paymentTxn = paymentTxnRepository.findByID(search.getId());
            if (paymentTxn == null) {
                log.debug("PaymentTxn not Existed. id: {} ", search.getId());
                return ResponseDTO.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }
            PaymentTxnRP paymentTxnRP = convertFromPaymentTxn(paymentTxn);
            List<PaymentTxnDetail> payDt = paymentTxnDetailRepository.findByPaymentID(search.getId());

            if (payDt == null || payDt.isEmpty()) {
                log.debug("Get paymentTxn Detail ...fail. pay ID: {} ", search.getId());
                return ResponseDTO.builder()
                        .code(ResponseCode.ERROR.getCode())
                        .message(ResponseCode.ERROR.getDesc(OBJECT_PAY_DETAIL))
                        .build();
            }
            paymentTxnRP.setPaymentTxnDetails(fromListPaymentTxnDetail(payDt));

            log.debug("Get paymentTxn by id successful. {}", paymentTxnRP);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(paymentTxnRP)
                    .build();
        } catch (Exception ex) {
            log.error("Get product by id ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
    }

    private List<PaymentTxnDetailRP> fromListPaymentTxnDetail(List<PaymentTxnDetail> list) {
        List<PaymentTxnDetailRP> listRs = new ArrayList<>();
        for (PaymentTxnDetail pay : list) {
            PaymentTxnDetailRP payRs = PaymentTxnDetailRP.builder()
                    .id(pay.getId())
                    .payId(pay.getPayId())
                    .productId(pay.getProductId())
                    .productPrice(pay.getProductPrice())
                    .productQuantity(pay.getProductQuantity())
                    .dateQuantity(pay.getDateQuantity())
                    .status(pay.getStatus())
                    .createdDate(pay.getCreatedDate())
                    .createdBy(pay.getCreatedBy())
                    .updatedBy(pay.getUpdatedBy())
                    .updatedDate(pay.getUpdatedDate())
                    .build();
            listRs.add(payRs);
        }
        return listRs;
    }

    @Override
    public ResponseDTO searchPaymentTxn(PaymentTxnSearch search) {
        try {
            log.debug("Begin search paymentTxn: {}", search);
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

            Page<PaymentTxn> page = paymentTxnRepository.search(
                    search.getCusId(),
                    search.getStatus(),
                    fromDate,
                    toDate,
                    pageable);

            List<PaymentTxn> paymentTxnList = page.toList();
            List<PaymentTxnRP> listRs = new ArrayList<>();
            for (PaymentTxn pay : paymentTxnList) {
                listRs.add(convertFromPaymentTxn(pay));
            }

            final long total = page.getTotalElements();
            log.debug("Search paymentTxn successful. {}", page);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(listRs)
                    .total(total)
                    .build();
        } catch (Exception ex) {
            log.error("Search paymentTxn ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
    }

    @Override
    public ActionRes createPayment(PaymentTxnRQ paymentTxnRQ) {
        try {
            log.debug("Begin create payment: {}", paymentTxnRQ);
            if (paymentTxnRQ.getCustomerId() == null || paymentTxnRQ.getCustomerId() == 0
                    || paymentTxnRQ.getTotalAmount() == null || paymentTxnRQ.getTotalAmount() == 0
                    || paymentTxnRQ.getPaymentTxnDetails() == null
                    || paymentTxnRQ.getPaymentTxnDetails().isEmpty()
            ) {
                log.debug("Data request invalid.");
                return ActionRes.builder()
                        .code(ResponseCode.INVALID_DATA.getCode())
                        .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                        .build();
            }

            Customer customer = customerRepository.findByID(paymentTxnRQ.getCustomerId());
            if (customer == null) {
                log.debug("Customer Not Existed. id: {} ", paymentTxnRQ.getCustomerId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT_CUSTOMER))
                        .build();
            }

            long totalAmount = calTotalAmount(paymentTxnRQ.getPaymentTxnDetails());
            if (totalAmount != paymentTxnRQ.getTotalAmount()) {
                log.debug("Invalid Amount. AmountReq: {}, AmountSys: {}", paymentTxnRQ.getTotalAmount(), totalAmount);
                return ActionRes.builder()
                        .code(ResponseCode.INVALID_AMOUNT.getCode())
                        .message(ResponseCode.INVALID_AMOUNT.getDesc(OBJECT))
                        .build();
            }

            PaymentTxn paymentTxn = PaymentTxn.builder()
                    .customerId(paymentTxnRQ.getCustomerId())
                    .totalAmount(totalAmount)
                    .productIds(getProductIds(paymentTxnRQ.getPaymentTxnDetails()))
                    .txnCount(calTotalProduct(paymentTxnRQ.getPaymentTxnDetails()))
                    .status(StatusCode.ACTIVE.getCode())
                    .createdBy(SecurityUtil.getCurrentUsernameId())
                    .createdDate(new Date())
                    .build();

            PaymentTxn paymentTxnRs = paymentTxnRepository.save(paymentTxn);
            if (paymentTxnRs != null) {
                log.debug("Begin insert paymentTxn detail");
                boolean pdRs = insertPaymentTxnDetail(paymentTxnRQ.getPaymentTxnDetails(), paymentTxnRs.getId());
                if (!pdRs) {
                    log.debug("Insert PaymentTxn detail ...fail. rollback transaction.");
                    //fixme rollback transaction
                    return ActionRes.builder()
                            .code(ResponseCode.CREATE_FAIL.getCode())
                            .message(ResponseCode.CREATE_FAIL.getDesc(OBJECT))
                            .build();
                }
            }

            log.debug("Create paymentTxn successful.");
            return ActionRes.builder()
                    .code(ResponseCode.CREATE_SUCCESS.getCode())
                    .message(ResponseCode.CREATE_SUCCESS.getDesc(OBJECT))
                    .build();
        } catch (Exception exception) {

            log.error("Create paymentTxn ...fail.", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

    @Override
    public ActionRes updatePayment(PaymentTxnRQ paymentTxnRQ) {
        return null;
    }

    @Override
    public ActionRes updateStatusPayment(PaymentTxnRQ paymentTxnRQ) {
        try {
            log.debug("Begin update status paymentTxn: {}", paymentTxnRQ);
            PaymentTxn paymentTxnOld = paymentTxnRepository.findByID(paymentTxnRQ.getId());
            if (paymentTxnOld == null) {
                log.debug("PaymentTxn not Exists. Id: {} ", paymentTxnRQ.getId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }

            paymentTxnOld.setStatus(paymentTxnRQ.getStatus());
            paymentTxnOld.setUpdatedDate(new Date());
            paymentTxnOld.setUpdatedBy(SecurityUtil.getCurrentUsernameId());

            paymentTxnRepository.save(paymentTxnOld);
            log.error("Update status paymentTxn successful.");
            return ActionRes.builder()
                    .code(ResponseCode.UPDATE_SUCCESS.getCode())
                    .message(ResponseCode.UPDATE_SUCCESS.getDesc(OBJECT))
                    .build();

        } catch (Exception exception) {
            log.error("Update status paymentTxn ...fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }


    private boolean insertPaymentTxnDetail(List<PaymentTxnDetailRQ> list, Long payID) {
        for (PaymentTxnDetailRQ pay : list) {
            PaymentTxnDetail payReq = PaymentTxnDetail.builder()
                    .payId(payID)
                    .productId(pay.getProductId())
                    .productPrice(pay.getProductPrice())
                    .productQuantity(pay.getProductQuantity())
                    .dateQuantity(pay.getDateQuantity())
                    .createdDate(new Date())
                    .createdBy(SecurityUtil.getCurrentUsernameId())
                    .status(StatusCode.ACTIVE.getCode())
                    .build();
            PaymentTxnDetail payRs = paymentTxnDetailRepository.save(payReq);
            if (payRs == null) {
                log.debug("Insert paymentTxn Detail ...fail. {}", payReq);
                return false;
            }
        }
        return true;
    }

    private String getProductIds(List<PaymentTxnDetailRQ> list) {
        String productIds = "";
        for (PaymentTxnDetailRQ pay : list) {
            productIds += pay.getProductId() + "|";
        }
        return productIds;
    }

    private long calTotalAmount(List<PaymentTxnDetailRQ> list) {
        long total = 0;
        for (PaymentTxnDetailRQ pay : list) {
            total += (pay.getProductPrice() * pay.getDateQuantity() * pay.getProductQuantity());
        }
        return total;
    }

    private int calTotalProduct(List<PaymentTxnDetailRQ> list) {
        int total = 0;
        for (PaymentTxnDetailRQ pay : list) {
            total += pay.getProductQuantity();
        }
        return total;
    }

    private PaymentTxnRP convertFromPaymentTxn(PaymentTxn paymentTxn) {

        Customer customer = customerRepository.getById(paymentTxn.getCustomerId());
        CustomerRP customerRP = null;
        if (customer != null) {
            customerRP = fromCustomer(customer);
        }
        return PaymentTxnRP.builder()
                .id(paymentTxn.getId())
                .customer(customerRP)
                .productIds(paymentTxn.getProductIds())
                .txnCount(paymentTxn.getTxnCount())
                .totalAmount(paymentTxn.getTotalAmount())
                .cancelDesc(paymentTxn.getCancelDesc())
                .status(paymentTxn.getStatus())
                .createdDate(paymentTxn.getCreatedDate())
                .createdBy(paymentTxn.getCreatedBy())
                .updatedDate(paymentTxn.getUpdatedDate())
                .updatedBy(paymentTxn.getUpdatedBy())
                .paymentTxnDetails(new ArrayList<>())
                .build();
    }

    private CustomerRP fromCustomer(Customer customer) {
        return CustomerRP.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .identifyCard(customer.getIdentifyCard())
                .dateBorn(customer.getDateBorn())
                .address(customer.getAddress())
                .mobile(customer.getMobile())
                .status(customer.getStatus())
                .createdBy(customer.getCreatedBy())
                .createdDate(customer.getCreatedDate())
                .updatedBy(customer.getUpdatedBy())
                .updatedDate(customer.getUpdatedDate())
                .build();
    }

}
