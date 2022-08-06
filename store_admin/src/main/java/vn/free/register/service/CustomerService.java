package vn.free.register.service;

import vn.free.register.request.customer.CustomerRQ;
import vn.free.register.request.customer.CustomerSearch;
import vn.free.register.request.user.UserRQ;
import vn.free.register.request.user.UserSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;

public interface CustomerService {

    ResponseDTO getAllCustomer();

    ResponseDTO searchCustomer(CustomerSearch search);

    ResponseDTO getCustomerByID(CustomerRQ customerRQ);

    ActionRes createCustomer(CustomerRQ customerRQ);

    ActionRes updateCustomer(CustomerRQ customerRQ);

    ActionRes updateStatusCustomer(CustomerRQ customerRQ);
}
