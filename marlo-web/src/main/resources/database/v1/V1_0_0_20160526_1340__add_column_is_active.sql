ALTER TABLE crp_assumptions ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crp_cluster_of_activities ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crp_milestones ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crp_outcome_sub_idos ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crp_parameters ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crp_program_leaders ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crp_program_outcomes ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crp_programs ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crp_sites_leaders ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crp_sub_idos_contributions ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crp_users ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crps ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE crps_sites_integration ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE srf_cross_cutting_issues ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE srf_idos ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE srf_slo_idos ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE srf_slo_indicator_targets ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE srf_slo_indicators ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE srf_slos ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE srf_sub_idos ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
ALTER TABLE srf_target_units ADD COLUMN `is_active`  tinyint(1) NOT NULL  ;
