-- Migrasi database untuk Praktikum PBO Pertemuan 11
-- Jalankan pada database db_sikursus melalui phpMyAdmin / HeidiSQL.
CREATE DATABASE IF NOT EXISTS db_sikursus
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE db_sikursus;

CREATE TABLE IF NOT EXISTS users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','OPERATOR') NOT NULL DEFAULT 'OPERATOR',
    aktif BOOLEAN NOT NULL DEFAULT TRUE
);

-- Akun demo dibuat dengan hash PBKDF2 oleh util.UserSeeder.
-- Jangan memasukkan password asli ke kolom password_hash secara manual.
