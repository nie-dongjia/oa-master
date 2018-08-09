DROP DATABASE DB_Activiti;
CREATE DATABASE DB_Activiti CHARACTER SET utf8 COLLATE utf8_general_ci;
USE DB_Activiti;
SELECT VERSION();
SELECT COUNT(*) TABLES, table_schema FROM information_schema.TABLES   WHERE table_schema = 'DB_Activiti' GROUP BY table_schema;

CREATE DATABASE `DB_Act` CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE DATABASE `DB_Act2` CHARACTER SET utf8 COLLATE utf8_general_ci;



