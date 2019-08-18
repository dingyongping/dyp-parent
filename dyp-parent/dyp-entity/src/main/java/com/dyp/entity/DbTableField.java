package com.dyp.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设置表字段表
 * </p>
 *
 * @author dyp
 * @since 2019-07-28
 */
@TableName("db_table_field")
public class DbTableField extends Model<DbTableField> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键uuid
     */
	private String id;
    /**
     * 父uuid
     */
	@TableField("father_uuid")
	private String fatherUuid;
    /**
     * 数据表id
     */
	@TableField("db_table_id")
	private String dbTableId;
    /**
     * 编码
     */
	private String code;
    /**
     * 英文名称
     */
	@TableField("dbef_name")
	private String dbefName;
    /**
     * 中文名称
     */
	@TableField("dbcf_name")
	private String dbcfName;
    /**
     * 字段类型
     */
	@TableField("dbf_type")
	private String dbfType;
    /**
     * 是否为空  null /not null
     */
	@TableField("not_null")
	private String notNull;
    /**
     * 是否是主键  否 /是
     */
	private String pk;
    /**
     * 排序
     */
	@TableField("order_num")
	private Integer orderNum;
    /**
     * 状态  0：禁用   1：正常
     */
	private Integer status;
    /**
     * 备注
     */
	private String remarks;
    /**
     * 创建者ID
     */
	@TableField("create_user_id")
	private Long createUserId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public String getId() {
		return id;
	}

	public DbTableField setId(String id) {
		this.id = id;
		return this;
	}

	public String getFatherUuid() {
		return fatherUuid;
	}

	public DbTableField setFatherUuid(String fatherUuid) {
		this.fatherUuid = fatherUuid;
		return this;
	}

	public String getDbTableId() {
		return dbTableId;
	}

	public DbTableField setDbTableId(String dbTableId) {
		this.dbTableId = dbTableId;
		return this;
	}

	public String getCode() {
		return code;
	}

	public DbTableField setCode(String code) {
		this.code = code;
		return this;
	}

	public String getDbefName() {
		return dbefName;
	}

	public DbTableField setDbefName(String dbefName) {
		this.dbefName = dbefName;
		return this;
	}

	public String getDbcfName() {
		return dbcfName;
	}

	public DbTableField setDbcfName(String dbcfName) {
		this.dbcfName = dbcfName;
		return this;
	}

	public String getDbfType() {
		return dbfType;
	}

	public DbTableField setDbfType(String dbfType) {
		this.dbfType = dbfType;
		return this;
	}

	public String getNotNull() {
		return notNull;
	}

	public DbTableField setNotNull(String notNull) {
		this.notNull = notNull;
		return this;
	}

	public String getPk() {
		return pk;
	}

	public DbTableField setPk(String pk) {
		this.pk = pk;
		return this;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public DbTableField setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public DbTableField setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public DbTableField setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public DbTableField setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public DbTableField setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public DbTableField setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
