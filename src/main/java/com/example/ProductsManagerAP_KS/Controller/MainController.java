package com.example.ProductsManagerAP_KS.Controller;

import com.example.ProductsManagerAP_KS.Entity.ProductRecord;
import com.example.ProductsManagerAP_KS.Entity.UserRecord;
import com.example.ProductsManagerAP_KS.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalTime;

@Controller
public class MainController {
    @Autowired
    ProductService productService;
    @Autowired
    private HttpSession session;

    /**
     * 初期画面
     * @return ログイン画面
     */
    @GetMapping("/index")
    public String firstLoginView(){
        return "/index";
    }

    /**
     * リスト画面
     * @return
     */
    @GetMapping("/change-page")
    public String changePage() {
        return "/products-list";
    }

    /**
     * リスト画面に戻る
     * @return
     */
    @GetMapping("/backToList")
    public String backToListView(){return "/products-list";}

    /**
     * ログアウト
     * @return ログイン画面
     */
    @GetMapping("/logout")
    public String logout() {
        if (session != null) {
            session.invalidate(); // セッションを無効化して削除する
        }
        return "/index";
    }

    /**
     *新規津登録画面
     * @return
     */
    @GetMapping("/insert")
    public String insertView(){
        return "/new-product";
    }

    /**
     *カテゴリー管理画面
     * @return
     */
    @GetMapping("/category")
    public String categoryView(){
        return "/category-list";
    }

    /**
     * 商品情報画面
     * @return
     */
    @GetMapping("/product-detail/{id}")
    public String detailView(@PathVariable("id") int productId, Model model){

        model.addAttribute("productId",productId);

        return "/product-detail";
    }

}
