USE pharma_swing;

CREATE TABLE  `account`  (
  username varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
   position  varchar(45) NOT NULL,
   namestaff  varchar(45) NOT NULL,
   idstaff  int NOT NULL AUTO_INCREMENT,
   `active`  boolean NOT NULL DEFAULT '1',
   id_position  int DEFAULT NULL,
  PRIMARY KEY ( idstaff ),
  KEY ( id_position )
);

CREATE TABLE  voucher  (
   codevoucher  varchar(45) NOT NULL,
   idvoucher  int NOT NULL AUTO_INCREMENT,
   startdate  date NOT NULL,
   enddate  date NOT NULL,
   discount  float NOT NULL,
   `active`  boolean DEFAULT '0',
  PRIMARY KEY ( idvoucher ),
  UNIQUE KEY ( codevoucher )
) ;

CREATE TABLE  pharma  (
   id  int NOT NULL,
  ` Name`  varchar(50) NOT NULL,
  `invisible ` tinyint DEFAULT '1',
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id )
);
CREATE TABLE  loginhistory  (
   id  int NOT NULL AUTO_INCREMENT,
   id_user  int NOT NULL,
   `position`  int NOT NULL,
   login_time  datetime NOT NULL,
   logout_time  datetime NOT NULL,
   transactions  int DEFAULT NULL,
     PRIMARY KEY ( id ),
  CONSTRAINT  FK_account_loginhistory  FOREIGN KEY ( id_user ) REFERENCES  account  ( idstaff )
) ;

CREATE TABLE  bill  (
   idBill  int NOT NULL AUTO_INCREMENT,
   dateBill  datetime NOT NULL,
   totalBill  float NOT NULL,
   customer  varchar(45) DEFAULT NULL,
   cashier  varchar(45) NOT NULL,
   voucher  int DEFAULT NULL,
   points  int DEFAULT NULL,
   phoneCustomer  varchar(45) DEFAULT NULL,
   idvoucher  int DEFAULT NULL,
  PRIMARY KEY ( idBill ),
  UNIQUE KEY  idBill_UNIQUE  ( idBill ),
  CONSTRAINT  fk_customer_bill  FOREIGN KEY ( phoneCustomer ) REFERENCES  customer  ( phonenumber ),
  CONSTRAINT  fk_customer_voucher  FOREIGN KEY ( idvoucher ) REFERENCES  voucher  ( idvoucher )
) ;

CREATE TABLE  customer  (
   phonenumber  varchar(10) NOT NULL,
   customername  varchar(45) NOT NULL,
   datecustomer  date NOT NULL,
   points  int DEFAULT NULL,
  PRIMARY KEY ( phonenumber )
);


CREATE TABLE  detailbill  (
   iddetailBill  int NOT NULL AUTO_INCREMENT,
   idfkDetailBill  int NOT NULL,
   idDrugs  int NOT NULL,
   quantityDrugs  int NOT NULL,
   nameDrugs  varchar(45) NOT NULL,
   totalDrugs  int NOT NULL,
  PRIMARY KEY ( iddetailBill ),
  CONSTRAINT  fk_detailbill_drugs  FOREIGN KEY ( idDrugs ) REFERENCES  drugslist  ( code ),
  CONSTRAINT  FK_detailbill_idfkDetailBill  FOREIGN KEY ( idfkDetailBill ) REFERENCES  bill  ( idBill )
);


CREATE TABLE  drugslist  (
   `code`  int NOT NULL,
   `name`  varchar(45) NOT NULL,
   price  int NOT NULL,
   idpk  int NOT NULL,
   invisible  boolean NOT NULL DEFAULT '1',
  PRIMARY KEY ( `code` ),
  CONSTRAINT  drugslist_ibfk_1  FOREIGN KEY ( idpk ) REFERENCES  pharmalv2  ( id ) ON DELETE CASCADE,
  CONSTRAINT  FK_drugslist_pharmalv2  FOREIGN KEY ( idpk ) REFERENCES  pharmalv2  ( id )
) ;


CREATE TABLE  menu  (
   idmenu  int NOT NULL AUTO_INCREMENT,
   `name`  varchar(45) NOT NULL,
   amount  int NOT NULL,
   price  int NOT NULL,
   iddrugs  int NOT NULL,
   stt  int NOT NULL DEFAULT '1',
  PRIMARY KEY ( idmenu )
);

CREATE TABLE  permission  (
   id  int NOT NULL,
   permission_name  varchar(45) NOT NULL,
   permission_idfk  int NOT NULL,
  PRIMARY KEY ( id ),
  CONSTRAINT  FK_position_permission  FOREIGN KEY ( permission_idfk ) REFERENCES  position  ( idposition )
) ;



CREATE TABLE  pharmalv2  (
   id  int NOT NULL,
   `name`  varchar(45) NOT NULL,
   idpk  int NOT NULL,
   invisible  boolean NOT NULL DEFAULT '1',
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id ),
  KEY  idpk  ( idpk ),
  CONSTRAINT  FK_pharmalv2_pharma  FOREIGN KEY ( idpk ) REFERENCES  pharma  ( id ),
  CONSTRAINT  pharmalv2_ibfk_1  FOREIGN KEY ( idpk ) REFERENCES  pharma  ( id ) ON DELETE CASCADE
) ;

CREATE TABLE  position  (
   idposition  int NOT NULL,
   nameposition  varchar(45) NOT NULL,
   idfkpermission  int NOT NULL,
  PRIMARY KEY ( idposition ),
  CONSTRAINT  FK_account_position  FOREIGN KEY ( idposition ) REFERENCES  account  ( id_position )
) ;

CREATE TABLE  transaction_detail  (
   id  int NOT NULL AUTO_INCREMENT,
   transaction_idfk  int NOT NULL,
   transaction_time  datetime NOT NULL,
   id_bill  int NOT NULL,
   total_bill  int NOT NULL,
  PRIMARY KEY ( id ),
  CONSTRAINT  fk_transactiondetail_bill  FOREIGN KEY ( id_bill ) REFERENCES  bill  ( idBill ),
  CONSTRAINT  fk_transactiondetail_loginhistory  FOREIGN KEY ( transaction_idfk ) REFERENCES  loginhistory  ( id )
);


