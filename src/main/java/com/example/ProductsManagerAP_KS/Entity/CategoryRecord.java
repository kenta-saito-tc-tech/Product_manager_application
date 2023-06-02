package com.example.ProductsManagerAP_KS.Entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 論理カラム名	物理カラム名	型	制約	備考
 * id	id	serial	primary key
 * 名前	name	varchar(255)	not null
 * 登録日時	created_at	datetime
 * 更新日時	created_at	datetime
 */
public record CategoryRecord(
        int id, //id
        String name, //名前
        Timestamp createdAt, //登録日時
        Timestamp updatedAt //更新日時
) {
}
