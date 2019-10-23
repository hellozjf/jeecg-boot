package org.jeecg.modules.sc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.sc.entity.ScDept;
import org.jeecg.modules.sc.service.IScDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: sc_dept
 * @Author: jeecg-boot
 * @Date:   2019-10-23
 * @Version: V1.0
 */
@RestController
@RequestMapping("/sc/scDept")
@Slf4j
public class ScDeptController extends JeecgController<ScDept, IScDeptService> {
	@Autowired
	private IScDeptService scDeptService;
	
	/**
	 * 分页列表查询
	 *
	 * @param scDept
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ScDept scDept,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ScDept> queryWrapper = QueryGenerator.initQueryWrapper(scDept, req.getParameterMap());
		Page<ScDept> page = new Page<ScDept>(pageNo, pageSize);
		IPage<ScDept> pageList = scDeptService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param scDept
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ScDept scDept) {
		scDeptService.save(scDept);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param scDept
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ScDept scDept) {
		scDeptService.updateById(scDept);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		scDeptService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.scDeptService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ScDept scDept = scDeptService.getById(id);
		if(scDept==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(scDept);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param scDept
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ScDept scDept) {
        return super.exportXls(request, scDept, ScDept.class, "sc_dept");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ScDept.class);
    }

}
