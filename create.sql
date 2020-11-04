CREATE DATABASE news;

\c news

CREATE TABLE news(
    id SERIAL PRIMARY KEY,
    title VARCHAR,
    content VARCHAR,
    departmentId VARCHAR
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    userPosition VARCHAR,
    role VARCHAR,
    departmentId VARCHAR
);

CREATE TABLE departments(
    id SERIAL PRIMARY KEY,
    departmentName VARCHAR,
    description VARCHAR
);