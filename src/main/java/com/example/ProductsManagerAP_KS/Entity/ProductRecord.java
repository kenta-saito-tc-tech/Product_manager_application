package com.example.ProductsManagerAP_KS.Entity;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

public record ProductRecord(
        int id, //ID
        String productId, //商品ID
        int categoryId, //カテゴリーID
        String name, //商品名
        int price, //値段
        String imagePath, //商品画像
        String description, //商品説明
        Timestamp createdAt, //登録日時
        Timestamp updatedAt //更新日時
) {
}
