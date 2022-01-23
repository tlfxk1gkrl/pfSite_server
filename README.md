# pf_server

SPRING BOOT PROJECT

Tool : IntelliJ
-------------------------------------------------
TABLES
------------------------------------------------

CREATE TABLE TB_BOARD(

    ID int primary key auto_increment,
	
    TITLE text not null,
    
    TOOL VARCHAR(255) NOT NULL,
    
    PERIOD int not null,
    
    SUB MEDIUMTEXT not null
    
);

TEXT = 64MB

MEDIUMTEXT = 16MB

LONGTEXT = 4GB
  
