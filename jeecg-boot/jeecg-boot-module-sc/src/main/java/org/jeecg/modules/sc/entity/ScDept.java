package org.jeecg.modules.sc.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: sc_dept
 * @Author: jeecg-boot
 * @Date:   2019-10-23
 * @Version: V1.0
 */
@Data
@TableName("sc_dept")
public class ScDept implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**编号*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date updateTime;
	/**删除标识0-正常,1-已删除*/
	@Excel(name = "删除标识0-正常,1-已删除", width = 15)
    private java.lang.Integer delFlag;
	/**名称*/
	@Excel(name = "名称", width = 15)
    private java.lang.String name;
	/**系主任*/
	@Excel(name = "系主任", width = 15)
    private java.lang.String chairman;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    private java.lang.String tel;
	/**desc*/
	@Excel(name = "desc", width = 15)
    private java.lang.String desc;
}
