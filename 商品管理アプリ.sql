-- drop table users; 
create table users(
    id serial primary key
    , login_id varchar(255) not null unique
    , password varchar(255) not null
    , name varchar(255) not null
    , role int not null
    , created_at timestamp
    , updated_at timestamp
);

-- drop table categories;
create table categories(
    id serial primary key
    , name varchar(255) not null
    , created_at timestamp
    , updated_at timestamp
);

-- drop table products; 
create table products(
    id serial primary key
    , product_id varchar(20) unique not null 
    , category_id int not null
    , name varchar(255) not null
    , price int 
    , image_path varchar(255)
    , description varchar(2000)
    , created_at timestamp
    , updated_at timestamp
);

-- users
insert into users(login_id, password, name, role) values('admin', 'admin','システム管理者', 1);
insert into users(login_id, password, name, role) values('sato', 'pass01','佐藤', 2);
insert into users(login_id, password, name, role) values('suzuki', 'pass02','鈴木', 2);

-- categories
insert into categories(name) values('筆記具');
insert into categories(name) values('オフィス機器');
insert into categories(name) values('事務消耗品');
insert into categories(name) values('紙製品');
insert into categories(name) values('雑貨');

-- products
insert into products (product_id, category_id, name, price, description)
values('10001', 1, 'マッキー(黒)', 160, 'ゼブラ株式会社 線の太さ：太6.0mm、細1.5～2.0mm');
insert into products (product_id, category_id, name, price, description)
values('10002', 2, '電卓', 935, '');
insert into products (product_id, category_id, name, price, description)
values('10003', 3, 'ホッチキス芯', 220, '');
insert into products (product_id, category_id, name, price, description)
values('10004', 4, 'Campus(5冊組)', 220, '');
insert into products (product_id, category_id, name, price, description)
values('10005', 5, '地球儀', 3800, '');
insert into products (product_id, category_id, name, price, description)
values('10006', 4, 'ロジカルノート(5冊組)', 386, '');
insert into products (product_id, category_id, name, price, description)
values('10007', 2, 'レジスター', 24000, '');
insert into products (product_id, category_id, name, price, description)
values('10008', 1, 'カドケシ', 130, '');
insert into products (product_id, category_id, name, price, description)
values('10009', 3, 'アラビックヤマト', 200, '');
insert into products (product_id, category_id, name, price, description)
values('10010', 5, '粘土', 160, '');
