package vn.free.register.service;

import vn.free.register.request.request_txn.RequestTxnRQ;
import vn.free.register.request.request_txn.RequestTxnSearch;
import vn.free.register.request.user.UserSearch;
import vn.free.register.request.user.UserRQ;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;

public interface RequestTxnService {

    ResponseDTO searchRequestTxn(RequestTxnSearch search);

    ResponseDTO getRequestTxnById(RequestTxnRQ userRQ);

    ActionRes createRequestTxn(RequestTxnRQ userRQ);

    ActionRes updateStatusRequestTxn(RequestTxnRQ userRQ);
}
