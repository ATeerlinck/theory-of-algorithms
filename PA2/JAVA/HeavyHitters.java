import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HeavyHitters {

    public static ArrayList<Character> naive(String stream, int k) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < stream.length(); i++) {
            char c = stream.charAt(i);
            int f = freqMap.containsKey(c) ? freqMap.get(c) : 0;
            freqMap.put(c, f + 1);
        }
        return heavyHittersHelper(freqMap, stream.length(), k);
    }
    
    private static ArrayList<Character> heavyHittersHelper(HashMap<Character, Integer> freqMap, int n, int k) {
        Iterator<Character> it = freqMap.keySet().iterator();
        ArrayList<Character> heavyHitters = new ArrayList<>();
        while (it.hasNext()) {
            char c = it.next();
            int f = freqMap.get(c);
            if (f > n / k)
            heavyHitters.add(c);
        }
        return heavyHitters;
    }
    
    public static ArrayList<Character> misraGriesOnePass(String stream, int k) { // complete this method
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < stream.length(); i++) {
            char c = stream.charAt(i);
            if(freqMap.containsKey(c))freqMap.put(c, freqMap.get(c)+1);
            else if (freqMap.size() < k-1) freqMap.put(c, 1);
            else{
                Iterator<Character> it = freqMap.keySet().iterator();
                while (it.hasNext()) {
                    c = it.next();
                    freqMap.put(c, freqMap.get(c)-1);
                }
                for (int j = 0; j<stream.length(); j++) {
                    if(freqMap.containsKey(stream.charAt(j))) if(freqMap.get(stream.charAt(j)) == 0) freqMap.remove(stream.charAt(j));
                }
            }
            
        }
        ArrayList<Character> heavyHitters = new ArrayList<>();
        Iterator<Character> it = freqMap.keySet().iterator();
            while (it.hasNext()) {
                heavyHitters.add(it.next());
            }
            return heavyHitters;
    }
        
    public static ArrayList<Character> misraGriesTwoPass(String stream, int k) { // complete this method
        ArrayList<Character> heavyHitters = misraGriesOnePass(stream, k);
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (Character c : heavyHitters) {
            freqMap.put(c, 0);
        }
        for (int i = 0; i < stream.length(); i++) {
            char c = stream.charAt(i);
            if(freqMap.containsKey(c)) freqMap.put(c, freqMap.get(c)+1);
        }
        return heavyHittersHelper(freqMap, stream.length(), k);
    }
}
