package com.example.vincent.sgvfirestation;

/**
 * Created by Vincent on 5/7/2017.
 */

public class AppConstants {

    public static final String
            lowTier = ("low-tier"),
            midTier = ("mid-tier"),
            highTier = ("top-shelf"),
            connoisseur = ("best-of-best");

    public enum LowTierPrice {

        Gram(10),
        Eighth(35),
        Quad(70),
        HalfOz(140),
        Ounce(280);

        private float price;

        LowTierPrice(float price) {
            this.price = price;
        }

        public float getPrice() {
            return price;
        }

        public String toString() {
            return "low-tier";
        }

    }

    public enum TierString {
        LowTier("low-tier"),
        MidTier("mid-tier"),
        HighTier("top-shelf"),
        Connoisseur("best-of-best");
        private String name;

        TierString(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }


    public enum ItemTypes {
        Sativa,
        Indica,
        Hybrid,
        Edible,
        Wax,
        Concentrate,
        Pens,
        Prerolls;


    }


}
