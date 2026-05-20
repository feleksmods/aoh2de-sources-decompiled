package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.GameValues.GameValues;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.MEHover_2E;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Image_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Space;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_TextDesc;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_2Type_Text_Big;
import age.of.civilizations2.jakowski.lukasz.MenuE_HoverP.ME_Hover_v2;
import java.util.ArrayList;

public class NationalBank {
    public static void updateReserves(int civID, long gold) {
        CFG.core.getCiv((int)civID).civGD.nationalBankReserves = Math.max(0L, CFG.core.getCiv((int)civID).civGD.nationalBankReserves + gold);
    }

    public static boolean constructNationalBank(int civID) {
        if (CFG.core.getCiv((int)civID).civGD.nationalBankBuilt) {
            return false;
        }
        if (CFG.core.getCiv(civID).getTechLevel() < GameValues.gvIncome.BANK_REQUIRED_TECH_LVL) {
            return false;
        }
        if (CFG.core.getCiv(civID).getNumOfProvs() < GameValues.gvIncome.BANK_REQUIRED_PROVINCES) {
            return false;
        }
        if (CFG.core.getCiv(civID).getGold() < (long)NationalBank.getBankCost()) {
            return false;
        }
        CFG.core.getCiv(civID).setGold(CFG.core.getCiv(civID).getGold() - (long)NationalBank.getBankCost());
        CFG.core.getCiv((int)civID).civGD.nationalBankBuilt = true;
        NationalBank.updateReserves(civID, GameValues.gvIncome.INITIAL_RESERVES_BONUS);
        return true;
    }

    public static int getReserveLimit(int civID) {
        return (int)Math.max(500.0f, (float)(CFG.core.getCiv((int)civID).incomeTaxation + CFG.core.getCiv((int)civID).incomeProduction) * GameValues.gvIncome.RESERVE_LIMIT_MODIFIER);
    }

    public static int getBankCost() {
        return Math.max(100, (int)((float)CFG.core.getGameScenars().getScenario_StartingPopulation() * GameValues.gvIncome.BANK_COST));
    }

    public static ME_Hover_v2 getHoverBank(int civID) {
        ArrayList<MEHover_2E> nElements = new ArrayList<MEHover_2E>();
        ArrayList<ME_Hover_2Type> nData = new ArrayList<ME_Hover_2Type>();
        nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("NationalBank"), CFG.COLOR_HOVER_TITLE));
        nData.add(new ME_Hover_2Type_Image_Big(Images.bank, CFG.PADD, 0));
        nData.add(new ME_Hover_2Type_Image_Big(CFG.core.getCiv((int)civID).civGD.nationalBankBuilt ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
        nElements.add(new MEHover_2E(nData));
        nData.clear();
        if (civID == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId()) {
            nData.add(new ME_Hover_2Type_Space());
            nElements.add(new MEHover_2E(nData));
            nData.clear();
            nData.add(new ME_Hover_2Type_Text_Big(CFG.lang.get("NationalBankReserves") + ": "));
            nData.add(new ME_Hover_2Type_Text_Big("" + CFG.getNumberWthSpaces("" + CFG.core.getCiv((int)civID).civGD.nationalBankReserves), CFG.COLOR_GOLD));
            nData.add(new ME_Hover_2Type_Image_Big(Images.topGold(), CFG.PADD, 0));
            nData.add(new ME_Hover_2Type_Image_Big(Images.bank, CFG.PADD, 0));
            nElements.add(new MEHover_2E(nData));
            nData.clear();
        }
        nData.add(new ME_Hover_2Type_Space());
        nElements.add(new MEHover_2E(nData));
        nData.clear();
        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("AcceptableTaxationLimit") + ": "));
        nData.add(new ME_Hover_2Type_Text("+" + (int)(GameValues.gvIncome.BANK_ACCEPTABLE_TAXATION * 100.0f) + "%", CFG.COLOR_POSITIVE));
        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
        nElements.add(new MEHover_2E(nData));
        nData.clear();
        nData.add(new ME_Hover_2Type_Text(CFG.lang.get("ReserveLimit") + ": "));
        nData.add(new ME_Hover_2Type_Text("" + CFG.getNumberWthSpaces("" + NationalBank.getReserveLimit(civID)), CFG.COLOR_GOLD));
        nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
        nElements.add(new MEHover_2E(nData));
        nData.clear();
        nData.add(new ME_Hover_2Type_Space());
        nElements.add(new MEHover_2E(nData));
        nData.clear();
        nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("NationalBankDesc1")));
        nElements.add(new MEHover_2E(nData));
        nData.clear();
        nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("NationalBankDesc2")));
        nElements.add(new MEHover_2E(nData));
        nData.clear();
        nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("NationalBankDesc3")));
        nElements.add(new MEHover_2E(nData));
        nData.clear();
        nData.add(new ME_Hover_2Type_TextDesc(CFG.lang.get("NationalBankDesc4")));
        nElements.add(new MEHover_2E(nData));
        nData.clear();
        if (civID == CFG.core.getPlayer(CFG.PLAYER_TURN_ID).getCivId() && !CFG.core.getCiv((int)civID).civGD.nationalBankBuilt) {
            nData.add(new ME_Hover_2Type_Space());
            nElements.add(new MEHover_2E(nData));
            nData.clear();
            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("Cost") + ": "));
            nData.add(new ME_Hover_2Type_Text("" + CFG.getNumberWthSpaces("" + NationalBank.getBankCost()), CFG.COLOR_GOLD));
            nData.add(new ME_Hover_2Type_Image(Images.topGold(), CFG.PADD, 0));
            nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getGold() >= (long)NationalBank.getBankCost() ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
            nElements.add(new MEHover_2E(nData));
            nData.clear();
            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredProvinces") + ": "));
            nData.add(new ME_Hover_2Type_Text("" + CFG.getNumberWthSpaces("" + GameValues.gvIncome.BANK_REQUIRED_PROVINCES), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new ME_Hover_2Type_Image(Images.provinces, CFG.PADD, 0));
            nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getNumOfProvs() >= GameValues.gvIncome.BANK_REQUIRED_PROVINCES ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
            nElements.add(new MEHover_2E(nData));
            nData.clear();
            nData.add(new ME_Hover_2Type_Text(CFG.lang.get("RequiredTechnologyLevel") + ": "));
            nData.add(new ME_Hover_2Type_Text("" + CFG.getPrecision2(GameValues.gvIncome.BANK_REQUIRED_TECH_LVL, 100), CFG.COLOR_TECHNOLOGY));
            nData.add(new ME_Hover_2Type_Image(Images.technology, CFG.PADD, 0));
            nData.add(new ME_Hover_2Type_Image(CFG.core.getCiv(civID).getTechLevel() >= GameValues.gvIncome.BANK_REQUIRED_TECH_LVL ? Images.iconTrue : Images.iconFalse, CFG.PADD, 0));
            nElements.add(new MEHover_2E(nData));
            nData.clear();
        }
        return new ME_Hover_v2(nElements);
    }
}
