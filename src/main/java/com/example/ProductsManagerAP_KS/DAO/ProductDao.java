package com.example.ProductsManagerAP_KS.DAO;

import com.example.ProductsManagerAP_KS.Entity.*;

import java.util.List;

public interface ProductDao {

    public UserRecord findUser(IdPassRecord idPassRecord);
    public List<CategoryRecord> findCategoryAll();
    public int categoryUpdate(CategoryRecord categoryRecord);
    public int categoryDelete(CategoryRecord categoryRecord);
    public int insertProduct(ProductRecord productRecord);
    public CategoryRecord findCategoryByName(String categoryName);
    public List<ListRecord> findAll();
    public ProductRecord findById(String id);
    public ProductRecord findProductByID(int id);

    public int update(ProductRecord productRecord);
    public int delete(int id);

    public List<ListRecord> productSort(String sortText, String keyword);
}
