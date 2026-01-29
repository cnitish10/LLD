package org.example.hashmap;

public class HashMapApp {
    public static void main(String[] args){
        MyCustomHashMap<String, String> map = new MyCustomHashMap<>(7);
        map.put("Nitish", "Ballia");
        map.put("Varun", "Varanasi");
        map.put("Aman", "Lucknow");
        System.out.println(map.get("Nitish"));
    }
}
