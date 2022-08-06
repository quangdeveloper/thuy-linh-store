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
import vn.free.register.entity.Product;
import vn.free.register.entity.ProductImg;
import vn.free.register.entity.User;
import vn.free.register.repository.ProductImgRepository;
import vn.free.register.repository.ProductRepository;
import vn.free.register.repository.UserRepository;
import vn.free.register.request.product.ProductImgRQ;
import vn.free.register.request.product.ProductRQ;
import vn.free.register.request.product.ProductSearch;
import vn.free.register.request.user.UserRQ;
import vn.free.register.request.user.UserSearch;
import vn.free.register.response.ActionRes;
import vn.free.register.response.ResponseDTO;
import vn.free.register.response.product.ProductRP;
import vn.free.register.service.ProductService;
import vn.free.register.service.UserService;
import vn.free.register.util.DateUtil;
import vn.free.register.util.SecurityUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private static String OBJECT = "sản phẩm";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImgRepository productImgRepository;


    @Override
    public ResponseDTO searchProduct(ProductSearch search) {
        try {
            log.debug("Begin search product: {}", search);
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

            Page<Product> page = productRepository.search(
                    search.getKeyword(),
                    search.getStatus(),
                    fromDate,
                    toDate,
                    pageable);

            final long total = page.getTotalElements();
            log.debug("Search product successful. {}", page);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(page.toList())
                    .total(total)
                    .build();
        } catch (Exception ex) {
            log.error("Search product ...fail. ", ex);
            return ResponseDTO.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .data(Collections.EMPTY_LIST)
                    .total(0L)
                    .build();
        }
    }

    @Override
    public ResponseDTO getProductById(ProductRQ productRQ) {
        try {
            log.debug("Begin get product by id: {}", productRQ);
            Product product = productRepository.findByID(productRQ.getId());
            if (product == null) {
                log.debug("Product not Existed. id: {} ", productRQ.getId());
                return ResponseDTO.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }
            ProductRP productRP = fromProduct(product);
            List<ProductImg> images = productImgRepository.findByProductID(product.getId());
            if (images == null) images = new ArrayList<>();
            productRP.setProductImages(images);
            log.debug("Get product by id successful. {}", productRP);
            return ResponseDTO.builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getDesc(OBJECT))
                    .data(productRP)
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

    @Override
    public ActionRes createProduct(ProductRQ productRQ) {
        try {
            log.debug("Begin create product: {}", productRQ);
            if (StringUtils.isEmpty(productRQ.getCode())
                    || productRQ.getCode().length() != 10
                    || StringUtils.isEmpty(productRQ.getName())
                    || StringUtils.isEmpty(productRQ.getLogo())
                    || productRQ.getQuantity() == null || productRQ.getQuantity() == 0
                    || productRQ.getPriceImport() == null || productRQ.getPriceImport() == 0
                    || productRQ.getPriceExport() == null || productRQ.getPriceExport() == 0
            ) {
                log.debug("Data request invalid.");
                return ActionRes.builder()
                        .code(ResponseCode.INVALID_DATA.getCode())
                        .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                        .build();
            }
            Product productOld = productRepository.findByCode(productRQ.getCode());
            if (productOld != null) {
                log.debug("Product Code Existed. code: {} ", productRQ.getCode());
                return ActionRes.builder()
                        .code(ResponseCode.EXISTS.getCode())
                        .message(ResponseCode.EXISTS.getDesc(OBJECT))
                        .build();
            }

            Product product = Product.builder()
                    .code(productRQ.getCode())
                    .name(productRQ.getName())
                    .desc(productRQ.getDesc())
                    .logo(productRQ.getLogo())
                    .quantity(productRQ.getQuantity())
                    .priceImport(productRQ.getPriceImport())
                    .priceExport(productRQ.getPriceExport())
                    .quantity(productRQ.getQuantity())
                    .status(StatusCode.ACTIVE.getCode())
                    .createdBy(SecurityUtil.getCurrentUsernameId())
                    .createdDate(new Date())
                    .build();

            Product productRs = productRepository.save(product);
            if (productRs != null && product.getId() != null) {
                insertProductImg(productRQ.getProductImages(), product.getId());
            }

            log.debug("Create product successful.");
            return ActionRes.builder()
                    .code(ResponseCode.CREATE_SUCCESS.getCode())
                    .message(ResponseCode.CREATE_SUCCESS.getDesc(OBJECT))
                    .build();
        } catch (Exception exception) {

            log.error("Create product ...fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

    @Override
    public ActionRes updateProduct(ProductRQ productRQ) {
        try {
            log.debug("Begin update product: {}", productRQ);
            if (StringUtils.isEmpty(productRQ.getCode())
                    || productRQ.getCode().length() != 10
                    || StringUtils.isEmpty(productRQ.getName())
                    || StringUtils.isEmpty(productRQ.getLogo())
                    || productRQ.getQuantity() == null || productRQ.getQuantity() == 0
                    || productRQ.getPriceImport() == null || productRQ.getPriceImport() == 0
                    || productRQ.getPriceExport() == null || productRQ.getPriceExport() == 0
                    || productRQ.getId() == null || productRQ.getId() == 0
            ) {
                log.debug("Data request invalid.");
                return ActionRes.builder()
                        .code(ResponseCode.INVALID_DATA.getCode())
                        .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                        .build();
            }
            Product productOld = productRepository.findByID(productRQ.getId());
            if (productOld == null) {
                log.debug("Product not Existed. id: {} ", productRQ.getId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }

            //check ma san pham cap nhat moi da co trong he thong ?
            if (!productOld.getCode().equals(productRQ.getCode())) {
                Product productByCode = productRepository.findByCode(productRQ.getCode());
                if (productByCode != null) {
                    log.debug("Product Code Existed. code: {} ", productRQ.getCode());
                    return ActionRes.builder()
                            .code(ResponseCode.INVALID_DATA.getCode())
                            .message(ResponseCode.INVALID_DATA.getDesc(OBJECT))
                            .build();
                }
            }

            Product product = Product.builder()
                    .id(productRQ.getId())
                    .code(productRQ.getCode())
                    .name(productRQ.getName())
                    .desc(productRQ.getDesc())
                    .logo(productRQ.getLogo())
                    .quantity(productRQ.getQuantity())
                    .priceImport(productRQ.getPriceImport())
                    .priceExport(productRQ.getPriceExport())
                    .quantity(productRQ.getQuantity())
                    .status(StatusCode.ACTIVE.getCode())
                    .createdBy(productOld.getCreatedBy())
                    .createdDate(productOld.getCreatedDate())
                    .updatedBy(SecurityUtil.getCurrentUsernameId())
                    .updatedDate(new Date())
                    .build();

            Product productRs = productRepository.save(product);
            if (productRs != null && product.getId() != null) {
                updateProductImg(productRQ.getProductImages(), product.getId());
            }

            log.debug("Update product successful.");
            return ActionRes.builder()
                    .code(ResponseCode.UPDATE_SUCCESS.getCode())
                    .message(ResponseCode.UPDATE_SUCCESS.getDesc(OBJECT))
                    .build();
        } catch (Exception exception) {

            log.error("Update product ...fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

    @Override
    public ActionRes updateStatusProduct(ProductRQ productRQ) {
        try {
            log.debug("Begin update status product: {}", productRQ);
            Product product = productRepository.findByID(productRQ.getId());
            if (product == null) {
                log.debug("Product not Exists. Id: {} ", productRQ.getId());
                return ActionRes.builder()
                        .code(ResponseCode.NOT_EXISTS.getCode())
                        .message(ResponseCode.NOT_EXISTS.getDesc(OBJECT))
                        .build();
            }

            product.setStatus(productRQ.getStatus());
            product.setUpdatedBy(SecurityUtil.getCurrentUsernameId());
            product.setUpdatedDate(new Date());
            productRepository.save(product);

            log.debug("Update status product successful.");
            return ActionRes.builder()
                    .code(ResponseCode.UPDATE_SUCCESS.getCode())
                    .message(ResponseCode.UPDATE_SUCCESS.getDesc(OBJECT))
                    .build();

        } catch (Exception exception) {
            log.error("Update status product ...fail. ", exception);
            return ActionRes.builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getDesc(OBJECT))
                    .build();
        }
    }

    private boolean insertProductImg(List<ProductImgRQ> images, Long productId) {
        try {
            if (!images.isEmpty()) {
                log.debug("Begin insert product images");
                for (ProductImgRQ productImgRQ : images) {
                    ProductImg productImg = ProductImg.builder()
                            .productId(productId)
                            .image(productImgRQ.getImage())
                            .status(StatusCode.ACTIVE.getCode())
                            .build();
                    productImgRepository.save(productImg);
                }
            }
            return true;
        } catch (Exception ex) {
            log.debug("Insert product image ...fail. ", ex);
            return false;
        }
    }

    private boolean updateProductImg(List<ProductImgRQ> images, Long productId) {
        try {
            if (!images.isEmpty()) {
                log.debug("Begin update product images");
                for (ProductImgRQ productImgRQ : images) {
                    ProductImg productImg = ProductImg.builder()
                            .id(productImgRQ.getId())
                            .productId(productImgRQ.getProductId())
                            .image(productImgRQ.getImage())
                            .status(productImgRQ.getStatus())
                            .build();
                    if (productImgRQ.getProductId() == null) {
                        productImg.setProductId(productId);
                        productImg.setStatus(StatusCode.ACTIVE.getCode());
                    }
                    productImgRepository.save(productImg);
                }
            }
            return true;
        } catch (Exception ex) {
            log.debug("Update product image ...fail. ", ex);
            return false;
        }
    }

    private ProductRP fromProduct(Product product) {
        return ProductRP.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .logo(product.getLogo())
                .desc(product.getDesc())
                .quantity(product.getQuantity())
                .status(product.getStatus())
                .priceImport(product.getPriceImport())
                .priceExport(product.getPriceExport())
                .createdBy(product.getCreatedBy())
                .createdDate(product.getCreatedDate())
                .updatedBy(product.getUpdatedBy())
                .updatedDate(product.getUpdatedDate())
                .build();
    }
}
