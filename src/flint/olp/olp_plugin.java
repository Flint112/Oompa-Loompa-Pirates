package flint.olp;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import exerelin.campaign.SectorManager;

public class olp_plugin extends BaseModPlugin {

    @Override
    public void onNewGame() {
        boolean haveNexerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
        if (!haveNexerelin || !Global.getSector().getMemoryWithoutUpdate().getBoolean("$nex_randomSector"))
            new olp_gen().generate(Global.getSector());
        // You can add more methods from ModPlugin here. Press Control-O in IntelliJ to see options.
    }
}