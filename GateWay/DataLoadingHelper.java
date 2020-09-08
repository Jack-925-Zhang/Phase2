package GateWay;

import Model.Item;
import Model.ItemInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * this class is the helper class for the class which loads data
 * @author LINNI XIE
 * @version 1.8
 */
public class DataLoadingHelper {

    private static final DataLoadingHelper dataLoadingHelper = new DataLoadingHelper();

    public static DataLoadingHelper getInstance(){return dataLoadingHelper;}

    /**
     * loads data from string to list of item
     * @param s string needs to be loaded
     * @return loaded list of item
     */
    public List<ItemInterface> stringToListOfItem(String s){
        List<ItemInterface> result = new ArrayList<>();
        ItemInterface item = new Item(null,null,null,false);
        if(!s.isEmpty()&&!s.equals("[]")){
            String[] list = s.split(",");
            if(!(list.length == 0)){
            for(String itemId: list){
                item = item.SearchItemByKey(Integer.parseInt(itemId));
                result.add(item);
            }}
        }
        return result;
    }

    /**
     * loads data from string to hashmap of string and integer
     * @param keys string needs to be loaded
     * @param values string needs to be loaded
     * @return loaded hashmap of string and integer
     */
    public HashMap<String, Integer> stringsToHashMapStringAndInteger(String keys, String values){
        HashMap<String, Integer> result = new HashMap<>();
        if(!keys.isEmpty() && !values.isEmpty()&& !keys.equals("[]")&&!values.equals("[]")){
            String[] keyList = keys.split(",");
            String[] valueList = values.split(",");
            if(!(keyList.length == 0)&&!(valueList.length==0)){
            for(int j=0;j<keyList.length;j++){
                result.put(keyList[j], Integer.parseInt(valueList[j]));
            }}
        }
        return result;
    }

    /**
     * loads data from string to list of string
     * @param s string needs to be loaded
     * @return loaded list of string
     */
    public List<String> stringToListOfString(String s){
        List<String> result = new ArrayList<>();
        if(!s.isEmpty()&&!s.equals("[]")){
            String[] list = s.split(",");
            if(!(list.length == 0)){
            for(String i:list){
                result.add(i);
            }}
        }
        return result;
    }

    /**
     * loads data from string to list of integer
     * @param s string needs to be loaded
     * @return loaded list of integer
     */
    public List<Integer> stringToListOfInteger(String s){
        List<Integer> result = new ArrayList<>();
        if(!s.isEmpty()&&!s.equals("[]")){
            String[] list = s.split(",");
            if(!(list.length == 0)){
            for(String i:list){
                result.add(Integer.parseInt(i));
            }}
        }
        return result;
    }

    /**
     * loads data from string to hashmap of integer and string
     * @param keys string needs to be loaded
     * @param values string needs to be loaded
     * @return loaded hashmap of integer and string
     */
    public HashMap<Integer, String> stringsToHashMapIntegerAndString(String keys, String values){
        HashMap<Integer, String> result = new HashMap<>();
        if(!keys.isEmpty() && !values.isEmpty()&& !keys.equals("[]")&&!values.equals("[]")){
            String[] keyList = keys.split(",");
            String[] valueList = values.split(",");
            if(!(keyList.length == 0)&&!(valueList.length==0)){
            for(int j=0;j<keyList.length;j++){
                result.put(Integer.parseInt(keyList[j]), valueList[j]);
            }}
        }
        return result;
    }

    /**
     * loads data from string to hashmap of integer and integer
     * @param keys string needs to be loaded
     * @param values string needs to be loaded
     * @return loaded hashmap of integer and integer
     */
    public HashMap<Integer, Integer> stringsToHashMapIntegerAndInteger(String keys, String values){
        HashMap<Integer, Integer> result = new HashMap<>();
        if(!keys.isEmpty() && !values.isEmpty()&& !keys.equals("[]")&&!values.equals("[]")){
            String[] keyList = keys.split(",");
            String[] valueList = values.split(",");
            if(!(keyList.length == 0)&&!(valueList.length==0)){
            for(int j=0;j<keyList.length;j++){
                result.put(Integer.parseInt(keyList[j]), Integer.parseInt(valueList[j]));
            }}
        }
        return result;
    }

    /**
     * loads data from string to hashmap of string and string
     * @param keys string needs to be loaded
     * @param values string needs to be loaded
     * @return loaded hashmap of string and string
     */
    public HashMap<String, String> stringsToHashMapStringAndString(String keys, String values){
        HashMap<String, String> result = new HashMap<>();
        if(!keys.isEmpty() && !values.isEmpty()&& !keys.equals("[]")&&!values.equals("[]")){
            String[] keyList = keys.split(",");
            String[] valueList = values.split(",");
            if(!(keyList.length == 0)&&!(valueList.length==0)){
            for(int j=0;j<keyList.length;j++){
                result.put(keyList[j], valueList[j]);
            }}
        }
        return result;
    }

}
