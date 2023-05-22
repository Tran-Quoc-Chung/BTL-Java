USE pharma_swing;
DELIMITER $$
DROP TRIGGER IF EXISTS birthday_voucher;
CREATE TRIGGER birthday_voucher
AFTER INSERT ON customer
FOR EACH ROW
BEGIN
    IF MONTH(NEW.datecustomer) = MONTH(CURRENT_DATE()) AND DAY(NEW.datecustomer) = DAY(CURRENT_DATE()) THEN
        INSERT INTO voucher (codevoucher, startdate, enddate, discount, active)
        VALUES (CONCAT('hpbd_', NEW.phonenumber), CURRENT_DATE(), DATE_ADD(CURRENT_DATE(), INTERVAL 7 DAY), 0.2, 1);
    END IF;
	UPDATE voucher SET active = false WHERE enddate < NOW() AND active = true;
END $$
