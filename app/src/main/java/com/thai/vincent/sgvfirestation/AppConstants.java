package com.thai.vincent.sgvfirestation;

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
            return TierString.LowTier.getName();
        }

    }

    public enum MidTierPrice {

        Gram(15),
        Eighth(50),
        Quad(90),
        HalfOz(170),
        Ounce(300);

        private float price;

        MidTierPrice(float price) {
            this.price = price;
        }

        public float getPrice() {
            return price;
        }

        public String toString() {
            return TierString.MidTier.getName();
        }

    }

    public enum HighTierPrice {

        Gram(20),
        Eighth(60),
        Quad(105),
        HalfOz(190),
        Ounce(320);

        private float price;

        HighTierPrice(float price) {
            this.price = price;
        }

        public float getPrice() {
            return price;
        }

        public String toString() {
            return TierString.HighTier.getName();
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

        Sativa("Sativa"),
        Indica("Indica"),
        Hybrid("Hybrid"),
        Edible("Edible"),
        Wax("Wax"),
        Concentrate("Concentrate"),
        Pens("Pens"),
        Prerolls("Prerolls");

        private String name;

        ItemTypes(String name){this.name=name;}

        public String getName() {
            return name;
        }


    }


}
