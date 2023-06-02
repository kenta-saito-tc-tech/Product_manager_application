package com.example.ProductsManagerAP_KS.Service;

import com.example.ProductsManagerAP_KS.DAO.ProductDao;
import com.example.ProductsManagerAP_KS.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PgProductService implements ProductService{
    @Autowired
    private ProductDao productDao;

    @Override
    public UserRecord findUser(IdPassRecord idPassRecord){
        return productDao.findUser(idPassRecord);
    }

    @Override
    public List<CategoryRecord> findCategoryAll() {
        return productDao.findCategoryAll();
    }

    @Override
    public int categoryUpdate(CategoryRecord categoryRecord) {
        return productDao.categoryUpdate(categoryRecord);
    }

    @Override
    public int categoryDelete(CategoryRecord categoryRecord) {
        return productDao.categoryDelete(categoryRecord);
    }

    @Override
    public int insertProduct(ProductRecord productRecord) {
        return productDao.insertProduct(productRecord);
    }


    @Override
    public List<ListRecord> findAll() {
        return productDao.findAll();
    }

    @Override
    public ProductRecord findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public ProductRecord findProductByID(int id) {
        return productDao.findProductByID(id);
    }

    @Override
    public int update(ProductRecord productRecord) {
        return productDao.update(productRecord);
    }

    @Override
    public int delete(int id) {
        return productDao.delete(id);
    }

    @Override
    public List<ListRecord> productSort(String sortText, String keyword) {
        return productDao.productSort(sortText, keyword);
    }


}
