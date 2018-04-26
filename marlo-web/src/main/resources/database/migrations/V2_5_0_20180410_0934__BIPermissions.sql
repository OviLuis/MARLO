SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bi_permissions
-- ----------------------------
DROP TABLE IF EXISTS `bi_permissions`;
CREATE TABLE `bi_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(1),
  `user` bigint(20),
  `urlbi` text,
  `userBi` text,
  `userPass` text,
  `is_active` tinyint(1) NOT NULL,  
  PRIMARY KEY (`id`),
  CONSTRAINT `bi_permissions_fk` FOREIGN KEY (`user`) REFERENCES `users` (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


/***********************************************
Add Data about permissions and roles
************************************************/
insert into permissions (permission,description,type) VALUES ('crp{0}:Bi:Dashboard','Can access to BI Pentaho Dashboard',1);
insert into permissions (permission,description,type) VALUES ('crp{0}:Bi:Analytics','Can access to BI Pentaho Analytics',1);

-- roles for ccafs
insert into roles (description,acronym,`order`,global_unit_id) VALUES ('BI Dashboard','BID',17,1); 
insert into roles (description,acronym,`order`,global_unit_id) VALUES ('BI Analytics','BIA',18,1);

-- role permissions
insert into role_permissions (role_id,permission_id) 
SELECT (select id from roles where description = 'BI Dashboard'), (select id from permissions where permission = 'crp{0}:Bi:Dashboard');

insert into role_permissions (role_id,permission_id) 
SELECT (select id from roles where description = 'BI Analytics'), (select id from permissions where permission = 'crp{0}:Bi:Analytics');
