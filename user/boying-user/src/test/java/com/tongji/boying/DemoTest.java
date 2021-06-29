//package com.tongji.boying;
//
//import base.InitHttpCase;
//import com.tongji.boying.mapper.BoyingStockMapper;
//import com.tongji.boying.model.BoyingStock;
//import com.tongji.boying.service.BoyingStockService;
//import com.tongji.boying.service.impl.BoyingStockServiceImpl;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvFileSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import javax.annotation.Resource;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
///**
// * Mockito是当前最流行的单元测试 Mock框架。采用 Mock框架，我们可以虚拟出一个外部依赖，
// * 降低测试组件之间的耦合度，只注重代码的流程与结果，真正地实现测试目的。
// *
// * Mock 测试就是在测试过程中，对于某些 不容易构造（如 HttpServletRequest 必须在 Servlet 容器中才能构造出来）
// * 或者不容易获取 比较复杂 的对象（如 JDBC 中的 ResultSet 对象），用一个 虚拟 的对象（Mock 对象）来创建，以便测试方法。
// *
// * 单元测试 是为了验证我们的代码运行正确性，我们注重的是代码的流程以及结果的正确与否。
// *
// * 对比真实运行代码，可能其中有一些 外部依赖 的构建步骤相对麻烦，如果我们还是按照真实代码的构建规则构造出外部依赖，会大大增加单元测试的工作，代码也会参杂太多非测试部分的内容，测试用例显得复杂难懂。
// *
// * 采用 Mock 框架，我们可以 虚拟 出一个 外部依赖，只注重代码的 流程与结果，真正地实现测试目的。
// *
// * Mock测试框架的好处
// * 可以很简单的虚拟出一个复杂对象（比如虚拟出一个接口的实现类）；
// * 可以配置 mock 对象的行为；
// * 可以使测试用例只注重测试流程与结果；
// * 减少外部类、系统和依赖给单元测试带来的耦合。
// */
//public class DemoTest extends InitHttpCase {
//    /*
//    @SpringBootApplication扩展自@Configuration，其scanBasePackages属性指定了扫描的根路径。确保测试目标类在这个路径下，而且需要明白这个路径下的所有bean都会被实例化。虽然我们已经尽可能的缩小了实例化的范围，但是我们没有避免其他无关类的实例化开销。
//     */
//    @Autowired
//    @InjectMocks
//    private BoyingStockServiceImpl boyingStockService;
//
//    // @MockBean负责声明这是一个模拟的bean。在进行单元测试时，需要将测试目标的所有依赖bean声明为模拟的bean，这些模拟的bean将被注入测试目标bean。
//    @Mock
//    private BoyingStockMapper boyingStockMapper;
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/integration/IT_TD_001_Test.csv", numLinesToSkip = 1)
//    public void selectByPrimaryKey(Integer id, String expected) {
//        MockitoAnnotations.initMocks(this);
//        BoyingStock stock = new BoyingStock();
//        stock.setId(3);
//        Mockito.when(boyingStockMapper.selectByPrimaryKey(id)).thenReturn(stock);
//
//        BoyingStock boyingStock = boyingStockService.selectByPrimaryKey(id);
//        if (boyingStock == null) assertEquals(expected, "null");
//        else assertEquals(expected, String.valueOf(boyingStock.getId()));
//    }
//}