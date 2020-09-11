DROP DATABASE IF EXISTS bakici;
CREATE DATABASE IF NOT EXISTS bakici;
USE bakici;

create table referans(
    id int unsigned primary key auto_increment,
    adi varchar(50),
    aciklama varchar(500)
    );
create table dosya(
    id int primary key auto_increment,
    adi varchar(100),
    dosya_yolu varchar(200)
    );
create table il(
    id int primary key auto_increment,
    adi varchar(20)
    );
create table grup(
    id int unsigned primary key auto_increment,
    adi varchar(20),
    admin tinyint,
    veren tinyint,
    alan tinyint
    );

create table cv(
    id int primary key auto_increment,
    adi varchar(20),
    dosya_id int,
    aciklama varchar(500),
    FOREIGN KEY (dosya_id) REFERENCES dosya(id) ON DELETE SET NULL
    );

create table yorum(
    id int primary key auto_increment,
    aciklama varchar(200)
    );

create table adres(
    id int unsigned primary key auto_increment,
    adres varchar(20),
    adi varchar(20),
    il_id int,
    yol_tarifi varchar(500),
    FOREIGN KEY (il_id) REFERENCES il(id) ON DELETE SET NULL
    );

create table kisi(
    id int primary key auto_increment,
    adi varchar(20),
    soyadi varchar(20),
    yas int,
    tel_no varchar(11),
    sifre varchar(20),
    kullanici_adi varchar(20) unique,
    biyografi varchar(20),
    grup_id int UNSIGNED DEFAULT NULL,
    referans_id int UNSIGNED DEFAULT NULL,
    adres_id int UNSIGNED DEFAULT NULL,
    FOREIGN KEY (grup_id) REFERENCES grup(id) ON DELETE SET NULL,
    FOREIGN KEY (adres_id) REFERENCES adres(id) ON DELETE SET NULL,
    FOREIGN KEY (referans_id) REFERENCES referans(id) ON DELETE SET NULL
    );
create table kisi_yorum(
    id int primary key auto_increment,
    kisi_id int,
    yorum_id int,
    FOREIGN KEY (kisi_id) REFERENCES kisi(id) ON DELETE SET NULL,
    FOREIGN KEY (yorum_id) REFERENCES yorum(id) ON DELETE SET NULL
    );
create table `is`(
    id int primary key auto_increment,
    baslik varchar(20),
    aciklama varchar(20),
    kisi_id int,
    FOREIGN KEY (kisi_id) REFERENCES kisi(id) ON DELETE SET NULL
    );
create table basvuru(
    id int primary key auto_increment,
    aciklama varchar(20),
    kisi_id int,
    FOREIGN KEY (kisi_id) REFERENCES kisi(id) ON DELETE SET NULL
    );
create table is_basvuru(    
    id int primary key auto_increment,
    basvuru_id int,
    is_id int,
    FOREIGN KEY (basvuru_id) REFERENCES basvuru(id) ON DELETE SET NULL,
    FOREIGN KEY (is_id) REFERENCES `is`(id) ON DELETE SET NULL
    );
