package com.yangcq.dsa.lovedsa.s1.class02_arraylist;

/**
 *
 * @author yangcq6
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        User u1 = new User(1, "rose");
        User u2 = new User(2, "jack");
        User u3 = new User(3, "mike");

        list.add(u1);
        list.add(u2);
        list.add(u3);

        System.out.println(list);

        System.out.println(list.indexOf(new User(1, "rose")));
        System.out.println(list.indexOf(u2));
        list.add(null);
        System.out.println(list.indexOf(null));
    }
}
