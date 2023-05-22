CREATE EVENT birthday_voucher_event
ON SCHEDULE EVERY 1 DAY
STARTS '2023-05-15 00'
DO
  CALL birthday_voucher();
DROP EVENT outdate_voucher_event;

CREATE EVENT outdate_voucher_event
ON SCHEDULE EVERY 1 DAY
STARTS '2023-05-15 00'
DO
  CALL check_outdate_voucher();
  
