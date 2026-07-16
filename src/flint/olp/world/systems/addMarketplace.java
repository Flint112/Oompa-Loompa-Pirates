package flint.olp.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.ArrayList;
import java.util.List;

public class addMarketplace{

    public static MarketAPI addMarketplace(String factionID, SectorEntityToken primaryEntity, ArrayList<SectorEntityToken> connectedEntities, String name,
                                           int size, ArrayList<String> marketConditions, ArrayList<String> Industries, ArrayList<String> submarkets, float tariff) {
        EconomyAPI globalEconomy = Global.getSector().getEconomy();
        String Loompaland = primaryEntity.getId();
        String LoompalandmarketID = Loompaland;

        MarketAPI Loompalandmarket = Global.getFactory().createMarket(LoompalandmarketID, name, size);
        Loompalandmarket.setFactionId(factionID);
        Loompalandmarket.setPrimaryEntity(primaryEntity);
        Loompalandmarket.getTariff().modifyFlat("generator", tariff);

        if (null != submarkets){
            for (String market : submarkets){
                Loompalandmarket.addSubmarket(market);
            }
        }

        for (String condition : marketConditions) {
            Loompalandmarket.addCondition(condition);
        }

        for (String industry : Industries) {
            Loompalandmarket.addIndustry(industry);
        }

        if (null != connectedEntities) {
            for (SectorEntityToken entity : connectedEntities) {
                Loompalandmarket.getConnectedEntities().add(entity);
            }
        }

        globalEconomy.addMarket(Loompalandmarket, true);
        primaryEntity.setMarket(Loompalandmarket);
        primaryEntity.setFaction("oompaloompapirates");

        if (null != connectedEntities) {
            for (SectorEntityToken entity : connectedEntities) {
                entity.setMarket(Loompalandmarket);
                entity.setFaction("oompaloompapirates");
            }
        }

        return Loompalandmarket;
    }
}
