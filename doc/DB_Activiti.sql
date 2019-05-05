DROP DATABASE DB_Activiti;
CREATE DATABASE DB_Activiti CHARACTER SET utf8 COLLATE utf8_general_ci;
USE DB_Activiti;
SELECT VERSION();
SELECT COUNT(*) TABLES, table_schema FROM information_schema.TABLES   WHERE table_schema = 'DB_Activiti' GROUP BY table_schema;




SELECT TRANSACTION;

SHOW DATABASES;

SHOW COLUMNS FROM act_evt_log; 	

SHOW COLUMNS FROM `act_id_info`


SHOW STATUS;

SHOW GRANTS;

SELECT * FROM t_sys_userinfo LIMIT 2
SELECT COUNT(1) FROM t_sys_userinfo 



SHOW VARIABLES LIKE 'slow%';
SET GLOBAL slow_query_log = ON;
SELECT VERSION();
SELECT * FROM `act_hi_taskinst` ORDER BY ID_ DESC LIMIT 2
-- 
SELECT * FROM `act_hi_taskinst` ORDER BY ID_ DESC LIMIT 4,2 
-- 

DESCRIBE act_hi_taskinst









