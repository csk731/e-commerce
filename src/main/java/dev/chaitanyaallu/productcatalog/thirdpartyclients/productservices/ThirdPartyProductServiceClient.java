package dev.chaitanyaallu.productcatalog.thirdpartyclients.productservices;

import dev.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore.FakeStoreProductDto;
import dev.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import dev.chaitanyaallu.productcatalog.exceptions.NotFoundException;

import java.util.List;

/**
 * This interface is useful and be used when all the 3rd party apis has similar methods.
 * If not similar, this interface has no use, we can delete it.
 */

public interface ThirdPartyProductServiceClient {
    FakeStoreProductDto getProductById(Long id) throws NotFoundException;
    FakeStoreProductDto createProduct(GenericProductDto product);
    List<FakeStoreProductDto> getAllProducts();
    FakeStoreProductDto deleteProductById(Long id);
    FakeStoreProductDto updateProductById(Long id, GenericProductDto product);
}
