package com.caochaojie.springcloud.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.caochaojie.springcloud.sentinel.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caochaojie
 * @date 2022/3/26
 */
@RestController
@Slf4j
public class HelloController {

    public static final String RESOURCE_NAME = "hello";
    public static final String USER_RESOURCE_NAME = "user";
    public static final String DEGRADE_RESOURCE_NAME = "degrade";

    @PostConstruct
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 设置资源
        rule.setResource(RESOURCE_NAME);
        // 限流阈值类型 QPS模式
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护的阈值
        rule.setCount(1);

        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    @PostConstruct
    public void initDegradeRule() {
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource(DEGRADE_RESOURCE_NAME);
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        // 统计时长  单位:ms
        degradeRule.setStatIntervalMs(60 * 1000 * 60);
        // 异常数
        degradeRule.setCount(2);
        // 触发熔断最小请求数
        degradeRule.setMinRequestAmount(2);
        // 熔断的持续时长 单位 秒
        // 一旦触发了熔断 再次请求对应的接口，就会直接调用我们降级的方法
        // 10秒过落后 -半天状态：恢复接口请求调用：如果第一次请求就异常，再次熔断，不会根据设置的条件进行判定
        degradeRule.setTimeWindow(10);
        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);

    }

    @RequestMapping(value = "/hello")
    public String hello() {
        Entry entry = null;
        String str = null;
        try {
            entry = SphU.entry(RESOURCE_NAME);

            str = "hello world";
            log.info("======={}", str);
            return str;
        } catch (BlockException e) {
            log.info("block");
            return "被流控了";
        } catch (Exception e) {
            Tracer.traceEntry(e, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return str;
    }

    /**
     * @return
     */
    @RequestMapping("/user")
//    @SentinelResource(value = USER_RESOURCE_NAME, blockHandler = "blockHandlerForGetUser")
    @SentinelResource(value = USER_RESOURCE_NAME, blockHandler = "blockHandlerForGetUser", fallback = "fallbackHandlerForGetUser")
    public User getUser(String id) {
        int a = 1 / 0;
        return new User("ccj");
    }

    @RequestMapping("/degrade")
    @SentinelResource(value = DEGRADE_RESOURCE_NAME, entryType = EntryType.IN, blockHandler = "blockHandlerForFb")
    public User degrade(String id) {
        throw new RuntimeException("异常");
    }

    public User blockHandlerForFb(String id, BlockException e) {
        return new User("被熔断了");
    }


    /**
     * 注意
     * <p>
     * 1、一定要public
     * 2.返回值一定要和原方法保持一致  ，包含原方法参数
     * 3.可以在参数最后添加BlockException 可以区分是什么规则的处理方法
     *
     * @param id
     * @param ex
     * @return
     */
    public User blockHandlerForGetUser(String id, BlockException ex) {
        ex.printStackTrace();
        return new User("被流控了");
    }

    public User fallbackHandlerForGetUser(String id, Throwable e) {
        e.printStackTrace();
        return new User("异常处理");
    }
}
