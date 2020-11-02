CREATE DATABASE newsapi;

\c newsapi

CREATE TABLE news(
    id SERIAL PRIMARY KEY,
    title VARCHAR,
    content VARCHAR,
    departmentId INTEGER
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    userName VARCHAR,
    userPosition VARCHAR,
    userRole VARCHAR,
    departmentId INTEGER
);

CREATE TABLE departments(
    id SERIAL PRIMARY KEY,
    departmentName VARCHAR,
    description VARCHAR
);