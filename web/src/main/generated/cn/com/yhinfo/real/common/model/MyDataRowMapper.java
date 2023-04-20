package cn.com.yhinfo.real.common.model;

/**
 * Mapper for {@link MyData}.
 * NOTE: This class has been automatically generated from the {@link MyData} original class using Vert.x codegen.
 */
@io.vertx.codegen.annotations.VertxGen
public interface MyDataRowMapper extends io.vertx.sqlclient.templates.RowMapper<MyData> {

  @io.vertx.codegen.annotations.GenIgnore
  MyDataRowMapper INSTANCE = new MyDataRowMapper() { };

  @io.vertx.codegen.annotations.GenIgnore
  java.util.stream.Collector<io.vertx.sqlclient.Row, ?, java.util.List<MyData>> COLLECTOR = java.util.stream.Collectors.mapping(INSTANCE::map, java.util.stream.Collectors.toList());

  @io.vertx.codegen.annotations.GenIgnore
  default MyData map(io.vertx.sqlclient.Row row) {
    MyData obj = new MyData();
    Object val;
    int idx;
    if ((idx = row.getColumnIndex("id")) != -1 && (val = row.getString(idx)) != null) {
      obj.setId((java.lang.String)val);
    }
    if ((idx = row.getColumnIndex("max_size")) != -1 && (val = row.getString(idx)) != null) {
      obj.setMaxSize((java.lang.String)val);
    }
    return obj;
  }
}