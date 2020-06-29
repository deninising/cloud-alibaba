package com.dennis.springcloud.payment.support;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MysqlGenerator {
    /*+++++++++++++++++++++++++数据库配置++++++++++++++++++++++++++++*/
    private static final String IP4DB = "192.168.185.131"; // 【数据库IP】
    private static final String PORT = "3306"; // 端口
    private static final String DB = "cloud_alibaba"; // 【数据库】
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";  // 驱动
    private static final String USERNAME = "root"; // 用户名
    private static final String PWD = "123456"; // 密码
    private static final String PREFIX4TB = "ca_"; // 【表前缀】


    /*+++++++++++++++++++++++++作者配置++++++++++++++++++++++++++++*/
    private static final String AUTHOR = "peng.liao"; // 作者


    /*+++++++++++++++++++++++++类命名规约配置++++++++++++++++++++++++++++*/
    private static final String SUFFIX4Entity = "Entity"; // 实体类后缀
    private static final String SUFFIX4DAO = "Mapper";  // 映射类和xml文件后缀
    private static final String SUFFIX4SERVICE = "Service"; // service层后缀


    /*+++++++++++++++++++++++++目录位置配置++++++++++++++++++++++++++++*/
    private static final String DOMAIN_NAME = "com.dennis";  // 公司级别域名
    private static final String MODULE_NAME = "springcloud.payment";  // 【模块名】
    private static final String PACK4ENTITY = "entity"; // 各层对应的包名
    private static final String PACK4DAO = "mapper";
    private static final String PACK4SERVICE = "service";
    private static final String PACK4SERVICE_IMPL = "service.impl";
    private static final String PACK4CONTROLLER = "controller";
    // 【指定.java文件的资源路径入口,从maven识别的项目模块路径开始】
    private static final String SOURCE_FOLDER_PATH = "/cloud-alibaba-provider-payment8001/src/main/java";
    // 【指定.xml文件存放位置】
    private static final String MAPPER_XML_LOCATION = "/cloud-alibaba-provider-payment8001/src/main/resources/mapper/";

    public static String scanner(String tip) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!"".equals(ipt)) {
                return ipt;
            }
        }
        throw new Exception("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) throws Exception {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 指定.java文件的资源路径入口,从maven识别的项目模块路径开始
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + SOURCE_FOLDER_PATH);
        gc.setAuthor(AUTHOR);
        // 是否打开输出目录
        gc.setOpen(false);
        gc.setBaseResultMap(true); // XML ResultMap
        /* 自定义文件命名，注意 %s 会自动填充表实名！ */
        gc.setEntityName("%s" + SUFFIX4Entity);
        gc.setMapperName("%s" + SUFFIX4DAO);
        gc.setXmlName("%s" + SUFFIX4DAO);
        gc.setServiceName("%s" + SUFFIX4SERVICE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + IP4DB + ":" + PORT + "/" + DB + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
        // dsc.setSchemaName("public");
        dsc.setDriverName(DB_DRIVER);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PWD);
        mpg.setDataSource(dsc);

        // 指定各层文件输出的包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(DOMAIN_NAME);
        // pc.setModuleName(scanner("模块名"));
        pc.setModuleName(MODULE_NAME);
        pc.setController(PACK4CONTROLLER);
        pc.setService(PACK4SERVICE);
        pc.setServiceImpl(PACK4SERVICE_IMPL);
        pc.setMapper(PACK4DAO);
        pc.setEntity(PACK4ENTITY);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + MAPPER_XML_LOCATION
                        + tableInfo.getMapperName() + ".xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        // 除去表前缀，不参与实体类的命名
        strategy.setTablePrefix(PREFIX4TB);

        System.out.println(pc.getModuleName());
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
