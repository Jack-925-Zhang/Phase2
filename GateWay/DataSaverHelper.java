package GateWay;

import java.util.*;

import Model.*;
import com.google.gson.*;

/**
 * This class DataSaverHelper stores all the Helper function which used
 * in the Gateway class.
 * It's all the convert method from various type of attribute to the String
 * or List of String.
 * This class uses the gson.jar from google and the for-loops to do it.
 * @author Yupeng Zhang
 * @version 1.8
 */

public class DataSaverHelper {

    private static final DataSaverHelper dataSaverHelper = new DataSaverHelper();

    /**
     * gets instance dataSaverHelper
     * @return dataSaverHelper
     */
    public static DataSaverHelper getInstance(){return dataSaverHelper;}

    /**
     * An empty constructor to instantiate the DataSaverHelper.
     */
    public DataSaverHelper(){

    }

    /**
     * converts StringArray to String
     * @param StringArray the ArrayList of String that needs to be converted
     * @return the String converted from StringArray
     */
    public String StringArrayListConvert01(ArrayList<String> StringArray){
        Gson gson = new Gson();
        return gson.toJson(StringArray);
    }

    /**
     * converts IntArray to String
     * @param IntArray the ArrayList of Integer that needs to be converted
     * @return the String converted from IntArray
     */
    public String IntegerArrayListConvert01(ArrayList<Integer> IntArray){
        Gson gson = new Gson();
        return gson.toJson(IntArray);
    }

    /**
     * converts KeySet to String
     * @param KeySet the Set of String that needs to be converted
     * @return the String converted from KeySet
     */
    public String KeySetStringConvert(Set<String> KeySet){
        ArrayList<String> outputArray = new ArrayList<>(KeySet);
        return StringArrayListConvert01(outputArray);
    }

    /**
     * converts KeySet to String
     * @param KeySet the Set of Integer that needs to be converted
     * @return the String converted from KeySet
     */
    public String KeySetIntegerConvert(Set<Integer> KeySet){
        ArrayList<Integer> outputArray = new ArrayList<>(KeySet);
        return IntegerArrayListConvert01(outputArray);
    }
    /**
     * converts Collection to String
     * @param Collection the Collection of String that needs to be converted
     * @return the String converted from Collection
     */
    public String StringCollectionStringCovert(Collection<String> Collection){
        ArrayList<String> outputArray = new ArrayList<>(Collection);
        return StringArrayListConvert01(outputArray);
    }

    /**
     * converts Collection to String
     * @param Collection the ArrayList of Integer that needs to be converted
     * @return the String converted from Collection
     */
    public String IntegerCollectionStringConvert(Collection<Integer> Collection){
        ArrayList<Integer> outputArray = new ArrayList<>(Collection);
        return IntegerArrayListConvert01(outputArray);
    }

    /**
     * converts ItemInterfaceList to String
     * @param ItemInterfaceList the List of ItemInterface that needs to be converted
     * @return the String converted from ItemInterfaceList
     */
    public String ItemInterfaceListCovert(List<ItemInterface> ItemInterfaceList){
        ArrayList<String> outputArray = new ArrayList<>();
        if(!(ItemInterfaceList.isEmpty())) {
            for (ItemInterface Item : ItemInterfaceList) {
                outputArray.add(String.valueOf(Item.getItemId()));
            }
        }
        return StringArrayListConvert01(outputArray);
    }


    /**
     * converts StringList to String
     * @param StringList the List of String that needs to be converted
     * @return the String converted from StringList
     */
    public String StringListConvert01(List<String> StringList){
        Gson gson = new Gson();
        return gson.toJson(StringList);
    }

    /**
     * converts IntList to String
     * @param IntList the List of Integer that needs to be converted
     * @return the String converted from IntList
     */
    public String IntegerListConvert01(List<Integer> IntList){
        Gson gson = new Gson();
        return gson.toJson(IntList);
    }
}
