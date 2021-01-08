@XStreamOmitField注解作用在字段上, 在xml转化为pojo过程中, 忽略被该注解注释的字段, 不为该字段赋值

@XStreamImplicit(itemFieldName = "subject")注解作用在集合类型字段上,使该字段显示的时候为隐式集合(隐藏<list>标签)

@XStreamAlias("message") 别名注解用于实体类字段和xml文档标签名称不对应时使用,作用在类和字段上

 String filePath = "G:\\java\\apl-lms-label-print-java\\dev-file\\text.xml";
        XStream xStream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xStream);
//        xStream.processAnnotations(Student.class);
        xStream.allowTypes(new Class[]{Student.class});
//        xStream.addPermission(AnyTypePermission.ANY);
        File xmlFile = new File(filePath);
        Student result = (Student) xStream.fromXML(xmlFile);
        System.out.println(result.toString());