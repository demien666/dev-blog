import com.demien.gradletest.TestClass;
import com.demien.gradletest.TestHelloProcessor;
import com.demien.gradletest.TestHiProcessor;
import com.demien.gradletest.TestProcessor;


public class App {
    public static void main(String[] args) {
        TestProcessor testHelloProcessor=new TestHelloProcessor();
        TestProcessor testHiProcessor=new TestHiProcessor();
        TestClass testClass=new TestClass();

        testClass.setTestProcessor(testHelloProcessor);
        System.out.println(testClass.greeting("Joe"));


        testClass.setTestProcessor(testHiProcessor);
        System.out.println(testClass.greeting("Anna"));
    }
}
