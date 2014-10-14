DROP SCHEMA IF EXISTS `oncoblocks`;
CREATE SCHEMA `oncoblocks`;

DROP TABLE IF EXISTS `entrez_gene` ;

CREATE TABLE IF NOT EXISTS `entrez_gene` (
  `entrez_gene_id` INT NOT NULL,
  `tax_id` INT NOT NULL,
  `locus_tag` VARCHAR(32) NULL,
  `primary_gene_symbol` VARCHAR(30) NOT NULL,
  `chromosome` VARCHAR(10) NOT NULL,
  `chromosome_location` VARCHAR(64) NULL,
  `description` VARCHAR(1024) NOT NULL,
  `gene_type` VARCHAR(32) NULL,
  `db_xrefs` VARCHAR(256) NULL,
  `gene_symbol_aliases` VARCHAR (1024) NULL,
  `is_cgc_gene` VARCHAR(1) NULL,
  `is_kinase` VARCHAR(1) NULL,
  PRIMARY KEY (`entrez_gene_id`))
;

DROP TABLE IF EXISTS `cell_lines` ;

CREATE TABLE IF NOT EXISTS `cell_lines` (
  `cell_line_id` INT NOT NULL AUTO_INCREMENT,
  `ccle_name` VARCHAR(128) NOT NULL,
  `primary_name` VARCHAR(32) NOT NULL,
  `gender` VARCHAR(1) NULL COMMENT '	',
  `site_primary` VARCHAR(256) NOT NULL,
  `histology_primary` VARCHAR(256) NULL,
  `histology_subtype` VARCHAR(256) NULL,
  `notes` VARCHAR(1024) NULL,
  `source` VARCHAR(32) NULL,
  PRIMARY KEY (`cell_line_id`))
;

DROP TABLE IF EXISTS `rnaseq_gene_expression` ;

CREATE TABLE IF NOT EXISTS `rnaseq_gene_expression` (
  `rnaseq_gene_expression_id` INT NOT NULL AUTO_INCREMENT,
  `cell_line_id` INT NOT NULL,
  `entrez_gene_id` INT NOT NULL,
  `accession` VARCHAR(20) NOT NULL,
  `value` DOUBLE NOT NULL,
  PRIMARY KEY (`cell_line_id`, `entrez_gene_id`, `accession`))
;
