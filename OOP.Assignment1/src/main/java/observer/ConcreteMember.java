package observer;

public class ConcreteMember implements Member{
    public UndoableStringBuilder usb;
    /**
     * shallow copies the member's UndoableStringBuilder to the GroupAdmin's UndoableStringBuilder
     * @param usb
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }
}