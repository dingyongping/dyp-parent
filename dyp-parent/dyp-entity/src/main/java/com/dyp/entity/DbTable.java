package com.dyp.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 数据表
 * </p>
 *
 * @author dyp
 * @since 2019-07-28
 */
@TableName("db_table")
public class DbTable extends Model<DbTable> {

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
     * 数据源配置id
     */
	@TableField("data_source_id")
	private String dataSourceId;
    /**
     * 编码
     */
	private String code;
    /**
     * 英文名称
     */
	@TableField("dbet_name")
	private String dbetName;
    /**
     * 中文名称
     */
	@TableField("dbct_name")
	private String dbctName;
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

	public DbTable setId(String id) {
		this.id = id;
		return this;
	}

	public String getFatherUuid() {
		return fatherUuid;
	}

	public DbTable setFatherUuid(String fatherUuid) {
		this.fatherUuid = fatherUuid;
		return this;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public DbTable setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
		return this;
	}

	public String getCode() {
		return code;
	}

	public DbTable setCode(String code) {
		this.code = code;
		return this;
	}

	public String getDbetName() {
		return dbetName;
	}

	public DbTable setDbetName(String dbetName) {
		this.dbetName = dbetName;
		return this;
	}

	public String getDbctName() {
		return dbctName;
	}

	public DbTable setDbctName(String dbctName) {
		this.dbctName = dbctName;
		return this;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public DbTable setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public DbTable setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public DbTable setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public DbTable setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public DbTable setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public DbTable setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
