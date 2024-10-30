package com.algorithm;

/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.po.ArrangePO;
import com.vo.ArrangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArrangeTask {
    Strategy strategy;
    ArrangePO arrangePO;

    @Autowired
    TimeStrategy timeStrategy;
    @Autowired
    ResourceStrategy resourceStrategy;
    @Autowired
    PositionStrategy positionStrategy;
    @Autowired
    TimeResourceStrategy timeResourceStrategy;
    @Autowired
    TimePositionStrategy timePositionStrategy;
    @Autowired
    ResourcePositionStrategy resourcePositionStrategy;
    @Autowired
    ThreeStrategy threeStrategy;

    //标准的写法这里是个构造方法，但是我这里使用容器统一管理对象
    //不能使用new语句，比如strategy = new TimeStrategy();
    public void setTaskType(ArrangePO arrangePO) {
        String str = arrangePO.getStrategy();
        switch (str) {
            case ("time"):
                strategy = this.timeStrategy;
                break;
            case ("resource"):
                strategy = this.resourceStrategy;
                break;
            case ("position"):
                strategy = this.positionStrategy;
                break;
            case ("time,resource"):
                strategy = this.timeResourceStrategy;
                break;
            case ("time,position"):
                strategy = this.timePositionStrategy;
                break;
            case ("resource,position"):
                strategy = this.resourcePositionStrategy;
                break;
            case ("time,resource,position"):
                strategy = this.threeStrategy;
                break;
            default:
                strategy = null;
        }
        this.arrangePO = arrangePO;
    }

    //每次使用前可以先修改策略
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    //获取结果
    public List<ArrangeVO> getStrategyResult() {
        if(strategy!=null){
            return strategy.arrange(this.arrangePO);
        }else{
            return null;
        }
    }
}
