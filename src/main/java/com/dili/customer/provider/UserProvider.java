package com.dili.customer.provider;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONPath;
import com.dili.customer.service.remote.UserRpcService;
import com.dili.ss.dto.DTOUtils;
import com.dili.ss.metadata.BatchProviderMeta;
import com.dili.ss.metadata.FieldMeta;
import com.dili.ss.metadata.ValuePair;
import com.dili.ss.metadata.ValuePairImpl;
import com.dili.ss.metadata.provider.BatchDisplayTextProviderSupport;
import com.dili.uap.sdk.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <B>Description</B>
 * <B>Copyright:本软件源代码版权归农丰时代所有,未经许可不得任意复制与传播.</B>
 * <B>农丰时代科技有限公司</B>
 *
 * @author yuehongbo
 * @date 2020/2/24 18:17
 */
@Component
@Scope("prototype")
public class UserProvider extends BatchDisplayTextProviderSupport {

    @Autowired
    private UserRpcService userService;

    @Override
    public List<ValuePair<?>> getLookupList(Object obj, Map metaMap, FieldMeta fieldMeta) {
        //是否查询有权限的市场中的用户
        Object showAuthMarket = JSONPath.read(String.valueOf(metaMap.get("queryParams")), "/showAuthMarket");
        List<User> list = "true".equalsIgnoreCase(String.valueOf(showAuthMarket)) ? userService.getCurrentAuthMarketUsers(obj.toString()) : userService.getCurrentMarketUser(obj.toString());
        List<ValuePair<?>> resultList = list.stream().map(f -> {
            return (ValuePair<?>) new ValuePairImpl(f.getRealName(), f.getId());
        }).collect(Collectors.toList());
        return resultList;
    }


    @Override
    protected List getFkList(List<String> relationIds, Map metaMap) {
        if (CollectionUtil.isEmpty(relationIds)) {
            return Collections.EMPTY_LIST;
        }
        relationIds = relationIds.stream().distinct().collect(Collectors.toList());
        return userService.listUserByIds(relationIds);
    }

    @Override
    protected BatchProviderMeta getBatchProviderMeta(Map metaMap) {
        BatchProviderMeta batchProviderMeta = DTOUtils.newInstance(BatchProviderMeta.class);
        //设置主DTO和关联DTO需要转义的字段名
        batchProviderMeta.setEscapeFiled("realName");
        //忽略大小写关联
        batchProviderMeta.setIgnoreCaseToRef(true);
        //关联(数据库)表的主键的字段名，默认取id
        batchProviderMeta.setRelationTablePkField("id");
        //当未匹配到数据时，返回的值
        batchProviderMeta.setMismatchHandler(t -> "-");
        return batchProviderMeta;
    }

}
