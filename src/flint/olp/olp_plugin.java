package flint.olp;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import exerelin.campaign.SectorManager;

public class olp_plugin extends BaseModPlugin {
    @Override
    public void onApplicationLoad() throws Exception {
        super.onApplicationLoad();

        // Test that the .jar is loaded and working, using the most obnoxious way possible.
        throw new RuntimeException("Template mod loaded and working!\nRemove this crash in TemplateModPlugin.");
    }

    @Override
    public void onNewGame() {
        super.onNewGame();
        // Add your code here, or delete this method (it does nothing unless you add code)

        // The code below requires that Nexerelin is added as a library (not a dependency, it's only needed to compile the mod).
        boolean isNexerelinEnabled = Global.getSettings().getModManager().isModEnabled("nexerelin");

        if (!isNexerelinEnabled || SectorManager.getManager().isCorvusMode()) {
            new olp_gen().generate(Global.getSector());
        }

        // You can add more methods from ModPlugin here. Press Control-O in IntelliJ to see options.
    }
}