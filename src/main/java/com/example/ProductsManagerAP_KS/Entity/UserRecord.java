package com.example.ProductsManagerAP_KS.Entity;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

public record UserRecord(
        int id, //id
        String loginId, //ログインID
        String password, //パスワード
        String name, //名前
        int role, //権限 1:管理者、2:一般ユーザー
        Timestamp createdAt, //登録日時
        Timestamp updatedAt //更新日時
) {
}
