package vn.free.register.service;

import vn.free.register.request.paymnet_txn.PaymentTxnRQ;
import vn.free.register.request.paymnet_txn.PaymentTxnSearch;
import vn.free.register.request.product.ProductRQ;
import vn.free.register.request.product.ProductSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;

public interface PaymentTxnService {

    ResponseDTO searchPaymentTxn(PaymentTxnSearch search);

    ResponseDTO getPaymentTxnById(PaymentTxnRQ search);

    ActionRes createPayment(PaymentTxnRQ paymentTxnRQ);

    ActionRes updatePayment(PaymentTxnRQ paymentTxnRQ);

    ActionRes updateStatusPayment(PaymentTxnRQ paymentTxnRQ);
}
