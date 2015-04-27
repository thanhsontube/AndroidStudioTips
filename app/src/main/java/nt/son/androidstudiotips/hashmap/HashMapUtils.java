package nt.son.androidstudiotips.hashmap;

import java.util.EnumMap;

/**
 * Created by Sonnt on 4/27/15.
 */
public class HashMapUtils {
    public  enum ENUM_COUNTRY {
        VIETNAM,
        MALAYSIA,
        SINGAPORE
    }
    public class Country {
        public String name;
        public String phone;

        public Country(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }
        public void update (Country country) {
            this.name = country.name;
            this.phone = country.phone;
        }
    }

    public EnumMap <ENUM_COUNTRY, Country> map = new EnumMap<ENUM_COUNTRY, Country>(ENUM_COUNTRY.class);

    public static HashMapUtils instance = null;
    public static void createInstance() {
        instance = new HashMapUtils();
    }

    public static void add (ENUM_COUNTRY enum_country, Country country) {
        if(instance.map.containsKey(enum_country)) {
            instance.map.get(enum_country).update(country);
        } else {

            instance.map.put(enum_country, country);
        }
    }

}
