package com.example.vincent.sgvfirestation.models;

import android.graphics.drawable.Icon;

import com.example.vincent.sgvfirestation.AppConstants;
import com.example.vincent.sgvfirestation.Firebase.FirebaseManager;

/**
 * Created by Vincent on 5/7/2017.
 */

public class MenuListItem {

    private long itemId;

    private String itemName;

    private String priceTier;
    private String itemType;
    private String description;

    private Icon image;
    private float singlePrice,eightPrice, quadPrice, halfOzPrice, ouncePrice,
            thcLevel, cbdLevel, cbnLevel;


    public MenuListItem(String itemName, String priceTier, String itemType, String description) {
        this.itemName = itemName;
        this.priceTier = priceTier;
        this.itemType = itemType;
        this.description = description;
        this.itemId = FirebaseManager.menuItemIdIncrementor++;
        this.thcLevel = 25f;
        this.cbdLevel = 5f;
        this.cbdLevel = 2f;
        switch(priceTier){
            case AppConstants.lowTier:
                singlePrice = AppConstants.LowTierPrice.Gram.getPrice();
                eightPrice =  AppConstants.LowTierPrice.Eighth.getPrice();
                quadPrice = AppConstants.LowTierPrice.Quad.getPrice();
                halfOzPrice = AppConstants.LowTierPrice.HalfOz.getPrice();
                ouncePrice = AppConstants.LowTierPrice.Ounce.getPrice();
                break;
            case AppConstants.midTier:
                singlePrice = AppConstants.MidTierPrice.Gram.getPrice();
                eightPrice =  AppConstants.MidTierPrice.Eighth.getPrice();
                quadPrice = AppConstants.MidTierPrice.Quad.getPrice();
                halfOzPrice = AppConstants.MidTierPrice.HalfOz.getPrice();
                ouncePrice = AppConstants.MidTierPrice.Ounce.getPrice();
                break;
            case AppConstants.highTier:
                singlePrice = AppConstants.HighTierPrice.Gram.getPrice();
                eightPrice =  AppConstants.HighTierPrice.Eighth.getPrice();
                quadPrice = AppConstants.HighTierPrice.Quad.getPrice();
                halfOzPrice = AppConstants.HighTierPrice.HalfOz.getPrice();
                ouncePrice = AppConstants.HighTierPrice.Ounce.getPrice();
                break;
            case AppConstants.connoisseur:
                break;
        }
    }

    public MenuListItem(){


    }




    public long getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPriceTier() {
        return priceTier;
    }

    public void setPriceTier(String priceTier) {
        this.priceTier = priceTier;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(float singlePrice) {
        this.singlePrice = singlePrice;
    }

    public float getEightPrice() {
        return eightPrice;
    }

    public void setEightPrice(float eightPrice) {
        this.eightPrice = eightPrice;
    }

    public float getQuadPrice() {
        return quadPrice;
    }

    public void setQuadPrice(float quadPrice) {
        this.quadPrice = quadPrice;
    }

    public float getHalfOz() {
        return halfOzPrice;
    }

    public void setHalfOz(float halfOz) {
        this.halfOzPrice = halfOz;
    }

    public float getOuncePrice() {
        return ouncePrice;
    }

    public void setOuncePrice(float ouncePrice) {
        this.ouncePrice = ouncePrice;
    }

    public float getThcLevel() {
        return thcLevel;
    }

    public void setThcLevel(float thcLevel) {
        this.thcLevel = thcLevel;
    }

    public float getCbdLevel() {
        return cbdLevel;
    }

    public void setCbdLevel(float cbdLevel) {
        this.cbdLevel = cbdLevel;
    }

    public float getCbnLevel() {
        return cbnLevel;
    }

    public void setCbnLevel(float cbnLevel) {
        this.cbnLevel = cbnLevel;
    }


}
