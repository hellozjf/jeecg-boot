package org.jeecg.modules.tool.controller;

import cn.binarywang.tools.generator.ChineseAddressGenerator;
import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;
import cn.binarywang.tools.generator.ChineseNameGenerator;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.tool.model.GenerateDateModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Jingfeng Zhou
 */
@RestController
@RequestMapping("/tool/random")
@Api(tags="随机")
@Slf4j
public class RandomController {

    @ApiOperation("生成姓名")
    @RequestMapping(value = "/generateName", method = RequestMethod.GET)
    public Result<String> generateName(@RequestParam(name = "odd", defaultValue = "false") boolean odd){
        Result<String> result = new Result<>();
        String generatedName = null;
        if (odd) {
            generatedName = ChineseNameGenerator.getInstance().generateOdd();
        } else {
            generatedName = ChineseNameGenerator.getInstance().generate();
        }
        result.setResult(generatedName);
        return result;
    }

    @ApiOperation("生成地址")
    @RequestMapping(value = "/generateAddress", method = RequestMethod.GET)
    public Result<String> generateAddress(){
        Result<String> result = new Result<>();
        String generatedAddress = ChineseAddressGenerator.getInstance().generate();
        result.setResult(generatedAddress);
        return result;
    }

    @ApiOperation("生成手机号码")
    @RequestMapping(value = "/generateMobileNumber", method = RequestMethod.GET)
    public Result<String> generateMobileNumber(@RequestParam(name = "fake", defaultValue = "true") boolean fake){
        Result<String> result = new Result<>();
        String generatedAddress;
        if (fake) {
            generatedAddress = ChineseMobileNumberGenerator.getInstance().generateFake();
        } else {
            generatedAddress = ChineseMobileNumberGenerator.getInstance().generate();
        }
        result.setResult(generatedAddress);
        return result;
    }

    @ApiOperation("生成日期")
    @RequestMapping(value = "/generateDate", method = RequestMethod.GET)
    public Result<String> generateDate(GenerateDateModel generateDateModel) {
        Result<String> result = new Result<>();
        if (generateDateModel.getStartDate() == null ||
            generateDateModel.getEndDate() == null ||
            generateDateModel.getStartDate().getTime() > generateDateModel.getEndDate().getTime()) {
            result.setResult(null);
            return result;
        }

        long startTimestamp = generateDateModel.getStartDate().getTime();
        long endTimestamp = generateDateModel.getEndDate().getTime();
        long generatedTimestamp = RandomUtil.randomLong(startTimestamp, endTimestamp + 1000 * 60 * 60 * 24);
        Date date = new Date(generatedTimestamp);
        result.setResult(DateUtil.formatDate(date));
        return result;
    }
}
