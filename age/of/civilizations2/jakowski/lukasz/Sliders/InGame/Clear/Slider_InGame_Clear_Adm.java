package age.of.civilizations2.jakowski.lukasz.Sliders.InGame.Clear;

import age.of.civilizations2.jakowski.lukasz.Sliders.InGame.Clear.Slider_InGame_Clear_Tech;

public class Slider_InGame_Clear_Adm
extends Slider_InGame_Clear_Tech {
    public Slider_InGame_Clear_Adm(float fModifier, String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        super(fModifier, sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    @Override
    public String getTextLeft() {
        if (this.getCurr() > 0) {
            return "-" + (float)((int)((float)this.getCurr() * this.fModifier * 100.0f)) / 100.0f + "%";
        }
        return "";
    }
}
