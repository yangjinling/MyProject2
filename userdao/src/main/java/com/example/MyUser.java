package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyUser {

    public static void main(String[] args) throws Exception {
        //数据库的版本号  src-main-java-gen自动生成的包名
        Schema schema = new Schema(1, "com.user.dao");
        //      当然，如果你愿意，你也可以分别指定生成的 Bean 与 DAO 类所在的目录，只要如下所示：
//      Schema schema = new Schema(1, "me.itangqi.bean");
//      schema.setDefaultJavaPackageDao("me.itangqi.dao");


        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
        addUser(schema);

        new DaoGenerator().generateAll(schema, "./mygreendao/src/main/java-gen");
    }

    public static void addUser(Schema schema) {
        //表名（类名）
        Entity user = schema.addEntity("user");
        // 你也可以重新给表命名
        // user.setTableName("");
        // 接下来你便可以设置表中的字段：
        user.addIdProperty();
        user.addStringProperty("name");
        user.addStringProperty("age");
        user.addStringProperty("adress");
        user.addIntProperty("sex");

    }
}
