package priv.mm.apache.digester;

import org.apache.commons.digester3.Digester;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;

/**
 * DigesterDemo
 *
 * @author maomao
 * @since 2019-10-30
 */
public class DigesterDemo {

    @Test
    public void test() throws IOException, SAXException {
        Digester digester = new Digester();
        digester.setValidating(false);

        /*
         * 遇到某个 pattern 时创建对象，可以使用 attributeName 参数覆盖默认 className
         */
        digester.addObjectCreate("employee", "priv.mm.apache.digester.Employee");
        /*
         * 遇到某个 pattern 时为创建的对象设置属性
         */
        digester.addSetProperties("employee");
        digester.addObjectCreate("employee/office", "priv.mm.apache.digester.Employee$Office");
        /*
         * Digester 实例有一个内部栈，用于临时存储创建的对象。当使用 addObjectCreate() 实例化一个类时，会把结果压入这个栈中。
         * 当调用两次 addObjectCreate() 时，第一个对象会优先被丢入栈中，然后是第二个对象。
         * addSetNext() 会调用第一个对象的指定方法，并将第二个对象作为参数，以此方式来建立两个对象之间的关联关系。
         */
        digester.addSetNext("employee/office", "setOffice");
        digester.addObjectCreate("employee/office/address", "priv.mm.apache.digester.Employee$Office$Address");
        digester.addSetProperties("employee/office/address");
        digester.addSetNext("employee/office/address", "setAddress");

        URL xmlUrl = DigesterDemo.class.getResource("example.xml");
        Employee employee = digester.parse(xmlUrl);
        Assert.assertNotNull(employee);
        Assert.assertEquals("Brian", employee.getFirstName());
        Assert.assertEquals("May", employee.getLastName());
        Assert.assertNotNull(employee.getOffice());
        Assert.assertEquals("Wellington Street", employee.getOffice().getAddress().getStreetName());
        Assert.assertEquals("10", employee.getOffice().getAddress().getStreetNumber());
    }
}