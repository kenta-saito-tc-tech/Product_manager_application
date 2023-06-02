package com.example.ProductsManagerAP_KS.Controller;

import com.example.ProductsManagerAP_KS.Entity.*;
import com.example.ProductsManagerAP_KS.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    private ProductService productService;

    @Autowired
    private HttpSession session;

    /**
     * ログイン時のID、PASSチェック
     *
     * @param idPassRecord
     * @return
     */
    @PostMapping("/log-check")
    public ResponseEntity<String> insertView(@RequestBody IdPassRecord idPassRecord) {
        try {
            UserRecord user = productService.findUser(idPassRecord);

            if (user != null) {
                session.setAttribute("user", user);

                return new ResponseEntity<>("POST request processed", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("POST request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * categoriesテーブル情報全取得
     *
     * @param
     * @return
     */
    @GetMapping("/categories")
    public List<CategoryRecord> selectCategories() {
        try {
            var list = productService.findCategoryAll();
            return list; //ステータスコード200番
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * categoryテーブルの更新
     *
     * @param
     * @return
     */
    @PutMapping("/categoryUpdate")
    public ResponseEntity<String> categoryUpdate(@RequestBody CategoryRecord categoryRecord) {
        try {
            int count = productService.categoryUpdate(categoryRecord);
            if (count == 1) {
                return new ResponseEntity<>("PUT request processed", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("PUT request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * categoryテーブルの削除
     *
     * @param categoryRecord
     * @return
     */
    @DeleteMapping("/categoryDelete")
    public ResponseEntity<String> categoryDelete(@RequestBody CategoryRecord categoryRecord) {
        try {
            int count = productService.categoryDelete(categoryRecord);
            if (count == 1) {
                return new ResponseEntity<>("DELETE request processed", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("DELETE request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * productsテーブルの追加
     * @param productRecord
     * @return
     */
    @PostMapping("/insertProduct")
    public ResponseEntity<String> insertProduct(@RequestBody ProductRecord productRecord) {
        try {
         ProductRecord pr = productService.findById(productRecord.productId());
         if (pr == null){
             try {
                 int count = productService.insertProduct(productRecord);
                 if (count == 1) {
                     return new ResponseEntity<>("POST request processed", HttpStatus.OK);
                 } else {
                     return new ResponseEntity<>("POST request failed", HttpStatus.BAD_REQUEST);
                 }
             } catch (Exception e) {
                 e.printStackTrace();
                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
             }
         }else {
             return new ResponseEntity<>("product_id exists", HttpStatus.BAD_REQUEST);
         }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("POST request failed", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * ,menuのlistの表示用データ取得
     * @return
     */
    @GetMapping("/products")
    public List<ListRecord> productList(){
        try {
            var list = productService.findAll();
            return list; //ステータスコード200番
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * productの詳細ページ用データ取得用
     * @param productId
     * @return
     */
    @GetMapping("/product")
    public ProductRecord productIdFind(@RequestParam(name = "searchId") String productId){
        try {
            var pr = productService.findById(productId);
            return pr; //ステータスコード200番
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * productの並び順変更
     * @param
     * @return
     */
    @GetMapping("/productSort")
    public List<ListRecord> productSort(@RequestParam(name = "changeMenu") String sortText){
        try {
            var pr = productService.productSort(sortText);
            return pr; //ステータスコード200番
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * productの詳細ページ用データ取得用
     * @param id
     * @return
     */
    @GetMapping("/productDetail")
    public ProductRecord productInfo(@RequestParam(name = "productId") int id){
        try {
            var pr = productService.findProductByID(id);
            return pr; //ステータスコード200番
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * productの更新
     * @param productRecord
     * @return
     */
    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody ProductRecord productRecord){
        try {
            ProductRecord pr = productService.findById(productRecord.productId());
            if (pr == null || productRecord.id() == pr.id()){
                try {
                    int count = productService.update(productRecord);
                    if (count == 1) {
                        return new ResponseEntity<>("PUT request processed", HttpStatus.OK);
                    }else {
                        return new ResponseEntity<>("PUT request failed", HttpStatus.BAD_REQUEST);
                    }
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
                }
            }else {
                return new ResponseEntity<>("product_id exists", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * productテーブルの削除
     *
     * @param
     * @return
     */
    @DeleteMapping("/productDelete")
    public ResponseEntity<String> deleteProduct(@RequestBody ProductRecord productRecord) {
        try {
            int count = productService.delete(productRecord.id());
            if (count == 1) {
                return new ResponseEntity<>("DELETE request processed", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("DELETE request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }



}



