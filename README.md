# easy-util
### Test.class
```
public class Test {
    String data;
    public String getData() {return data;}
    public void setData(String data) {this.data = data;}
}
```
### 通用执行器
```
GeneralExecutor general = DefaultExecutorFactory.getExecutor(GeneralExecutor.class);
Test test = python2.execute(Test.class, HOME + "hello.py", null);
log.info("将执行输出的json信息反序列化成java对象 {}.data = {}", test, test.getData());
String s3 = python2.execute(HOME + "hello.py", null);
log.info("取执行输出最后一行{}", s3);
```
### 特定执行器
```
Python2Executor python2 = DefaultExecutorFactory.getExecutor(Python2Executor.class);
String s = general.execute("cat", HOME + "hello.py");
log.info("取执行输出最后一行 {}", s);
String s1 = general.execute(text -> "handler:" + text, "cat", HOME + "hello.py");
log.info("取执行输出所有行，通过handler进行处理 {}", s1);
```
