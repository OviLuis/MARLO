SET FOREIGN_KEY_CHECKS = 0;

CREATE TEMPORARY TABLE
IF NOT EXISTS table2 AS (SELECT * FROM crp_program_outcomes);



CREATE TEMPORARY TABLE
IF NOT EXISTS table_temp_crp_cluster_key_outputs_outcome AS (
SELECT
  pp.*,ppp.description,ppp.crp_program_id
FROM
  crp_cluster_key_outputs_outcome pp
INNER JOIN crp_program_outcomes ppp ON pp.outcome_id = ppp.id)
;

CREATE TEMPORARY TABLE
IF NOT EXISTS table_temp_crp_outcome_sub_idos AS (

SELECT
 pp.*,ppp.description,ppp.crp_program_id
FROM
  crp_outcome_sub_idos pp
INNER JOIN crp_program_outcomes ppp ON pp.crp_program_outcome_id=ppp.id
)
;

CREATE TEMPORARY TABLE
IF NOT EXISTS table_temp_crp_milestones AS (

SELECT
 pp.*,ppp.description,ppp.crp_program_id
FROM
  crp_milestones pp
INNER JOIN crp_program_outcomes ppp ON pp.crp_program_outcome_id=ppp.id
)
;


CREATE TEMPORARY TABLE
IF NOT EXISTS table_temp_crp_assumptions AS (

SELECT
 pp1.*,ppp.description 'adescription',ppp.crp_program_id,pp.srf_sub_ido_id
FROM
  crp_assumptions pp1
inner join crp_outcome_sub_idos pp on pp.id=pp1.crp_outcome_sub_idos_id
INNER JOIN crp_program_outcomes ppp ON pp.crp_program_outcome_id=ppp.id
);


TRUNCATE TABLE crp_program_outcomes;
TRUNCATE TABLE crp_cluster_key_outputs_outcome ;
TRUNCATE TABLE crp_outcome_sub_idos ;
TRUNCATE TABLE crp_milestones ;
TRUNCATE TABLE crp_assumptions ;


ALTER TABLE `crp_program_outcomes`
ADD COLUMN `id_phase`  bigint(20) NULL AFTER `modification_justification`;

ALTER TABLE `crp_program_outcomes` ADD FOREIGN KEY (`id_phase`) REFERENCES `phases` (`id`);

INSERT INTO crp_program_outcomes (
 
description,
`year`,
`value`,
target_unit_id,
crp_program_id,
is_active,
created_by,
active_since,
modified_by,
modification_justification,
id_phase
) SELECT 

t2.description,
t2.`year`,
t2.`value`,
t2.target_unit_id,
t2.crp_program_id,
t2.is_active,
t2.created_by,
t2.active_since,
t2.modified_by,
t2.modification_justification,
  ph.id
FROM
  table2 t2
  inner join crp_programs pr on pr.id=t2.crp_program_id
inner JOIN phases ph ON ph.crp_id=pr.crp_id
;


insert into crp_cluster_key_outputs_outcome (contribution,
key_output_id,
outcome_id,
is_active,
created_by,
active_since,
modified_by,
modification_justification


)
select distinct 
temp.contribution,
temp.key_output_id,
pp.id,
temp.is_active,
temp.created_by,
temp.active_since,
temp.modified_by,
temp.modification_justification

from table_temp_crp_cluster_key_outputs_outcome temp 
INNER JOIN crp_program_outcomes pp on pp.crp_program_id=temp.crp_program_id
and pp.description =temp.description
;



insert into crp_outcome_sub_idos (
contribution,
srf_sub_ido_id,
crp_program_outcome_id,
is_active,
created_by,
active_since,
modified_by,
modification_justification

)
select distinct 
temp.contribution,
temp.srf_sub_ido_id,
pp.id,
temp.is_active,
temp.created_by,
temp.active_since,
temp.modified_by,
temp.modification_justification
from table_temp_crp_outcome_sub_idos temp 
INNER JOIN crp_program_outcomes pp on pp.crp_program_id=temp.crp_program_id
and pp.description =temp.description
;


insert into crp_milestones (title,
`year`,
`value`,
target_unit_id,
crp_program_outcome_id,
is_active,
created_by,
active_since,
modified_by,
modification_justification


)
select distinct 
temp.title,
temp.`year`,
temp.`value`,
temp.target_unit_id,
pp.id,
temp.is_active,
temp.created_by,
temp.active_since,
temp.modified_by,
temp.modification_justification
from table_temp_crp_milestones temp 
INNER JOIN crp_program_outcomes pp on pp.crp_program_id=temp.crp_program_id
and pp.description =temp.description
;


insert into crp_assumptions (description,
crp_outcome_sub_idos_id,
is_active,
created_by,
active_since,
modified_by,
modification_justification



)
select distinct 
temp.description,
temp.crp_outcome_sub_idos_id,
temp.is_active,
temp.created_by,
temp.active_since,
temp.modified_by,
temp.modification_justification
from table_temp_crp_assumptions temp 
INNER JOIN crp_program_outcomes pp on pp.crp_program_id=temp.crp_program_id
and pp.description =temp.adescription
inner join  crp_outcome_sub_idos asp on asp.srf_sub_ido_id=temp.srf_sub_ido_id
;