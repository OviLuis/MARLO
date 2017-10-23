SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `agreements`;
CREATE TABLE `agreements` (
`id` varchar (20) not null,
`description` text not null,
`short_title` varchar(255),
`objectives` text,
`donor_id` varchar(50),
`donor` varchar(255),
`original_donor_id` varchar(50),
`original_donor` varchar(255),
`research_id` varchar(50),
`reasearch_name` varchar(255),
`grant_ammount` varchar(255),
`start_date` date,
`end_date` date,
`extension_date` date,
`contract_status` varchar(50),
`funding_type` varchar(50),
`sync_date` datetime,
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1369 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `countries_agreement`;
CREATE TABLE `countries_agreement`(
`id` bigint NOT NULL AUTO_INCREMENT,
`agreement_id` varchar(20) not null,
`code` varchar(50) not null,
`description` text not null,
`percentage` double(5,2),
PRIMARY KEY (`id`),
KEY `agreement_id` (`agreement_id`) USING BTREE,
CONSTRAINT `countries_agreement_fk1` FOREIGN KEY (`agreement_id`) REFERENCES `agreements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1369 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `crps_agreement`;
CREATE TABLE `crps_agreement`(
`id` bigint not null auto_increment,
`agreement_id` varchar(20) not null,
`crp_id` varchar(20) not null,
`description` text not null,
`percentage` double (5,2),
PRIMARY KEY (`id`),
KEY `agreement_id` (`agreement_id`) USING BTREE,
CONSTRAINT `crps_agreement_fk1` FOREIGN KEY (`agreement_id`) REFERENCES `agreements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1369 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `plas_agreement`;
CREATE TABLE `plas_agreement`(
 `id` bigint not null auto_increment,
 `agreement_id` varchar(20) not null,
 `pla_id` varchar(20) not null,
 `description` text not null,
 `ammount` double(50,2),
 PRIMARY KEY (`id`),
 KEY `agreement_id` (`agreement_id`) USING BTREE,
 CONSTRAINT `plas_agreement_fk1` FOREIGN KEY (`agreement_id`) REFERENCES `agreements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1369 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `funding_sources_agreements`;

