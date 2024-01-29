package cn.acqz.middleware.db.router.strategy.impl;

import cn.acqz.middleware.db.router.DBContextHolder;
import cn.acqz.middleware.db.router.DBRouterConfig;
import cn.acqz.middleware.db.router.strategy.IDBRouterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 哈希路由
 * 不同的 DBRouterConfig 对应不同的哈希路由
 * @author feng
 * @date 2023/7/27 21:58
 */

public class DBRouterStrategyHashCode implements IDBRouterStrategy {
    private Logger logger = LoggerFactory.getLogger(DBRouterStrategyHashCode.class);
    private DBRouterConfig dbRouterConfig;

    public DBRouterStrategyHashCode(DBRouterConfig dbRouterConfig) {
        this.dbRouterConfig = dbRouterConfig;
    }

    @Override
    public void doRouter(String dbKeyAttr) {
        // 参考 hashmap 的 hash 计算，让散列更加均匀
        int size = dbRouterConfig.getDbCount() * dbRouterConfig.getTbCount();
        int idx = (size - 1) & (dbKeyAttr.hashCode() ^ (dbKeyAttr.hashCode() >>> 16));
        // 计算对应库编号和表编号
        int dbIdx = idx / dbRouterConfig.getTbCount() + 1;
        int tbIdx = idx - dbRouterConfig.getTbCount() * (dbIdx - 1);
        //保存到线程上下文中
        DBContextHolder.setDBKey(String.format("%02d", dbIdx));
        DBContextHolder.setTBKey(String.format("%03d", tbIdx));
        logger.debug("数据库路由 dbIdx：{} tbIdx：{}",  dbIdx, tbIdx);
    }

    @Override
    public void setDBKey(int dbIdx) {
        DBContextHolder.setDBKey(String.format("%02d", dbIdx));
    }

    @Override
    public void setTBKey(int tbIdx) {
        DBContextHolder.setTBKey(String.format("%03d", tbIdx));
    }


    @Override
    public int dbCount() {
        return dbRouterConfig.getDbCount();
    }

    @Override
    public int tbCount() {
        return dbRouterConfig.getTbCount();
    }

    @Override
    public void clear() {
        DBContextHolder.clearDBKey();
        DBContextHolder.clearTBKey();
    }
}
