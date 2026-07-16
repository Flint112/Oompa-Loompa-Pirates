package flint.olp;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import flint.olp.world.systems.olp_system;

public class olp_gen{
    public static void initFactionRelationships(SectorAPI sector) {
        FactionAPI hegemony = sector.getFaction(Factions.HEGEMONY);
        FactionAPI tritachyon = sector.getFaction(Factions.TRITACHYON);
        FactionAPI pirates = sector.getFaction(Factions.PIRATES);
        FactionAPI kol = sector.getFaction(Factions.KOL);
        FactionAPI church = sector.getFaction(Factions.LUDDIC_CHURCH);
        FactionAPI path = sector.getFaction(Factions.LUDDIC_PATH);
        FactionAPI league = sector.getFaction(Factions.PERSEAN);
        FactionAPI olp= sector.getFaction("oompaloompapirates");

        olp.setRelationship(path.getId(), RepLevel.HOSTILE);
        olp.setRelationship(hegemony.getId(), RepLevel.FAVORABLE);
        olp.setRelationship(pirates.getId(), RepLevel.HOSTILE);
        olp.setRelationship(tritachyon.getId(), RepLevel.SUSPICIOUS);
        olp.setRelationship(church.getId(), RepLevel.INHOSPITABLE);
        olp.setRelationship(kol.getId(), RepLevel.INHOSPITABLE);
        olp.setRelationship(league.getId(), RepLevel.SUSPICIOUS);

    }

    public void generate(SectorAPI sector) {

        initFactionRelationships(sector);
        new olp_system().generate(sector);


    }
}