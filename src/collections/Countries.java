package collections;

import java.util.*;

/**
 * Created by utsav on 14/2/16.
 */
public class Countries {

    public static final String[][] DATA = {
            {"ALGERIA", "Algiers"},
            {"INDIA", "New Delhi"},
            {"USA", "Washington"}
    };

    private static class FlyWeightMap extends AbstractMap<String, String>{

        private static class Entry implements Map.Entry<String,String>{
            int index;
            Entry(int index){
                this.index = index;
            }

            @Override
            public String getKey() {
                return DATA[index][0];
            }

            @Override
            public String getValue() {
                return DATA[index][1];
            }

            @Override
            public String setValue(String value) {
                throw new UnsupportedOperationException();
            }

            @Override
            public int hashCode() {
                return DATA[index][0].hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                return DATA[index][0].equals(obj);
            }
        }

        static class EntrySet extends AbstractSet<Map.Entry<String,String>>{

            private int size;
            EntrySet(int size){
                if(size<0)
                    this.size=size;
                else if(size>DATA.length)
                    this.size = DATA.length;
                else
                    this.size = size;
            }

            private class Iter implements Iterator<Map.Entry<String, String>>{

                private Entry entry = new Entry(-1);
                @Override
                public boolean hasNext() {
                    return entry.index < size-1;
                }

                @Override
                public Map.Entry<String, String> next() {
                    entry.index++;
                    return entry;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            }

            @Override
            public Iterator<Map.Entry<String, String>> iterator() {
                return new Iter();
            }

            @Override
            public int size() {
                return this.size;
            }
        }

        @Override
        public Set<Map.Entry<String, String>> entrySet() {
            return entries;
        }

        private static Set<Map.Entry<String, String>> entries = new EntrySet(DATA.length);
    }

    static Map<String, String> select(final int size){
        return new FlyWeightMap(){
            public Set<Map.Entry<String,String>> entrySet(){
                return new EntrySet(size);
            }
        };
    }

    static Map<String,String> map = new FlyWeightMap();

    public static Map<String, String> capitals() {
        return map;
    }

    public static Map<String, String> capitals(int size){
        return select(size);
    }

    static List<String> names = new ArrayList<String>(map.keySet());

    public static List<String> names(){
        return names;
    }

    public static List<String> names(int size){
        return new ArrayList<String>(select(size).keySet());
    }

}
