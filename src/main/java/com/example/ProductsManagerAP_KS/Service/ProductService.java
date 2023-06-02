package com.example.ProductsManagerAP_KS.Service;

import com.example.ProductsManagerAP_KS.Entity.*;

import java.util.List;

public interface ProductService {
    public UserRecord findUser(IdPassRecord idPassRecord);
    public List<CategoryRecord> findCategoryAll();
    public int categoryUpdate(CategoryRecord categoryRecord);
    public int categoryDelete(CategoryRecord categoryRecord);
    public int insertProduct(ProductRecord productRecord);
    public List<ListRecord> findAll();
    public ProductRecord findById(String id);
    public ProductRecord findProductByID(int id);
    public int update(ProductRecord productRecord);
    public int delete(int id);
}
