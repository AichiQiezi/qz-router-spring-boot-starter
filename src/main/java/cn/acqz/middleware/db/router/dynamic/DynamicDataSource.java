package cn.acqz.middleware.db.router.dynamic;

import cn.acqz.middleware.db.router.DBContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源获取，每当切换数据源，都要从这个里面进行获取
 * @author feng
 * @date 2023/7/27 21:06
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return "db" + DBContextHolder.getDBKey();
    }
}
