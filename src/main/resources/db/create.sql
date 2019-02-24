CREATE TABLE brand (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  code VARCHAR(45) NOT NULL COMMENT 'code',
  name VARCHAR(45) NOT NULL COMMENT 'name',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX udx_brand_code_type (code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE category (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  code VARCHAR(45) NOT NULL COMMENT 'code',
  name VARCHAR(45) NOT NULL COMMENT 'name',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX udx_category_code_type (code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE brand_category_mapping (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  brand_code VARCHAR(45) NOT NULL COMMENT '品牌code',
  category_code VARCHAR(45) NOT NULL COMMENT '品类code',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX udx_brand_category_mapping (category_code,brand_code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE goods (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  brand_code VARCHAR(45) NOT NULL COMMENT '品牌',
  goods_code VARCHAR(45) NOT NULL COMMENT 'code',
  goods_name VARCHAR(45) NOT NULL COMMENT 'name',
  illustration VARCHAR(4000) NOT NULL DEFAULT '' COMMENT '产品说明',
  url VARCHAR(4000) NOT NULL DEFAULT '' COMMENT 'url',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX udx_goods_brand_type (goods_code,brand_code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE goods_category_mapping (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  category_code VARCHAR(45) NOT NULL COMMENT '品类',
  goods_code VARCHAR(45) NOT NULL COMMENT 'sku',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX udx_goods_category_mapping_goods_category_type (goods_code,category_code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE sku (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  brand_code VARCHAR(45) NOT NULL COMMENT 'brand',
  goods_code VARCHAR(45) NOT NULL COMMENT 'goods',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  sku_code VARCHAR(45) NOT NULL COMMENT 'sku',
  sku_name VARCHAR(45) NOT NULL COMMENT 'name',
  sku_by_name VARCHAR(45) NOT NULL COMMENT '别名',
  url VARCHAR(4000) NOT NULL DEFAULT '' COMMENT 'url',
  extension JSON DEFAULT NULL COMMENT '扩展信息',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  INDEX idx_sku_goods_code (goods_code),
  INDEX idx_sku_sku_code (sku_code),
  UNIQUE INDEX idx_sku_sku_brand_type (sku_code,brand_code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_info (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  source VARCHAR(64) NOT NULL COMMENT '来源',
  open_id  VARCHAR(64) NOT NULL COMMENT '用户id',
  avatar VARCHAR(64) NOT NULL COMMENT '头像文件',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  INDEX idx_user_info_open_id (open_id)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_favorites (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  source VARCHAR(64) NOT NULL COMMENT '来源',
  open_id  VARCHAR(64) NOT NULL COMMENT '用户id',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  brand_code VARCHAR(45) NOT NULL COMMENT 'brand code',
  brand_name VARCHAR(45) NOT NULL COMMENT 'brand name',
  goods_code VARCHAR(45) NOT NULL COMMENT 'goods code',
  goods_name VARCHAR(45) NOT NULL COMMENT 'goods name',
  goods_illustration VARCHAR(4000) NOT NULL DEFAULT '' COMMENT '产品说明',
  goods_url VARCHAR(4000) NOT NULL DEFAULT '' COMMENT '产品url',
  sku_code VARCHAR(45) NOT NULL COMMENT 'sku code',
  sku_name VARCHAR(45) NOT NULL COMMENT 'name code',
  sku_by_name VARCHAR(45) NOT NULL COMMENT '别名',
  sku_url VARCHAR(4000) NOT NULL DEFAULT '' COMMENT 'sku url',
  sku_extension JSON DEFAULT NULL COMMENT 'sku 扩展信息',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  INDEX idx_user_stock_open_id_sku_code (open_id,sku_code)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;