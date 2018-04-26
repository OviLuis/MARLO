SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Procedure structure for assignBIPermissions
-- ----------------------------
DROP PROCEDURE
IF EXISTS marlodb.`assignBIPermissions`;
DELIMITER ;;

CREATE PROCEDURE marlodb.`assignBIPermissions` (IN `userID` bigint,`typeBi` TINYINT(1),`urlBI` varchar(500), `userBI` varchar(500), `passwordBI` varchar(500))
BEGIN

if (select COUNT('x') from bi_permissions p  where p.user=`userID` and p.type = `typeBi`) =0 THEN
    insert into bi_permissions (`type`,`user`,`urlbi`,`userBi`, `userPass`,`is_active`) values (`typeBi`,`userID`,`urlBI`,`userBI`,`passwordBI`,1);
END IF;

END;;
DELIMITER;

