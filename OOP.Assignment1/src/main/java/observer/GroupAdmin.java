package observer;
import java.util.*;

public class GroupAdmin implements Sender{
    public ArrayList<Member> member_list;
    public UndoableStringBuilder usb;
    /**
     constructor for new GroupAdmin which is the observable in this project
     */
    public GroupAdmin()
    {
        this.member_list = new ArrayList<>();
        this.usb = new UndoableStringBuilder();
    }

    /**
     * register a new ConcreteMember(observer) to the GroupAdmin(observable) member list.
     * a member who registered will get all updates on changes made to the UndoableStringBuilder (shallow copy)
     * also, his usb will get the latest version of the GroupAdmin's usb
     * @param obj
     */
    @Override
    public void register(Member obj)
    {
        ConcreteMember m = (ConcreteMember) obj;
        if(member_list.contains(m) == false && m.usb == null) {
            member_list.add(m);
            notify(m,this.usb);
        }
    }

    /**
     * remove a ConcreteMember(observer) from the GroupAdmin(observable) member list.
     * the member will stop receiving updates and his usb will be set to null.
     * @param obj
     */
    @Override
    public void unregister(Member obj)
    {
        if(member_list.contains(obj)){
        obj.update(null);
        member_list.remove(obj);
        }
    }

    /**
     * uses the insert function in UndoableStringBuilder changes the GroupAdmin's usb
     * @param offset
     * @param obj
     */
    @Override
    public void insert(int offset, String obj)
    {
        this.usb.insert(offset,obj);
    }

    /**
     * uses the append function in UndoableStringBuilder changes the GroupAdmin's usb
     * @param obj
     */
    @Override
    public void append(String obj)
    {
        this.usb.append(obj);
    }

    /**
     * uses the delete function in UndoableStringBuilder changes the GroupAdmin's usb
     * @param start
     * @param end
     */
    @Override
    public void delete(int start, int end)
    {
        this.usb.delete(start,end);
    }

    /**
     * uses the undo function in UndoableStringBuilder, changes the GroupAdmin's usb
     */
    @Override
    public void undo() {
        this.usb.undo();
    }

    /**
     * notifies the member who registered to the GroupAdmin, we use it each time we register a new member to the GroupAdmin
     * @param usb
     */
    public void notify(Member m,UndoableStringBuilder usb)
    {
        m.update(usb);
    }
}