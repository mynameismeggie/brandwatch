package com.brandwatch.store.ingress.mapper;

import com.brandwatch.store.domain.entity.Product;
import com.brandwatch.store.ingress.request.ProductRequest;
import com.brandwatch.store.ingress.response.ProductOrderResponse;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<Product> toEntityFromRequest(List<ProductRequest> productRequests);

    Product toEntity(ProductRequest productRequest);

    List<Product> toEntityFromResponse(List<ProductOrderResponse> productOrderResponses);

    Product toEntity(ProductOrderResponse productOrderResponse);
}
