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
import vn.free.register.entity.Customer;
import vn.free.register.entity.GroupRole;
import vn.free.register.entity.User;
import vn.free.register.repository.CustomerRepository;
import vn.free.register.repository.UserRepository;
import vn.free.register.request.customer.CustomerRQ;
import vn.free.register.request.customer.CustomerSearch;
import vn.free.register.request.user.UserRQ;
import vn.free.register.request.user.UserSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.service.CustomerService;
import vn.free.register.service.UserService;
import vn.free.register.util.SecurityUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private static String OBJECT = "khách hàng";

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseDTO getAllCustomer() {
        try {
            log.debug("Begin get all group role");

            List<Customer> list = customerRepository.findAllActive();

            final long total = list.size();
            log.debug("Get all customer successful. {}", list);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(list)
                    .total(total)
                    .build();
        } catch (Exception ex) {
            log.error("Get all customer ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
    }

    @Override
    public ResponseDTO getCustomerByID(CustomerRQ customerRQ) {
        try {
            log.debug("Begin get customer by id: {}", customerRQ);

            if (customerRQ.getId() == null || customerRQ.getId() == 0) {
                log.debug("Data request invalid.");
                return ResponseDTO.builder()
                        .code(ResponseCode.INVALID_DATA.getCode())
                        .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                        .build();
            }
            Customer customer = customerRepository.findByID(customerRQ.getId());

            if (customer == null) {
                log.debug("Customer not exists. ID: {}", customerRQ.getId());
                return ResponseDTO.builder()
                        .code(ResponseCode.INVALID_DATA.getCode())
                        .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                        .build();
            }

            log.debug("Get customer by id successful. {}", customer);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(customer)
                    .build();
        } catch (Exception ex) {
            log.error("Get customer by id ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
    }

    @Override
    public ResponseDTO searchCustomer(CustomerSearch search) {
        try {
            log.debug("Begin search customer: {}", search);
            Pageable pageable = PageRequest.of(
                    search.getPageIndex() - 1,
                    search.getPageSize(),
                    Sort.by("id").descending());

            Page<Customer> page = customerRepository.search(
                    search.getKeyword(),
                    search.getStatus(),
                    pageable);

            final long total = page.getTotalElements();
            log.debug("Search customer successful. {}", page);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(page.toList())
                    .total(total)
                    .build();
        } catch (Exception ex) {
            log.error("Search customer ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
    }

    @Override
    public ActionRes createCustomer(CustomerRQ customerRQ) {
        try {
            log.debug("Begin create user: {}", customerRQ);
            if (StringUtils.isEmpty(customerRQ.getFullName())
                    || StringUtils.isEmpty(customerRQ.getIdentifyCard())
                    || StringUtils.isEmpty(customerRQ.getDateBorn())
                    || StringUtils.isEmpty(customerRQ.getAddress())
                    || StringUtils.isEmpty(customerRQ.getMobile())
            ) {
                log.debug("Data request invalid.");
                return ActionRes.builder()
                        .code(ResponseCode.INVALID_DATA.getCode())
                        .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                        .build();
            }
            Customer userOld = customerRepository.findByIdentifyCard(customerRQ.getIdentifyCard());
            if (userOld != null) {
                log.debug("IdentifyCard Existed. identifyCard: {} ", customerRQ.getIdentifyCard());
                return ActionRes.builder()
                        .code(ResponseCode.EXISTS.getCode())
                        .message(ResponseCode.EXISTS.getDesc(OBJECT))
                        .build();
            }

            Customer customer = Customer.builder()
                    .fullName(customerRQ.getFullName())
                    .identifyCard(customerRQ.getIdentifyCard())
                    .address(customerRQ.getAddress())
                    .mobile(customerRQ.getMobile())
                    .dateBorn(customerRQ.getDateBorn())
                    .status(StatusCode.ACTIVE.getCode())
                    .createdBy(SecurityUtil.getCurrentUsernameId())
                    .createdDate(new Date())
                    .build();

            customerRepository.save(customer);
            log.debug("Create customer successful.");
            return ActionRes.builder()
                    .code(ResponseCode.CREATE_SUCCESS.getCode())
                    .message(ResponseCode.CREATE_SUCCESS.getDesc(OBJECT))
                    .build();
        } catch (Exception exception) {

            log.error("Create customer ...fail.", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

    @Override
    public ActionRes updateCustomer(CustomerRQ customerRQ) {
        try {
            log.debug("Begin update customer: {}", customerRQ);
            Customer userOld = customerRepository.findByID(customerRQ.getId());
            if (userOld == null) {
                log.debug("UserName not exists. userId: {} ", customerRQ.getId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }

            Customer customer = Customer.builder()
                    .id(customerRQ.getId())
                    .fullName(customerRQ.getFullName())
                    .identifyCard(customerRQ.getIdentifyCard())
                    .dateBorn(customerRQ.getDateBorn())
                    .address(customerRQ.getAddress())
                    .mobile(customerRQ.getMobile())
                    .status(userOld.getStatus())
                    .createdBy(userOld.getCreatedBy())
                    .createdDate(userOld.getCreatedDate())
                    .updatedBy(SecurityUtil.getCurrentUsernameId())
                    .updatedDate(new Date())
                    .build();
            log.debug("Customer update: {} ", customer);
            customerRepository.save(customer);
            log.debug("Update customer successful");
            return ActionRes.builder()
                    .code(ResponseCode.UPDATE_SUCCESS.getCode())
                    .message(ResponseCode.UPDATE_SUCCESS.getDesc(OBJECT))
                    .build();

        } catch (Exception exception) {

            log.error("Update customer ...fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

    @Override
    public ActionRes updateStatusCustomer(CustomerRQ customerRQ) {
        try {
            log.debug("Begin update status customer: {}", customerRQ);
            Customer customer = customerRepository.findByID(customerRQ.getId());
            if (customer == null) {
                log.debug("Customer not Exists. customerId: {} ", customerRQ.getId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }
            customer.setStatus(customerRQ.getStatus());
            customer.setUpdatedBy(SecurityUtil.getCurrentUsernameId());
            customer.setUpdatedDate(new Date());
            customerRepository.save(customer);

            log.error("Update status customer successful.");
            return ActionRes.builder()
                    .code(ResponseCode.UPDATE_SUCCESS.getCode())
                    .message(ResponseCode.UPDATE_SUCCESS.getDesc(OBJECT))
                    .build();

        } catch (Exception exception) {
            log.error("Update status user ...fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

}
