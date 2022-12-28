import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    public void test2(){
        String s1 = "Yuval";
        String s2 = "Yarin";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }
    @Test
    public void testRegister(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        a1.register(yuval);
        assertEquals(true,a1.member_list.contains(yuval));
        assertEquals(a1.usb,yuval.usb);
    }
    @Test
    public void testUnregister()
    {
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        a1.register(yuval);
        assertEquals(true,a1.member_list.contains(yuval));
        a1.unregister(yuval);
        assertEquals(false,a1.member_list.contains(yuval));
        assertEquals(null,yuval.usb);
    }
    @Test void testNotify()
    {
        GroupAdmin a1 = new GroupAdmin();
        a1.append("latestVersion");
        ConcreteMember yuval = new ConcreteMember();
        a1.register(yuval);
        assertEquals(a1.usb,yuval.usb);
    }
    @Test
    public void testAppend(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember abraham = new ConcreteMember();
        a1.register(abraham);
        a1.register(yuval);
        a1.append("hello");
        assertEquals("hello",yuval.usb.toString(),abraham.usb.toString());
    }
    @Test
    public void testInsert(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember abraham = new ConcreteMember();
        a1.register(abraham);
        a1.register(yuval);
        a1.insert(0,"testinsert");
        a1.insert(4,"test");
        assertEquals("testtestinsert",yuval.usb.toString(),abraham.usb.toString());
    }
    @Test
    public void testDelete(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember abraham = new ConcreteMember();
        a1.register(abraham);
        a1.register(yuval);
        a1.append("testdelete");
        a1.delete(4,10);
        assertEquals("test",yuval.usb.toString(),abraham.usb.toString());
    }
    @Test
    public void testUndo(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember abraham = new ConcreteMember();
        a1.register(abraham);
        a1.register(yuval);
        a1.append("test");
        a1.append("undo");
        a1.undo();
        assertEquals(a1.usb.toString(),yuval.usb.toString(),abraham.usb.toString());
        a1.undo();
        assertEquals(a1.usb.toString(),yuval.usb.toString(),abraham.usb.toString());
    }
    @Test
    public void testShallowCopy(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember abraham = new ConcreteMember();
        a1.register(abraham);
        a1.register(yuval);
        a1.append("test");
        a1.append("undo");
        assertEquals(a1.usb,yuval.usb);
        assertEquals(a1.usb,abraham.usb);
        a1.unregister(yuval);
        a1.append("test");
        ConcreteMember yarin = new ConcreteMember();
        a1.register(yarin);
        assertEquals(a1.usb,abraham.usb);
        assertEquals(a1.usb,yarin.usb);
        assertEquals(null,yuval.usb);
    }
    @Test
    public void testUpdate() {
        GroupAdmin a1 = new GroupAdmin();
        a1.append("hello");
        a1.append("world");
        ConcreteMember yarin = new ConcreteMember();
        assertEquals(null, yarin.usb);
        a1.register(yarin);
        a1.append("test");
        assertEquals("helloworldtest", yarin.usb.toString());
    }
    @Test
    public void testMultiGroupAdmins(){
        GroupAdmin a1 = new GroupAdmin();
        GroupAdmin a2 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember yarin = new ConcreteMember();
        a1.register(yuval);
        a2.register(yarin);
        a1.register(yarin);
        a2.register(yuval);
        assertEquals(a1.usb, yuval.usb);
        assertEquals(a2.usb,yarin.usb);
    }

}