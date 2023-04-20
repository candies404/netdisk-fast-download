package cn.com.yhinfo.real.common.interceptorImpl;

import cn.com.yhinfo.core.base.BaseHttpApi;
import cn.com.yhinfo.core.interceptor.Interceptor;
import cn.com.yhinfo.core.model.JsonResult;
import cn.com.yhinfo.core.util.CommonUtil;
import cn.com.yhinfo.core.util.SharedDataUtil;
import cn.com.yhinfo.core.util.VertxHolder;
import io.vertx.core.json.JsonArray;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * 默认拦截器实现
 * 校验用户是否合法 <br>
 * TODO 暂时只做简单实现
 */
@Slf4j
public class DefaultInterceptor implements Interceptor, BaseHttpApi {

    private final JsonArray ignores = SharedDataUtil.getJsonArrayForCustomConfig("ignoresReg");

    @Override
    public void handle(RoutingContext ctx) {

        // 判断是否忽略
        if (CommonUtil.matchRegList(ignores.getList(), ctx.request().path())) {
            ctx.next();
            return;
        }
        // 执行拦截
        val token = ctx.request().getHeader("token");


        LocalMap<String, String> tokenMap = SharedDataUtil.getLocalMapWithCast("token");
        if (token != null && tokenMap != null && tokenMap.containsKey(token)) {
            VertxHolder.getVertxInstance().getOrCreateContext().put("username", tokenMap.get(token));
            ctx.next();
        } else {
            fireJsonResponse(ctx, JsonResult.error("没有权限", 401));
        }
    }
}