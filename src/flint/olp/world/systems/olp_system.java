package flint.olp.world.systems;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class olp_system {
    public void generate(SectorAPI sector) {

        float planet1Dist= 4000f; //this is the distance the planet is from the star

        StarSystemAPI system = sector.createStarSystem("Loompa");
        system.getLocation().set(5325, 1500); //position of the system on the map

        system.setBackgroundTextureFilename("graphics/backgrounds/background1.jpg");

        // create the star and generate the hyperspace anchor for this system
        PlanetAPI olp_system_star = system.initStar("Loompa ", // unique id for this star
                "star_yellow", // id in planets.json
                900f, // radius (in pixels at default zoom)
                400); // corona radius, from star edge
        system.setLightColor(new Color(247,255,140)); // light color in entire system, affects all entities

        PlanetAPI Loompaland = system.addPlanet("Loompaland",
                olp_system_star, //what it's orbiting
                "Loompaland", //name
                "jungle", //the planet type (look in planets.json for more)
                900, //angle
                130f, //radius
                planet1Dist, //distance from star
                365f); //how many days to orbit
        Loompaland.setCustomDescriptionId("Loompaland"); //for custom descriptions

        PlanetAPI little_loompa = system.addPlanet("Little Loompa",
                Loompaland, //what it's orbiting
                "Little Loompa", //name
                "barren", //the planet type (look in planets.json for more)
                500, //angle
                40f, //radius
                planet1Dist, //distance from star
                1f); //how many days to orbit
        little_loompa.setCustomDescriptionId("The moon of Loompaland"); //for custom descriptions

        PlanetAPI Glorp = system.addPlanet("Glorp",
                olp_system_star,
                "Glorp",
                "ice_giant",
                900,
                500f,
                planet1Dist,
                500f);

        PlanetAPI Whizzlesnax = system.addPlanet("Whizzlesnax",
                Glorp,
                "Whizzlesnax",
                "toxic_cold",
                300,
                100f,
                planet1Dist,
                2137f);

        system.autogenerateHyperspaceJumpPoints(true, true); //generates jump points

        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin(); //these lines clear the hyperspace clouds around the system
        NebulaEditor editor = new NebulaEditor(plugin);
        float minRadius = plugin.getTileSize() * 2f;

        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f, 0.25f);
        MarketAPI Loompaland_market = olp_market.addMarketplace("olp", Loompaland,
                null, //connected entities, like stations
                "Loompaland",
                10, //size
                new ArrayList<>(Arrays.asList( //these are conditions
                        Conditions.POPULATION_10,
                        Conditions.FARMLAND_RICH,
                        Conditions.ORE_RICH,
                        Conditions.HABITABLE,
                        Conditions.REGIONAL_CAPITAL,
                        Conditions.WATER,
                        Conditions.JUNGLE,
                        Conditions.COMM_RELAY
                )),
                new ArrayList<>(Arrays.asList( //industries
                        Industries.POPULATION,
                        Industries.MEGAPORT,
                        Industries.ORBITALSTATION,
                        Industries.MINING,
                        Industries.HEAVYBATTERIES,
                        Industries.WAYSTATION,
                        Industries.FARMING,
                        Industries.PATROLHQ,
                        Industries.HIGHCOMMAND

                )),
                new ArrayList<>(Arrays.asList(Submarkets.SUBMARKET_STORAGE, //aaand markets
                        Submarkets.GENERIC_MILITARY,
                        Submarkets.SUBMARKET_BLACK,
                        Submarkets.LOCAL_RESOURCES,
                        Submarkets.SUBMARKET_OPEN)),
                0.15f
        );
    }
}