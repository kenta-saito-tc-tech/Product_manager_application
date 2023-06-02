package com.example.ProductsManagerAP_KS.DAO;

import com.example.ProductsManagerAP_KS.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PgProductDao implements ProductDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * IDとPASSからUserを探すメソッド
     * @param idPassRecord
     * @return
     */
    @Override
    public UserRecord findUser(IdPassRecord idPassRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", idPassRecord.id());
        param.addValue("pass", idPassRecord.pass());
        var list = jdbcTemplate.query("SELECT * FROM users WHERE login_id = :id AND password = :pass",
                param, new DataClassRowMapper<>(UserRecord.class));
//        System.out.println(list.get(0));
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * categoryテーブル情報全取得
     * @return
     */
    @Override
    public List<CategoryRecord> findCategoryAll() {
        return jdbcTemplate.query("SELECT * FROM categories ORDER BY id",
                new DataClassRowMapper<>(CategoryRecord.class));
    }

    /**
     * categoryテーブルを更新
     * @param categoryRecord
     * @return
     */
    @Override
    public int categoryUpdate(CategoryRecord categoryRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", categoryRecord.id());
        param.addValue("name", categoryRecord.name());
        int count = jdbcTemplate.update("UPDATE categories" +
                " SET name = :name, updated_at = now()" +
                " WHERE id = :id",param);
        return count;
    }

    /**
     * categoryテーブルを削除
     * @param categoryRecord
     * @return
     */
    @Override
    public int categoryDelete(CategoryRecord categoryRecord){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", categoryRecord.id());
        int count = jdbcTemplate.update("DELETE FROM categories WHERE id = :id",param);
        return count;
    }


    /**
     * categories情報を名前検索
     * @param categoryName
     * @return
     */
    @Override
    public CategoryRecord findCategoryByName(String categoryName) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", categoryName);
        var list = jdbcTemplate.query("SELECT * FROM categories WHERE name = :name",
                param, new DataClassRowMapper<>(CategoryRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * productの新規追加
     * @param productRecord
     * @return
     */
    @Override
    public int insertProduct(ProductRecord productRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("productId", productRecord.productId());
        param.addValue("categoryId", productRecord.categoryId());
        param.addValue("name", productRecord.name());
        param.addValue("price", productRecord.price());
        param.addValue("imagePath", productRecord.imagePath());
        param.addValue("description", productRecord.description());
        int count = jdbcTemplate.update("INSERT INTO products" +
                "(product_id, category_id, name, price, image_path, description, created_at, updated_at)" +
                " VALUES (:productId, :categoryId, :name, :price::integer, :imagePath, :description, now(), now())",param);
        return count == 1 ? count : null;
    }

    /**
     * ,menuのlistの表示用データ取得
     * @return
     */
    @Override
    public List<ListRecord> findAll() {
        return jdbcTemplate.query("SELECT p.id, p.product_id, p.name, p.price, c.name category_name FROM products p INNER JOIN categories c ON p.category_id = c.id ORDER BY p.id",
                new DataClassRowMapper<>(ListRecord.class));
    }

    /**
     * productIdから情報取得用
     * @param id
     * @return
     */
    @Override
    public ProductRecord findById(String id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        var list = jdbcTemplate.query("SELECT * FROM products WHERE product_id = :id",
                param, new DataClassRowMapper<>(ProductRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * idから商品情報1件取得用
     * @param id
     * @return
     */
    @Override
    public ProductRecord findProductByID(int id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        var list = jdbcTemplate.query("SELECT * FROM products WHERE id = :id",
                param, new DataClassRowMapper<>(ProductRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }


    /**
     * productsテーブルの更新
     * @param productRecord
     * @return
     */
    @Override
    public int update(ProductRecord productRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", productRecord.id());
        param.addValue("productId", productRecord.productId());
        param.addValue("categoryId", productRecord.categoryId());
        param.addValue("name", productRecord.name());
        param.addValue("price", productRecord.price());
        param.addValue("imagePath", productRecord.imagePath());
        param.addValue("description", productRecord.description());
        int count = jdbcTemplate.update("UPDATE products" +
                " SET product_id = :productId, category_id = :categoryId, name = :name, price = :price, description = :description, updated_at = now() " +
                " WHERE id = :id",param);
        return count;
    }

    /**
     * productテーブルの削除
     * @param id
     * @return
     */
    @Override
    public int delete(int id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        int count = jdbcTemplate.update("DELETE FROM products WHERE id = :id",param);
        return count;
    }
}
