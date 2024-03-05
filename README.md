#資料庫使用mysql，username、password使用建DB時設定的username跟password  
#sql指令

#myjdbc1  
CREATE DATABASE myjdbc1;  
CREATE TABLE account  
(  
    userid INT PRIMARY KEY,  
    name VARCHAR(30),  
    balance INT  
);  
INSERT INTO account (userid, name, balance) VALUES (1, 'Judy', 9000);  
INSERT INTO account (userid, name, balance) VALUES (2, 'Kevin', 2000);  
INSERT INTO account (userid, name, balance) VALUES (3, 'Tom', 1000);  
  
#myjdbc2  
CREATE DATABASE myjdbc2;  
CREATE TABLE bonus(  
    userid INT PRIMARY KEY,  
    bonus_money INT  
);  
INSERT INTO bonus (userid, bonus_money) VALUES (1,10);  
INSERT INTO bonus (userid, bonus_money) VALUES (2,20);  
INSERT INTO bonus (userid, bonus_money) VALUES (3,30);  
  
#test api  
http://localhost:8080/transactional/transfer  
POST  
{  
    "from":1,  
    "to":2,  
    "money":1000  
}  
  
http://localhost:8080/transactional/transfer_bonus  
POST  
{  
    "from":1,  
    "to":2,  
    "money":1000  
}  