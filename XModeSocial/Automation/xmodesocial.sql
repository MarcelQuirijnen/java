#CREATE SCHEMA `xmodesocial` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
#CREATE USER 'xmodesocial'@'localhost' IDENTIFIED BY 'xmodesocial';
#GRANT ALL PRIVILEGES ON xmodesocial.* TO 'xmodesocial'@'localhost';
#FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS xmodesocial.location (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  advertiser_id VARCHAR(255) NOT NULL,
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL,
  horizontal_accuracy DOUBLE NOT NULL,
  timestamp datetime(6) NOT NULL,
  PRIMARY KEY (id, advertiser_id)
) ENGINE=InnoDB;

INSERT into xmodesocial.location values (null, '1', '29.7604', '95.3698', '0.0001', '2017-05-01 00:00:01');
INSERT into xmodesocial.location values (null, '10', '29.7604', '95.3698', '0.00001', '2017-04-30 23:59:59');
INSERT into xmodesocial.location values (null, '12', '32.7767', '96.7970', '0.00001', '2017-05-01 10:00:01');
INSERT into xmodesocial.location values (null, '31', '25.7617', '80.1918', '0.00011', '2017-05-02 02:00:01');
INSERT into xmodesocial.location values (null, '14', '29.4241', '98.4936', '0.00001', '2017-05-01 00:00:01');
INSERT into xmodesocial.location values (null, '51', '40.7128', '74.0060', '0.00021', '2017-06-01 00:00:01');
INSERT into xmodesocial.location values (null, '16', '34.0522', '118.2437', '0.00001', '2017-05-01 23:59:59');
INSERT into xmodesocial.location values (null, '71', '30.2672', '97.7431', '0.00031', '2017-05-01 00:59:01');
INSERT into xmodesocial.location values (null, '18', '41.8781', '87.6298', '0.00001', '2017-05-01 05:00:01');
INSERT into xmodesocial.location values (null, '91', '29.9511', '90.0715', '0.00091', '2017-05-01 00:07:01');
