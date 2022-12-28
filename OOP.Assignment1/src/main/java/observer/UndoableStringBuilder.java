package observer;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class UndoableStringBuilder {
    public StringBuilder first;
    public StringBuilder str;
    public Stack<StringBuilder> s;

    /**
     Constructs a string builder, use no arguments for an empty string.
     */
    public UndoableStringBuilder()
    {
        this.first = new StringBuilder("");
        this.str= new StringBuilder("");
        this.s = new Stack<StringBuilder>();
        this.s.push(new StringBuilder(""));

    }


    public UndoableStringBuilder(StringBuilder str)
    {
        this.first = str;
        this.str = new StringBuilder(str.toString());
        this.s = new Stack<StringBuilder>();
        this.s.push(new StringBuilder(str));
    }
    /**
     * Appends the specified string to this character sequence.
     * @param str
     * @return concatenated StringBuilder object
     */
    public UndoableStringBuilder append (String str)
    {
        if (str!=null)
        {
            this.s.push(this.str);
            this.str = new StringBuilder(this.str.append(str).toString());

        }
        return this;
    }


    /**
     *Removes the characters in a substring of this sequence. The substring begins
     at the specified start and extends to the character at index
     end - 1 or to the end of the sequence if no such character exists.
     If start is equal to end, no changes are made.
     * @param start
     * @param end
     * @exception StringIndexOutOfBoundsException if bad input
     * @return A deleted StringBuilder object at the specified indexes.
     */
    public UndoableStringBuilder delete (int start, int end)
    {
        try {
            this.s.push(this.str);
            this.str = new StringBuilder (this.str.delete(start, end).toString());
        }
        catch (StringIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            System.out.println("Please enter a valid start and end index");
        }
        return this;
    }
    /**
     * Inserts the string into this character sequence.
     * @param offset
     * @param str
     * @exception StringIndexOutOfBoundsException if bad input
     * @return StringBuilder object with inserted string at the specified indexes.
     */
    public UndoableStringBuilder insert(int offset, String str)
    {
        try {
            this.s.push(this.str);
            this.str = new StringBuilder(this.str.insert(offset, str).toString());
        }

        catch(StringIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            System.out.println("Please enter a valid offset index");
        }

        return this;
    }
    /**
     * Replaces the characters in a substring of this sequence with characters in
     the specified String. The substring begins at the specified start and
     extends to the character at index end - 1 or to the end of the sequence if
     no such character exists. First the characters in the substring are removed
     and then the specified String is inserted at start. (This sequence will be
     lengthened to accommodate the specified String if necessary).
     * @param start
     * @param end
     * @param str
     * @exception StringIndexOutOfBoundsException if bad input
     * @return A StringBuilder object with the replaced string at the specified indexes.
     */
    public UndoableStringBuilder replace(int start , int end , String str)
    {
        try {
            this.s.push(this.str);
            this.str = new StringBuilder(this.str.replace(start, end, str).toString());
        }
        catch(StringIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            System.out.println("Please enter a valid start and end index");
        }

        return this;
    }
    /**
     * Causes this character sequence to be replaced by the reverse of the
     sequence.
     * @return A reversed StringBuilder object.
     */
    public UndoableStringBuilder reverse()
    {
        this.s.push(this.str);
        this.str = new StringBuilder(this.str.reverse().toString());

        return new UndoableStringBuilder(this.str);	}
    /**
     * undo this operation.
     * @return Last StringBuilder object (before the operation).
     * @exception EmptyStackException if stack is empty
     */
    public void undo()
    {
        try {
            s.pop();
            this.str = new StringBuilder(s.peek().toString());
        }
        catch (EmptyStackException e){
            e.printStackTrace();
            System.out.println("Cannot undo empty string");
            if(first.toString() == "")
                s.push(new StringBuilder(""));
            else
                s.push(new StringBuilder(first));
            this.str = s.pop();
        }
    }

    @Override
    public String toString()
    {
        return this.str.toString();
    }
}