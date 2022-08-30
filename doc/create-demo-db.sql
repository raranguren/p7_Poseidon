######  DEMO DATABASE AND USER  ######

DROP DATABASE IF EXISTS demo;
CREATE DATABASE demo;
USE demo;

DROP USER IF EXISTS 'poseidon_demo_user';
CREATE USER 'poseidon_demo_user' IDENTIFIED BY 'demopassword';
GRANT SELECT, INSERT, UPDATE ON demo.* TO 'poseidon_demo_user';